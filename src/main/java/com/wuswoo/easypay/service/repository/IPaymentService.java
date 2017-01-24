package com.wuswoo.easypay.service.repository;

import com.wuswoo.easypay.service.model.*;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentResponse;
import com.wuswoo.easypay.service.request.PaymentRequest;

import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IPaymentService {

    /**
     * 保存支付结果
     *
     * @param paymentResult
     * @param ignoreNotify
     * @return
     */
    public boolean savePaymentResult(PaymentResult paymentResult, boolean ignoreNotify);

    /**
     * 保存退款结果
     *
     * @param refund
     * @param refundResults
     * @return
     */
    public boolean saveRefundAndResults(Refund refund, List<RefundResult> refundResults);

    /**
     * 保存业务回调结果
     *
     * @param notifySchedule
     * @return
     */
    public boolean saveNotifyResult(NotifySchedule notifySchedule);

    /**
     * 获取支付凭证
     *
     * @param payment
     * @param paymentRequest
     * @return
     */
    public EasyPaymentResponse generatePaymentRequest(Payment payment,
        PaymentRequest paymentRequest);

    /**
     * 保存业务系统通知记录
     *
     * @param notifySchedule
     * @return
     */
    public boolean saveNotifySchedule(NotifySchedule notifySchedule);

    /**
     * 更新业务系统通知记录
     *
     * @param notifySchedule
     * @return
     */
    public boolean updateNotifySchedule(NotifySchedule notifySchedule);
}
