package com.wuswoo.easypay.service.model;

import java.util.List;

/**
 * Created by wuxinjun on 16/11/21.
 */
public class RefundAndResults {

    private Refund refund;
    private List<RefundResult> refundResults;

    public RefundAndResults(Refund refund, List<RefundResult> refundResults) {
        this.refund = refund;
        this.refundResults = refundResults;
    }

    public Refund getRefund() {
        return refund;
    }

    public void setRefund(Refund refund) {
        this.refund = refund;
    }

    public List<RefundResult> getRefundResults() {
        return refundResults;
    }

    public void setRefundResults(List<RefundResult> refundResults) {
        this.refundResults = refundResults;
    }
}
