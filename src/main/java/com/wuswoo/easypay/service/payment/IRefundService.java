package com.wuswoo.easypay.service.payment;

import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundAndResults;
import com.wuswoo.easypay.service.model.RefundResult;
import com.wuswoo.easypay.service.payment.contract.EasyPaymentResponse;
import com.wuswoo.easypay.service.request.RefundRequest;

import java.util.List;

/**
 * Created by wuxinjun on 16/9/13.
 */
public interface IRefundService {

    public EasyPaymentResponse refund(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException;

    public RefundAndResults getRefundAndResults(Request request, Integer platformId)
        throws EasyPayException;

    public String responseToPaymentPlatform(boolean success);

    public RefundRequest makeRefundRequest(Refund refund, List<RefundResult> refundResults)
        throws EasyPayException;
}
