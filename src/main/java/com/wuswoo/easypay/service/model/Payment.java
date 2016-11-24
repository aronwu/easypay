package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付单号
     */
    @Column(name = "payment_code")
    private String paymentCode;

    /**
     * 第三方平台ID
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 请求支付的应用ID
     */
    @Column(name = "app_id")
    private Integer appId;

    /**
     * 第三方平台手续费比例
     */
    @Column(name = "service_fee_rate")
    private Integer serviceFeeRate;

    /**
     * 在线支付金额

     */
    private Integer amount;

    /**
     * 支付银行
     */
    private String bank;

    /**
     * 商品名称
     */
    @Column(name = "item_name")
    private String itemName;

    /**
     * 商品数量
     */
    @Column(name = "item_count")
    private Integer itemCount;

    /**
     * 商品描述
     */
    @Column(name = "item_desc")
    private String itemDesc;

    /**
     * 支付结果异步通知URL
     */
    @Column(name = "notify_url")
    private String notifyUrl;

    /**
     * 支付结果同步通知URL
     */
    @Column(name = "return_url")
    private String returnUrl;

    private Integer type;

    /**
     * 支付状态 0等待支付 1成功 10失败
     */
    private Byte status;

    /**
     * 下订用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 进行支付的买家IP
     */
    @Column(name = "client_ip")
    private String clientIp;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "open_id")
    private String openId;

    private String attach;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @Column(name = "updated_time")
    private Date updatedTime;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取支付单号
     *
     * @return payment_code - 支付单号
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * 设置支付单号
     *
     * @param paymentCode 支付单号
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * 获取第三方平台ID
     *
     * @return platform_id - 第三方平台ID
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置第三方平台ID
     *
     * @param platformId 第三方平台ID
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 获取请求支付的应用ID
     *
     * @return app_id - 请求支付的应用ID
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置请求支付的应用ID
     *
     * @param appId 请求支付的应用ID
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取第三方平台手续费比例
     *
     * @return service_fee_rate - 第三方平台手续费比例
     */
    public Integer getServiceFeeRate() {
        return serviceFeeRate;
    }

    /**
     * 设置第三方平台手续费比例
     *
     * @param serviceFeeRate 第三方平台手续费比例
     */
    public void setServiceFeeRate(Integer serviceFeeRate) {
        this.serviceFeeRate = serviceFeeRate;
    }

    /**
     * 获取在线支付金额

     *
     * @return amount - 在线支付金额

     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置在线支付金额

     *
     * @param amount 在线支付金额

     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取支付银行
     *
     * @return bank - 支付银行
     */
    public String getBank() {
        return bank;
    }

    /**
     * 设置支付银行
     *
     * @param bank 支付银行
     */
    public void setBank(String bank) {
        this.bank = bank;
    }

    /**
     * 获取商品名称
     *
     * @return item_name - 商品名称
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * 设置商品名称
     *
     * @param itemName 商品名称
     */
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    /**
     * 获取商品数量
     *
     * @return item_count - 商品数量
     */
    public Integer getItemCount() {
        return itemCount;
    }

    /**
     * 设置商品数量
     *
     * @param itemCount 商品数量
     */
    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    /**
     * 获取商品描述
     *
     * @return item_desc - 商品描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 设置商品描述
     *
     * @param itemDesc 商品描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    /**
     * 获取支付结果异步通知URL
     *
     * @return notify_url - 支付结果异步通知URL
     */
    public String getNotifyUrl() {
        return notifyUrl;
    }

    /**
     * 设置支付结果异步通知URL
     *
     * @param notifyUrl 支付结果异步通知URL
     */
    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    /**
     * 获取支付结果同步通知URL
     *
     * @return return_url - 支付结果同步通知URL
     */
    public String getReturnUrl() {
        return returnUrl;
    }

    /**
     * 设置支付结果同步通知URL
     *
     * @param returnUrl 支付结果同步通知URL
     */
    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    /**
     * @return type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取支付状态 0等待支付 1成功 10失败
     *
     * @return status - 支付状态 0等待支付 1成功 10失败
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置支付状态 0等待支付 1成功 10失败
     *
     * @param status 支付状态 0等待支付 1成功 10失败
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取下订用户ID
     *
     * @return user_id - 下订用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置下订用户ID
     *
     * @param userId 下订用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取进行支付的买家IP
     *
     * @return client_ip - 进行支付的买家IP
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * 设置进行支付的买家IP
     *
     * @param clientIp 进行支付的买家IP
     */
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return attach
     */
    public String getAttach() {
        return attach;
    }

    /**
     * @param attach
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新时间
     *
     * @return updated_time - 更新时间
     */
    public Date getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 设置更新时间
     *
     * @param updatedTime 更新时间
     */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}