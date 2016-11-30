package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "refund_result")
public class RefundResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 退款单id
     */
    @Column(name = "refund_id")
    private Long refundId;

    /**
     * 支付平台id
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 支付单号
     */
    @Column(name = "payment_code")
    private String paymentCode;

    /**
     * 支付平台返回的支付结果交易号
     */
    @Column(name = "trade_no")
    private String tradeNo;

    /**
     * 退款单号
     */
    @Column(name = "refund_code")
    private String refundCode;

    /**
     * 支付平台返回的退款交易号
     */
    @Column(name = "refund_trade_no")
    private String refundTradeNo;

    /**
     * 退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     */
    private Byte status;

    /**
     * 申请退款金额 以分为单位
     */
    private Integer amount;

    /**
     * 实际退款金额 以分为单位
     */
    @Column(name = "refund_amount")
    private Integer refundAmount;

    /**
     * 退款渠道
     */
    @Column(name = "refund_channel")
    private String refundChannel;

    /**
     * 第三方平台返还的手续费 以分为单位
     */
    private Integer fee;

    /**
     * 退款完成时间
     */
    @Column(name = "refund_time")
    private Date refundTime;

    /**
     * 第三方的状态代码
     */
    @Column(name = "return_code")
    private String returnCode;

    /**
     * 第三方平台返回的原始结果
     */
    @Column(name = "return_content")
    private String returnContent;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 订单系统退款流水号
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 退款类型(1：全部退款；2部分退款---返现)
     */
    @Column(name = "refund_type")
    private Integer refundType;

    @Column(name = "refund_error")
    private String refundError;

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
     * 获取退款单id
     *
     * @return refund_id - 退款单id
     */
    public Long getRefundId() {
        return refundId;
    }

    /**
     * 设置退款单id
     *
     * @param refundId 退款单id
     */
    public void setRefundId(Long refundId) {
        this.refundId = refundId;
    }

    /**
     * 获取支付平台id
     *
     * @return platform_id - 支付平台id
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置支付平台id
     *
     * @param platformId 支付平台id
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
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
     * 获取支付平台返回的支付结果交易号
     *
     * @return trade_no - 支付平台返回的支付结果交易号
     */
    public String getTradeNo() {
        return tradeNo;
    }

    /**
     * 设置支付平台返回的支付结果交易号
     *
     * @param tradeNo 支付平台返回的支付结果交易号
     */
    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    /**
     * 获取退款单号
     *
     * @return refund_code - 退款单号
     */
    public String getRefundCode() {
        return refundCode;
    }

    /**
     * 设置退款单号
     *
     * @param refundCode 退款单号
     */
    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    /**
     * 获取支付平台返回的退款交易号
     *
     * @return refund_trade_no - 支付平台返回的退款交易号
     */
    public String getRefundTradeNo() {
        return refundTradeNo;
    }

    /**
     * 设置支付平台返回的退款交易号
     *
     * @param refundTradeNo 支付平台返回的退款交易号
     */
    public void setRefundTradeNo(String refundTradeNo) {
        this.refundTradeNo = refundTradeNo;
    }

    /**
     * 获取退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     *
     * @return status - 退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     *
     * @param status 退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取申请退款金额 以分为单位
     *
     * @return amount - 申请退款金额 以分为单位
     */
    public Integer getAmount() {
        return amount;
    }

    /**
     * 设置申请退款金额 以分为单位
     *
     * @param amount 申请退款金额 以分为单位
     */
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 获取实际退款金额 以分为单位
     *
     * @return refund_amount - 实际退款金额 以分为单位
     */
    public Integer getRefundAmount() {
        return refundAmount;
    }

    /**
     * 设置实际退款金额 以分为单位
     *
     * @param refundAmount 实际退款金额 以分为单位
     */
    public void setRefundAmount(Integer refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 获取退款渠道
     *
     * @return refund_channel - 退款渠道
     */
    public String getRefundChannel() {
        return refundChannel;
    }

    /**
     * 设置退款渠道
     *
     * @param refundChannel 退款渠道
     */
    public void setRefundChannel(String refundChannel) {
        this.refundChannel = refundChannel;
    }

    /**
     * 获取第三方平台返还的手续费 以分为单位
     *
     * @return fee - 第三方平台返还的手续费 以分为单位
     */
    public Integer getFee() {
        return fee;
    }

    /**
     * 设置第三方平台返还的手续费 以分为单位
     *
     * @param fee 第三方平台返还的手续费 以分为单位
     */
    public void setFee(Integer fee) {
        this.fee = fee;
    }

    /**
     * 获取退款完成时间
     *
     * @return refund_time - 退款完成时间
     */
    public Date getRefundTime() {
        return refundTime;
    }

    /**
     * 设置退款完成时间
     *
     * @param refundTime 退款完成时间
     */
    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    /**
     * 获取第三方的状态代码
     *
     * @return return_code - 第三方的状态代码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设置第三方的状态代码
     *
     * @param returnCode 第三方的状态代码
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * 获取第三方平台返回的原始结果
     *
     * @return return_content - 第三方平台返回的原始结果
     */
    public String getReturnContent() {
        return returnContent;
    }

    /**
     * 设置第三方平台返回的原始结果
     *
     * @param returnContent 第三方平台返回的原始结果
     */
    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取订单系统退款流水号
     *
     * @return order_id - 订单系统退款流水号
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 设置订单系统退款流水号
     *
     * @param orderId 订单系统退款流水号
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取退款类型(1：全部退款；2部分退款---返现)
     *
     * @return refund_type - 退款类型(1：全部退款；2部分退款---返现)
     */
    public Integer getRefundType() {
        return refundType;
    }

    /**
     * 设置退款类型(1：全部退款；2部分退款---返现)
     *
     * @param refundType 退款类型(1：全部退款；2部分退款---返现)
     */
    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    /**
     * @return refund_error
     */
    public String getRefundError() {
        return refundError;
    }

    /**
     * @param refundError
     */
    public void setRefundError(String refundError) {
        this.refundError = refundError;
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