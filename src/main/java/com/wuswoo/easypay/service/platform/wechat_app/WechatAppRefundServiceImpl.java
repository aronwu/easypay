package com.wuswoo.easypay.service.platform.wechat_app;

import com.alibaba.fastjson.JSONObject;
import com.wuswoo.easypay.common.util.ThreadPoolUtil;
import com.wuswoo.easypay.common.util.XMLUtil;
import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.callback.ICallbackService;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundAndResults;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.payment.AbstractRefundService;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentResponse;
import com.wuswoo.easypay.service.platform.wechat.util.WechatAppUtil;
import com.wuswoo.easypay.service.repository.IPaymentDBService;
import com.wuswoo.easypay.service.repository.IRefundDBService;
import com.wuswoo.easypay.service.repository.IResultQueryDBService;
import com.wuswoo.easypay.service.request.RefundRequest;
import com.wuswoo.easypay.service.task.NotifyRefundResultsTask;
import com.wuswoo.easypay.service.util.PayConstant;
import com.wuswoo.easypay.service.util.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.SecapiPayRefund;
import weixin.popular.bean.paymch.SecapiPayRefundResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxinjun on 16/11/11.
 */
public class WechatAppRefundServiceImpl extends AbstractRefundService {

    private final static Logger logger = LogManager.getLogger(WechatAppRefundServiceImpl.class);
    @Autowired
    private IPaymentDBService paymentDBService;

    @Autowired
    private IRefundDBService refundDBService;

    @Autowired
    @Qualifier("callbackServiceFactory")
    private ICallbackService callbackService;

    @Autowired
    private IResultQueryDBService resultQueryDBService;

    public WechatAppRefundServiceImpl(RefundRequest refundRequest) {
        super(refundRequest);
    }

    @Override
    public EasyPaymentResponse refund(Refund refund, List<RefundResult> refundResults) throws EasyPayException {
        EasyPaymentResponse response = new EasyPaymentResponse();
        List<RefundResult> lstNotifies = new ArrayList<RefundResult>(refundResults.size());
        refund = refundDBService.insertRefund(refund, refundResults);
        int successes = 0;
        for(RefundResult refundResult : refundResults) {
            PaymentResult paymentResult = paymentDBService.getPaymentResult(refundResult.getPaymentCode());
            SecapiPayRefund wechatRefund = new SecapiPayRefund();
            wechatRefund.setAppid(this.refundRequest.getQueryParam("appid"));
            wechatRefund.setMch_id(this.refundRequest.getQueryParam("mch_id"));
            wechatRefund.setNonce_str(RandomStringUtils.randomStringByLength(30));
            wechatRefund.setTransaction_id(paymentResult.getTradeNo());
            wechatRefund.setOut_refund_no(refundResult.getRefundCode());
            wechatRefund.setTotal_fee(paymentResult.getPayAmount());
            wechatRefund.setRefund_fee(refundResult.getAmount());
            wechatRefund.setOp_user_id(this.refundRequest.getQueryParam("op_user_id"));
            logger.info("Wechat Refund: {0}", JSONObject.toJSONString(wechatRefund, true));
            SecapiPayRefundResult wechatResult = null;
            try {
                wechatResult = PayMchAPI.secapiPayRefund(wechatRefund, this.refundRequest.getQueryParam("paterner_key"));
            } catch (Exception e) {
                logger.error("微信退款请求发送失败", e);
                refundResult.setRefundError("微信退款请求发送失败");
                refundResult.setStatus(PayConstant.RefundStatus.REFUND_FAILED.byteValue());
                lstNotifies.add(refundResult);
                continue;
            }
            if (wechatResult != null && "SUCCESS".equalsIgnoreCase(wechatResult.getReturn_code())) {
                //退款请求成功
                //校验签名


                if(!WechatAppUtil.validateSecapiPayRefundResultSign(wechatResult, this.refundRequest.getQueryParam("paterner_key"))) {
                    logger.error("微信退款请求签名失败 :{0}", wechatResult.getSign());
                    refundResult.setRefundError("微信退款请求签名失败");
                    refundResult.setStatus(PayConstant.RefundStatus.REFUND_FAILED.byteValue());
                    lstNotifies.add(refundResult);
                    continue;

                }
                refundResult.setStatus(
                    "SUCCESS".equalsIgnoreCase(wechatResult.getResult_code()) ?
                        PayConstant.RefundStatus.REFUND_IN_PROCESS.byteValue() :
                        PayConstant.RefundStatus.REFUND_FAILED.byteValue()
                );
                refundResult.setReturnCode(wechatResult.getReturn_code());
                refundResult.setRefundTradeNo(wechatResult.getRefund_id());
                refundResult.setRefundAmount(wechatResult.getRefund_fee());
                refundResult.setRefundChannel(wechatResult.getRefund_channel());
                if (!"SUCCESS".equalsIgnoreCase(wechatResult.getResult_code())) {
                    refundResult.setRefundError(wechatResult.getErr_code_des());
                }
                try {
                    refundResult.setReturnContent(XMLUtil.convertToXML(wechatResult));
                } catch(Exception e) {
                    refundResult.setReturnContent(JSONObject.toJSONString(wechatResult, true));
                }
                logger.info("refund result: {}", JSONObject.toJSONString(refundResult));
                refundDBService.updateRefundResult(refundResult);
                if ("SUCCESS".equalsIgnoreCase(wechatResult.getResult_code())) {
                    successes ++;
                    resultQueryDBService.createResultQuery(PayConstant.PlatformType.WEIXINWAP.intValue(),
                        PayConstant.NotifyResultType.REFUND.byteValue(),
                        refundResult.getId()
                        );
                }
                //TODO
                //交易余额不足，使用微信余额退款
                lstNotifies.add(refundResult);
            } else {
                String msg = String.format("发送微信退款请求失败code:%s,msg:%s", wechatResult.getReturn_code()
                    ,wechatResult.getReturn_msg());
                refundResult.setRefundError(msg);
                refundResult.setReturnCode(wechatResult.getReturn_code());
                refundResult.setStatus(PayConstant.RefundStatus.REFUND_FAILED.byteValue());
                try {
                    refundResult.setReturnContent(XMLUtil.convertToXML(wechatResult));
                } catch(Exception e) {
                    refundResult.setReturnContent(JSONObject.toJSONString(wechatResult, true));
                }
                logger.info("error refund result: {}", JSONObject.toJSONString(refundResult));
                refundDBService.updateRefundResult(refundResult);
                lstNotifies.add(refundResult);

            }
        }
        refund.setSuccessNum((short)successes);
        refundDBService.updateRefund(refund);
        response.setData(refund);
        //提醒业务系统退款请求结果
        notifyRefundResults(lstNotifies);
        return response;
    }

    private void notifyRefundResults(List<RefundResult> lstNotifies) {
        NotifyRefundResultsTask task = new NotifyRefundResultsTask(callbackService, lstNotifies);
        ThreadPoolUtil.getExecutor().execute(task);
    }

    @Override
    public String responseToPaymentPlatform(boolean success) {
        return success ? "<xml>"
            + "<return_code><![CDATA[SUCCESS]]></return_code>"
            + "<return_msg><![CDATA[OK]]></return_msg>"
            + "</xml>"
            : "<xml>"
            + "<return_code><![CDATA[FAIL]]></return_code>"
            + "<return_msg><![CDATA[更新失败]]></return_msg>"
            + "</xml>";
    }

    @Override
    public RefundRequest makeRefundRequest(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException {
        return null;
    }

    @Override
    protected RefundAndResults parseRefundAndResultsFromRequest(Request request,
        Integer platformId) {
        return null;
    }

    @Override
    protected Refund parseRefundFromContent(String content) throws EasyPayException {
        return null;
    }
}
