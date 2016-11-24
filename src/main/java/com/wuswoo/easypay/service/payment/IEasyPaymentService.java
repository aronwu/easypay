package com.wuswoo.easypay.service.payment;

import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.request.PaymentRequest;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IEasyPaymentService {

    /**
     * 发起支付接口
     * @param payment
     * @return
     * @throws EasyPayException
     */
    public PaymentRequest newPayment(Payment payment) throws EasyPayException;

    /**
     * 获取异步支付同步通知结果
     * @param request
     * @param platformId
     * @return
     * @throws EasyPayException
     */
    public PaymentResult getNotifyPaymentResult(Request request, Integer platformId) throws
        EasyPayException;

    /**
     * 获取同步支付同步通知结果
     * @param request
     * @param platformId
     * @return
     * @throws EasyPayException
     */
    public PaymentResult getPaymentResult(Request request, Integer platformId) throws
        EasyPayException;

    /**
     * 根据处理结果回复支付网关
     * @param paymentResult
     * @param resultStatus
     * @return
     */
    public String returnPaymentPlatform(PaymentResult paymentResult, Boolean resultStatus);

}
