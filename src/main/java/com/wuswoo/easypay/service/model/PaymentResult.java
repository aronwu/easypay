package com.wuswoo.easypay.service.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "payment_result")
public class PaymentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 支付号
     */
    @Column(name = "payment_code")
    private String paymentCode;

    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 从第三方平台返回的支付交易号
     */
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 实际支付金额 以分为单位
     */
    @Column(name = "pay_amount")
    private Integer payAmount;

    /**
     * 第三方平台收取的支付手续费
     */
    private Integer fee;

    /**
     * 1 success
     * 2 success but sign verify fail 10 fail
     */
    private Byte status;

    /**
     * 买家支付时间
     */
    @Column(name = "payment_time")
    private Date paymentTime;

    /**
     * 支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义
     */
    @Column(name = "platform_return_code")
    private String platformReturnCode;

    /**
     * 记录校验订单是否匹配
     * 0 正常
     * 1 异常
     * 用于校验第三方返回的数据是否和我们发出去的数据保持一致
     * 如果不正确，
     */
    @Column(name = "return_check")
    private Byte returnCheck;

    /**
     * 结果通知状态 0未通知 1 已接收 2 支付确认
     */
    @Column(name = "notify_status")
    private Byte notifyStatus;

    /**
     * 成功通知时间
     */
    @Column(name = "notify_time")
    private Date notifyTime;

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
     * 从第三方返回的信息内容
     */
    @Column(name = "return_content")
    private String returnContent;

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
     * 获取支付号
     *
     * @return payment_code - 支付号
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * 设置支付号
     *
     * @param paymentCode 支付号
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * @return platform_id
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * @param platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 获取从第三方平台返回的支付交易号
     *
     * @return trade_no - 从第三方平台返回的支付交易号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置从第三方平台返回的支付交易号
     *
     * @param tradeNo 从第三方平台返回的支付交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取实际支付金额 以分为单位
     *
     * @return pay_amount - 实际支付金额 以分为单位
     */
    public Integer getPayAmount() {
        return payAmount;
    }

    /**
     * 设置实际支付金额 以分为单位
     *
     * @param payAmount 实际支付金额 以分为单位
     */
    public void setPayAmount(Integer payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取第三方平台收取的支付手续费
     *
     * @return fee - 第三方平台收取的支付手续费
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * 设置第三方平台收取的支付手续费
     *
     * @param fee 第三方平台收取的支付手续费
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * 获取1 success
     * 2 success but sign verify fail 10 fail
     *
     * @return status - 1 success
     * 2 success but sign verify fail 10 fail
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置1 success
     * 2 success but sign verify fail 10 fail
     *
     * @param status 1 success
     *               2 success but sign verify fail 10 fail
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取买家支付时间
     *
     * @return payment_time - 买家支付时间
     */
    public Date getPaymentTime() {
        return paymentTime;
    }

    /**
     * 设置买家支付时间
     *
     * @param paymentTime 买家支付时间
     */
    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 获取支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义
     *
     * @return platform_return_code - 支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义
     */
    public String getPlatformReturnCode() {
        return platformReturnCode;
    }

    /**
     * 设置支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义
     *
     * @param platformReturnCode 支付平台返回的状态码，可以去 payPlatformReturnCode表中查询具体含义
     */
    public void setPlatformReturnCode(String platformReturnCode) {
        this.platformReturnCode = platformReturnCode;
    }

    /**
     * 获取记录校验订单是否匹配
     * 0 正常
     * 1 异常
     * 用于校验第三方返回的数据是否和我们发出去的数据保持一致
     * 如果不正确，
     *
     * @return return_check - 记录校验订单是否匹配
     * 0 正常
     * 1 异常
     * 用于校验第三方返回的数据是否和我们发出去的数据保持一致
     * 如果不正确，
     */
    public Byte getReturnCheck() {
        return returnCheck;
    }

    /**
     * 设置记录校验订单是否匹配
     * 0 正常
     * 1 异常
     * 用于校验第三方返回的数据是否和我们发出去的数据保持一致
     * 如果不正确，
     *
     * @param returnCheck 记录校验订单是否匹配
     *                    0 正常
     *                    1 异常
     *                    用于校验第三方返回的数据是否和我们发出去的数据保持一致
     *                    如果不正确，
     */
    public void setReturnCheck(Byte returnCheck) {
        this.returnCheck = returnCheck;
    }

    /**
     * 获取结果通知状态 0未通知 1 已接收 2 支付确认
     *
     * @return notify_status - 结果通知状态 0未通知 1 已接收 2 支付确认
     */
    public Byte getNotifyStatus() {
        return notifyStatus;
    }

    /**
     * 设置结果通知状态 0未通知 1 已接收 2 支付确认
     *
     * @param notifyStatus 结果通知状态 0未通知 1 已接收 2 支付确认
     */
    public void setNotifyStatus(Byte notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    /**
     * 获取成功通知时间
     *
     * @return notify_time - 成功通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * 设置成功通知时间
     *
     * @param notifyTime 成功通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
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

    /**
     * 获取从第三方返回的信息内容
     *
     * @return return_content - 从第三方返回的信息内容
     */
    public String getReturnContent() {
        return returnContent;
    }

    /**
     * 设置从第三方返回的信息内容
     *
     * @param returnContent 从第三方返回的信息内容
     */
    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }
}
