package com.wuswoo.easypay.service.payment;

import com.wuswoo.easypay.service.model.Payment;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.model.RefundResult;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IResultQueryService {

    /**
     * 查询订单支付状态
     * @param payment
     * @return
     */
    public PaymentResult queryPayment(Payment payment);

    /**
     * 查询订单退款状态
     * @param refundResult
     * @return
     */
    public RefundResult queryRefund(RefundResult refundResult);


}
