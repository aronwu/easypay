package com.wuswoo.easypay.service.repository.impl;

import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.mapper.NotifyScheduleMapper;
import com.wuswoo.easypay.service.model.*;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentResponse;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.repository.IPaymentService;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.repository.IResultQueryDBService;
import com.wuswoo.easypay.service.request.PaymentRequest;
import com.wuswoo.easypay.service.util.PayConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxinjun on 16/9/14.
 */
@Service("paymentService")
public class PaymentService implements IPaymentService {
    private final static Logger logger = LogManager.getLogger(PaymentService.class);

    @Autowired
    private IPaymentDBService paymentDBService;
    @Autowired
    private IRefundDBService refundDBService;
    @Autowired
    private IResultQueryDBService resultQueryDBService;
    @Autowired
    private NotifyScheduleMapper notifyScheduleMapper;

    @Autowired
    @Qualifier("callbackServiceFactory")
    private ICallbackService callbackService;

    /**
     * 处理支付结果
     * 只处理支付成功,同时异步通知第三方业务系统
     * @param paymentResult
     * @param ignoreNotify
     * @return
     */
    @Override
    public boolean savePaymentResult(PaymentResult paymentResult, boolean ignoreNotify) {
        if (paymentResult  == null) {
            throw new EasyPayException("保存支付结果失败", EasyPayException.UNKNOWN_ERROR);
        }
        boolean result = false;
        //不处理非支付状态
        if (paymentResult.getStatus().byteValue() > PayConstant.PaymentStatus.TEMP.byteValue()) {
            return true;
        }

        PaymentResult existedPaymentResult = paymentDBService.getPaymentResult(paymentResult.getPaymentCode());

        if (existedPaymentResult != null && existedPaymentResult.getStatus() != null) {
            //已经成功支付,不更新记录,会通知业务系统
            if ( PayConstant.PaymentStatus.SUCCESS.byteValue() != existedPaymentResult.getStatus().byteValue()) {
                //没有支付成功用最新
                paymentDBService.savePaymentResult(paymentResult);
            }

            if (PayConstant.PaymentStatus.SUCCESS.byteValue() == paymentResult.getStatus().byteValue() && !ignoreNotify) {
                NotifySchedule notifySchedule = new NotifySchedule();
                notifySchedule.setPaymentCode(paymentResult.getPaymentCode());
                notifySchedule.setTradeId(existedPaymentResult.getId());
                notifySchedule.setTradeType(PayConstant.NotifyResultType.PAYMENT.byteValue());
                notifySchedule.setPlatformId(paymentResult.getPlatformId());
                notifySchedule.setStatus(PayConstant.PaymentStatus.SUCCESS.byteValue());
                callbackService.updatePaymentStatus(notifySchedule);
            }
        } else {

            int saveResult = paymentDBService.savePaymentResult(paymentResult);
            if (saveResult == 1 && PayConstant.PaymentStatus.SUCCESS.byteValue() == paymentResult.getStatus().byteValue() && !ignoreNotify) {
                logger.info("first recevie payment success notify result {}", paymentResult);
                NotifySchedule notifySchedule = new NotifySchedule();
                notifySchedule.setPaymentCode(paymentResult.getPaymentCode());
                notifySchedule.setTradeId(existedPaymentResult.getId());
                notifySchedule.setTradeType(PayConstant.NotifyResultType.PAYMENT.byteValue());
                notifySchedule.setPlatformId(paymentResult.getPlatformId());
                notifySchedule.setStatus(PayConstant.PaymentStatus.SUCCESS.byteValue());
                callbackService.updatePaymentStatus(notifySchedule);
            }
            result = true;
        }
        return result;
    }

    @Override
    public boolean saveRefundAndResults(Refund refund, List<RefundResult> refundResults) {
        boolean needNotify = refundDBService.saveRefundResult(refund, refundResults);
        if (needNotify) {
            //通知业务系统
            for (RefundResult refundResult : refundResults) {
                NotifySchedule notifySchedule = new NotifySchedule();
                notifySchedule.setPaymentCode(refundResult.getPaymentCode());
                notifySchedule.setOrderId(refundResult.getOrderId());
                notifySchedule.setTradeId(refundResult.getId());
                notifySchedule.setTradeType(PayConstant.NotifyResultType.REFUND.byteValue());
                notifySchedule.setPlatformId(refundResult.getPlatformId());
                notifySchedule.setRefundCode(refundResult.getRefundCode());
                notifySchedule.setRefundType(refundResult.getRefundType().byteValue());
                notifySchedule.setStatus(refundResult.getStatus());
                notifySchedule.setMessage(refundResult.getRefundError());
                callbackService.updatePaymentStatus(notifySchedule);
            }
        }
        return true;
    }

    @Override
    public boolean saveNotifyResult(NotifySchedule notifySchedule) {
        Date current = new Date();
        notifySchedule.setCreatedTime(current);
        notifySchedule.setUpdatedTime(current);
        notifySchedule.setNotifyCount((byte)1);
        return notifyScheduleMapper.insertSelective(notifySchedule) > 0 ? true : false;

    }

    @Override
    public EasyPaymentResponse generatePaymentRequest(Payment payment,
        PaymentRequest paymentRequest) {
        EasyPaymentResponse paymentResponse = new EasyPaymentResponse();
        //第三方支付返回状态码
        if (paymentRequest.getQueryParams().containsKey("code")) {
            paymentResponse.setCode(paymentRequest.getQueryParam("code"));
            paymentResponse.setMessage(paymentRequest.getQueryParam("message"));
            return paymentResponse;
        } else {
            paymentResponse.setCode("200");
            paymentResponse.setMessage("");
        }
        //支付宝支付处理
        if (payment.getPlatformId().intValue() == PayConstant.PlatformType.ALIPAYAPP.intValue()) {
            paymentRequest.sign();
        }
        paymentResponse.setData(paymentRequest.getQueryParams());
        return paymentResponse;
    }

    @Override
    public boolean saveNotifySchedule(NotifySchedule notifySchedule) {
        Date current = new Date();
        notifySchedule.setCreatedTime(current);
        notifySchedule.setUpdatedTime(current);
        notifySchedule.setNotifyCount((byte)1);
        return notifyScheduleMapper.insert(notifySchedule) > 0;
    }

    @Override
    public boolean updateNotifySchedule(NotifySchedule notifySchedule) {
        Date current = new Date();
        notifySchedule.setUpdatedTime(current);
        return notifyScheduleMapper.updateByPrimaryKeySelective(notifySchedule) > 0;
    }
}
