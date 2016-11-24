package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "notify_schedule")
public class NotifySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 交易ID
     */
    @Column(name = "trade_id")
    private Long tradeId;

    /**
     * 交易类型 0支付pay 1退款refund
     */
    @Column(name = "trade_type")
    private Byte tradeType;

    /**
     * 平台ID
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 下次通知时间
     */
    @Column(name = "next_notify_time")
    private Date nextNotifyTime;

    /**
     * 通知次数
     */
    @Column(name = "notify_count")
    private Byte notifyCount;

    /**
     * 通知状态 0未通知 1通知失败结束 2通知成功结束
     */
    @Column(name = "notify_status")
    private Byte notifyStatus;

    /**
     * 通知内容
     */
    @Column(name = "notify_content")
    private String notifyContent;

    @Column(name = "payment_code")
    private String paymentCode;

    private Byte status;

    /**
     * 退款类(1：全部退款；2部分退款---返现)
     */
    @Column(name = "refund_type")
    private Byte refundType;

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

    @Column(name = "notify_error")
    private String notifyError;

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
     * 获取交易ID
     *
     * @return trade_id - 交易ID
     */
    public Long getTradeId() {
        return tradeId;
    }

    /**
     * 设置交易ID
     *
     * @param tradeId 交易ID
     */
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取交易类型 0支付pay 1退款refund
     *
     * @return trade_type - 交易类型 0支付pay 1退款refund
     */
    public Byte getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型 0支付pay 1退款refund
     *
     * @param tradeType 交易类型 0支付pay 1退款refund
     */
    public void setTradeType(Byte tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * 获取平台ID
     *
     * @return platform_id - 平台ID
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置平台ID
     *
     * @param platformId 平台ID
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 获取下次通知时间
     *
     * @return next_notify_time - 下次通知时间
     */
    public Date getNextNotifyTime() {
        return nextNotifyTime;
    }

    /**
     * 设置下次通知时间
     *
     * @param nextNotifyTime 下次通知时间
     */
    public void setNextNotifyTime(Date nextNotifyTime) {
        this.nextNotifyTime = nextNotifyTime;
    }

    /**
     * 获取通知次数
     *
     * @return notify_count - 通知次数
     */
    public Byte getNotifyCount() {
        return notifyCount;
    }

    /**
     * 设置通知次数
     *
     * @param notifyCount 通知次数
     */
    public void setNotifyCount(Byte notifyCount) {
        this.notifyCount = notifyCount;
    }

    /**
     * 获取通知状态 0未通知 1通知失败结束 2通知成功结束
     *
     * @return notify_status - 通知状态 0未通知 1通知失败结束 2通知成功结束
     */
    public Byte getNotifyStatus() {
        return notifyStatus;
    }

    /**
     * 设置通知状态 0未通知 1通知失败结束 2通知成功结束
     *
     * @param notifyStatus 通知状态 0未通知 1通知失败结束 2通知成功结束
     */
    public void setNotifyStatus(Byte notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    /**
     * 获取通知内容
     *
     * @return notify_content - 通知内容
     */
    public String getNotifyContent() {
        return notifyContent;
    }

    /**
     * 设置通知内容
     *
     * @param notifyContent 通知内容
     */
    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }

    /**
     * @return payment_code
     */
    public String getPaymentCode() {
        return paymentCode;
    }

    /**
     * @param paymentCode
     */
    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取退款类(1：全部退款；2部分退款---返现)
     *
     * @return refund_type - 退款类(1：全部退款；2部分退款---返现)
     */
    public Byte getRefundType() {
        return refundType;
    }

    /**
     * 设置退款类(1：全部退款；2部分退款---返现)
     *
     * @param refundType 退款类(1：全部退款；2部分退款---返现)
     */
    public void setRefundType(Byte refundType) {
        this.refundType = refundType;
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
     * @return notify_error
     */
    public String getNotifyError() {
        return notifyError;
    }

    /**
     * @param notifyError
     */
    public void setNotifyError(String notifyError) {
        this.notifyError = notifyError;
    }
}