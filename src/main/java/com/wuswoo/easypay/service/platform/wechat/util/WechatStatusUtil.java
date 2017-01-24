package com.wuswoo.easypay.service.platform.wechat.util;


import com.wuswoo.easypay.service.util.PayConstant.PaymentStatus;
import com.wuswoo.easypay.service.util.PayConstant.RefundStatus;

public class WechatStatusUtil {

    public static Byte parsePaymentStatus(String tradeStatus) {
        if ("SUCCESS".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.SUCCESS.byteValue();
        } else if ("NOTPAY".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.NOTPAY.byteValue();
        } else if ("USERPAYING".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.USERPAYING.byteValue();
        } else if ("FAIL".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.FAIL.byteValue();
        } else if ("PAYERROR".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.PAYERROR.byteValue();
        } else if ("REFUND".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.REFUND.byteValue();
        } else if ("CLOSED".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.CLOSED.byteValue();
        } else if ("REVOKED".equalsIgnoreCase(tradeStatus)) {
            return PaymentStatus.REVOKED.byteValue();
        } else {
            return PaymentStatus.PAYERROR.byteValue();
        }
    }

    public static Byte parseRefundStatus(String refundStatus) {
        if ("SUCCESS".equalsIgnoreCase(refundStatus)) {
            return RefundStatus.REFUND_SUCCESS.byteValue();
        } else if ("PROCESSING".equalsIgnoreCase(refundStatus)) {
            return RefundStatus.REFUND_IN_PROCESS.byteValue();
        } else {
            return RefundStatus.REFUND_FAILED.byteValue();
        }
    }
}
