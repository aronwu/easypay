package com.wuswoo.easypay.service.payment;

import com.wuswoo.easypay.http.server.Request;
import com.wuswoo.easypay.service.exception.EasyPayException;
import com.wuswoo.easypay.service.model.PaymentResult;
import com.wuswoo.easypay.service.request.BaseRequest;

/**
 * Created by wuxinjun on 16/10/8.
 */
public abstract class AbstractPaymentService implements IEasyPaymentService {
    protected BaseRequest paymentRequest;
    private IVerifySignature verifySignature;

    public AbstractPaymentService(BaseRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    public void setVerifySignature(IVerifySignature verifySignature) {
        this.verifySignature = verifySignature;
    }

    public IVerifySignature getVerifySignature() {
        return verifySignature;
    }

    public BaseRequest getPaymentRequest() {
        return paymentRequest;
    }

    public void setPaymentRequest(BaseRequest paymentRequest) {
        this.paymentRequest = paymentRequest;
    }

    @Override
    public PaymentResult getNotifyPaymentResult(Request request, Integer platformId)
        throws EasyPayException {
        if (getVerifySignature() != null && !getVerifySignature().verify(request) ) {
            throw new EasyPayException("payment result signature verify is failed for platformId:" + platformId);
        }

        return makePaymentResult(request, platformId);
    }

    protected abstract PaymentResult makePaymentResult(Request request, Integer platformId) throws EasyPayException;
}
