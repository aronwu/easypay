package com.wuswoo.easypay.service.controller.impl;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.common.util.ReflectionUtil;
import com.wuswoo.easypay.common.util.TimeCost;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.http.server.Response;
import com.wuswoo.easypay.service.aspect.Contract;
import com.wuswoo.easypay.service.controller.IPaymentController;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.payment.IEasyPaymentService;
import com.wuswoo.easypay.service.payment.IRefundService;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentRequest;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentResponse;
import com.wuswoo.easypay.service.payment.contract.EasyRefundRequest;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.repository.IPaymentService;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.request.PaymentRequest;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.PaymentServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxinjun on 16/9/14.
 */
@Controller("paymentController")
public class PaymentController implements IPaymentController {
    private static final Logger logger = LogManager.getLogger(PaymentController.class);

    @Autowired
    private IPaymentDBService paymentDBService;

    @Autowired
    private IPaymentService zhifuService;

    @Override
    @Contract(EasyPaymentRequest.class)
    public void postPayment(Request request, Response response) throws Exception {
        EasyPaymentRequest contract = (EasyPaymentRequest) request.getContract();
        Payment payment = null;
        logger.info("postPayment contract: {}", contract);
        try {
            payment = ReflectionUtil.convert2Clazz(contract, Payment.class);
            logger.info("payment info: {}", JSONObject.toJSONString(payment));
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
        }
        if (payment == null){
            //输入数据出现问题
            throw new EasyPayException("输入支付信息有误", EasyPayException.INPUT_CONTRACT_ERROR);
        }
        IEasyPaymentService iEasyPaymentService = PaymentServiceFactory.getPaymentService(payment.getPlatformId());
        EasyPaymentResponse easyPaymentResponse = null;
        try {
            PaymentRequest paymentRequest = iEasyPaymentService.newPayment(payment);
            easyPaymentResponse = zhifuService.generatePaymentRequest(payment, paymentRequest);
        } catch (EasyPayException e) {
            easyPaymentResponse = new EasyPaymentResponse();
            easyPaymentResponse.setCode(e.getCode());
            easyPaymentResponse.setMessage(e.getMessage());
        } catch (Exception e) {
            easyPaymentResponse = new EasyPaymentResponse();
            easyPaymentResponse.setCode(EasyPayException.UNKNOWN_ERROR);
            easyPaymentResponse.setMessage(e.getMessage());
        }
        easyPaymentResponse.setCostTime(response.cost());
        response.respondJson(easyPaymentResponse);
    }

    @Override
    public void getPayment(Request request, Response response) {

    }

    @Override
    @Contract(EasyRefundRequest.class)
    public void postRefund(Request request, Response response) throws Exception {
        EasyPaymentResponse easyPaymentResponse = new EasyPaymentResponse();
        try {
            EasyRefundRequest easyRefundRequest = (EasyRefundRequest) request.getContract();
            Map<String, PaymentResult> paymentResults= getPaymentsByRefundRequest(easyRefundRequest);
            //初始化refund
            Refund refund = new Refund();
            refund.setUserId(easyRefundRequest.getUserId());
            refund.setAppId(easyRefundRequest.getAppId());
            //初始化退款列表
            for(RefundResult refundResult : easyRefundRequest.getBatchRefunds()) {
                PaymentResult paymentResult = paymentResults.get(refundResult.getPaymentCode());
                if (paymentResult == null){
                    throw new EasyPayException(String.format("没有找到支付单号:%s", refundResult.getPaymentCode()), EasyPayException.UNKNOWN_ERROR);
                }
                refundResult.setPlatformId(paymentResult.getPlatformId());
                refundResult.setTradeNo(paymentResult.getTradeNo());
                refundResult.setRefundAmount(null);
                refundResult.setStatus(null);
                refundResult.setReturnCode(null);
            }
            refund.setPlatformId(easyRefundRequest.getBatchRefunds().get(0).getPlatformId());
            IRefundService refundService = PaymentServiceFactory.getRefundService(refund.getPlatformId());
            easyPaymentResponse = refundService.refund(refund, easyRefundRequest.getBatchRefunds());

        } catch (EasyPayException e) {
            easyPaymentResponse.setCode(e.getCode());
            easyPaymentResponse.setMessage(e.getMessage());
        }
        easyPaymentResponse.setCostTime(response.cost());
        response.respondJson(easyPaymentResponse);
    }


    @Override
    public void getRefund(Request request, Response response) {

    }

    private Map<String,PaymentResult> getPaymentsByRefundRequest(EasyRefundRequest refundRequest) {
        List<String> paymentCodes = new ArrayList<String>();
        for(RefundResult refundResult : refundRequest.getBatchRefunds()) {
            paymentCodes.add(refundResult.getPaymentCode());
        }
        List<PaymentResult> paymentResults = paymentDBService.getPaymentResultsByPaymentCodes(paymentCodes,
            PayConstant.PaymentStatus.SUCCESS.byteValue());
        if (paymentResults.size() != refundRequest.getBatchRefunds().size()) {
            throw new EasyPayException("批量退款单数和实际支付单数不一致", EasyPayException.UNKNOWN_ERROR);
        }
        //检查是否批量退款是否同一支付平台
        Integer platformId = null;
        for(PaymentResult paymentResult : paymentResults) {
            if (platformId == null) {
                platformId = paymentResult.getPlatformId();
            } else if (platformId != paymentResult.getPlatformId()) {
                throw new EasyPayException("批量退款必须来源同一支付平台", EasyPayException.UNKNOWN_ERROR);
            }
        }
        Map<String, PaymentResult> results = new HashMap<String, PaymentResult>();
        for(PaymentResult paymentResult : paymentResults) {
            results.put(paymentResult.getPaymentCode(), paymentResult);
        }
        return results;
    }
}
