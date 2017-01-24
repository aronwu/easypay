package com.wuswoo.easypay.service.payment.contract;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by wuxinjun on 16/7/12.
 */
public class EasyPaymentRequest {
    /**
     * 支付平台
     */
    @NotNull(message = "支付平台不能为空")
    @Min(value = 1)
    @Max(value = 4)
    private Integer platformId;

    /**
     * 支付单号,用订单编号
     */
    @NotNull(message = "支付订单编号不能为空")
    @Size(min = 1, max = 64, message = "订单编号长度为不能超过64个字符")
    private String paymentCode;

    /**
     * 支付金额
     * 单位为分
     */
    @NotNull(message = "支付金额不能为空")
    @Min(value = 1, message = "amount必须大于0")
    private Integer amount;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    @Size(min = 1, max = 255, message = "商品名称长度不能超过255个字符")
    private String itemName;

    /**
     * 商品数量
     */
    private Integer itemCount;

    @NotNull(message = "商品描述不能为空")
    @Size(min = 1, max = 500, message = "商品名称长度不能超过500个字符")
    private String itemDesc;

    /**
     * 下订用户ID 无传0
     */
    @NotNull(message = "userId不能为空")
    private Long userId;

    /**
     * 下订订单编号 无传0
     */
    @NotNull(message = "orderId不能为空")
    private Long orderId;

    /**
     * 客户端IP地址
     */
    private String appIp;

    /**
     * 微信公共号支付openid
     */
    private String openId;


    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getAppIp() {
        return appIp;
    }

    public void setAppIp(String appIp) {
        this.appIp = appIp;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}


