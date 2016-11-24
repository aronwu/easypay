package com.wuswoo.easypay.service.payment.contract;


import com.wuswoo.easypay.service.model.RefundResult;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by wuxinjun on 16/11/14.
 */
public class EasyRefundRequest {
    @NotNull(message = "appId不能为空")
    private Integer appId;

    @NotNull(message = "userId不能为空")
    private Long userId;

    /**
     * 退款RefundResult
     * 需要上传三个参数refundCode, amount, paymentCode
     */
    @Valid()
    @NotNull(message = "batchRefunds不能为空")
    private List<RefundResult> batchRefunds;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<RefundResult> getBatchRefunds() {
        return batchRefunds;
    }

    public void setBatchRefunds(List<RefundResult> batchRefunds) {
        this.batchRefunds = batchRefunds;
    }
}
