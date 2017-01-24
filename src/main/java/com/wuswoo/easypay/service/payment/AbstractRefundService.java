package com.wuswoo.easypay.service.payment;

import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.Refund;
import com.wuswoo.easypay.service.model.RefundAndResults;
import com.wuswoo.easypay.service.request.RefundRequest;

/**
 * Created by wuxinjun on 16/10/13.
 */
public abstract class AbstractRefundService implements IRefundService {

    protected RefundRequest refundRequest;

    private IVerifySignature verifySignature;

    public AbstractRefundService(RefundRequest refundRequest) {
        this.refundRequest = refundRequest;
    }

    public IVerifySignature getVerifySignature() {
        return verifySignature;
    }

    public void setVerifySignature(IVerifySignature verifySignature) {
        this.verifySignature = verifySignature;
    }

    @Override
    public RefundAndResults getRefundAndResults(Request request, Integer platformId)
        throws EasyPayException {
        if (getVerifySignature() != null && !getVerifySignature().verify(request)) {
            throw new EasyPayException(
                "refund result signature verify is failed for platformId:" + platformId);
        }
        return parseRefundAndResultsFromRequest(request, platformId);
    }

    protected abstract RefundAndResults parseRefundAndResultsFromRequest(Request request,
        Integer platformId);

    protected abstract Refund parseRefundFromContent(String content) throws EasyPayException;

}
