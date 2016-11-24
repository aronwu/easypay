package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 发起退款请求的应用ID
     */
    @Column(name = "app_id")
    private Integer appId;

    /**
     * 支付平台id 1微信APP 2支付宝APP
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 退款批次号
     */
    @Column(name = "refund_batch_code")
    private String refundBatchCode;

    /**
     * 支付平台返回的支付结果交易号
     */
    @Column(name = "refund_trade_no")
    private String refundTradeNo;

    /**
     * 退款状态 (0:"未退款",1:"退款中",2:"退款失败",3:"退款成功")
     */
    private Byte status;

    /**
     * 第三方平台的错误代码
     */
    @Column(name = "return_code")
    private String returnCode;

    /**
     * 第三方平台返回的原始结果
     */
    @Column(name = "return_content")
    private String returnContent;

    /**
     * 通知时间
     */
    @Column(name = "notify_time")
    private Date notifyTime;

    /**
     * 结果通知状态 0未通知 1 已接收 2 退款成功确认
     */
    @Column(name = "notify_status")
    private Byte notifyStatus;

    /**
     * 成功退款笔数
     */
    @Column(name = "success_num")
    private Short successNum;

    /**
     * 批次退款笔数
     */
    @Column(name = "batch_num")
    private Short batchNum;

    /**
     * 执行退款操作者user_id
     */
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "order_id")
    private Integer orderId;

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
     * 获取发起退款请求的应用ID
     *
     * @return app_id - 发起退款请求的应用ID
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 设置发起退款请求的应用ID
     *
     * @param appId 发起退款请求的应用ID
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 获取支付平台id 1微信APP 2支付宝APP
     *
     * @return platform_id - 支付平台id 1微信APP 2支付宝APP
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置支付平台id 1微信APP 2支付宝APP
     *
     * @param platformId 支付平台id 1微信APP 2支付宝APP
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 获取退款批次号
     *
     * @return refund_batch_code - 退款批次号
     */
    public String getRefundBatchCode() {
        return refundBatchCode;
    }

    /**
     * 设置退款批次号
     *
     * @param refundBatchCode 退款批次号
     */
    public void setRefundBatchCode(String refundBatchCode) {
        this.refundBatchCode = refundBatchCode;
    }

    /**
     * 获取支付平台返回的支付结果交易号
     *
     * @return refund_trade_no - 支付平台返回的支付结果交易号
     */
    public String getRefundTradeNo() {
        return refundTradeNo;
    }

    /**
     * 设置支付平台返回的支付结果交易号
     *
     * @param refundTradeNo 支付平台返回的支付结果交易号
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
     * 获取第三方平台的错误代码
     *
     * @return return_code - 第三方平台的错误代码
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * 设置第三方平台的错误代码
     *
     * @param returnCode 第三方平台的错误代码
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
     * 获取通知时间
     *
     * @return notify_time - 通知时间
     */
    public Date getNotifyTime() {
        return notifyTime;
    }

    /**
     * 设置通知时间
     *
     * @param notifyTime 通知时间
     */
    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    /**
     * 获取结果通知状态 0未通知 1 已接收 2 退款成功确认
     *
     * @return notify_status - 结果通知状态 0未通知 1 已接收 2 退款成功确认
     */
    public Byte getNotifyStatus() {
        return notifyStatus;
    }

    /**
     * 设置结果通知状态 0未通知 1 已接收 2 退款成功确认
     *
     * @param notifyStatus 结果通知状态 0未通知 1 已接收 2 退款成功确认
     */
    public void setNotifyStatus(Byte notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    /**
     * 获取成功退款笔数
     *
     * @return success_num - 成功退款笔数
     */
    public Short getSuccessNum() {
        return successNum;
    }

    /**
     * 设置成功退款笔数
     *
     * @param successNum 成功退款笔数
     */
    public void setSuccessNum(Short successNum) {
        this.successNum = successNum;
    }

    /**
     * 获取批次退款笔数
     *
     * @return batch_num - 批次退款笔数
     */
    public Short getBatchNum() {
        return batchNum;
    }

    /**
     * 设置批次退款笔数
     *
     * @param batchNum 批次退款笔数
     */
    public void setBatchNum(Short batchNum) {
        this.batchNum = batchNum;
    }

    /**
     * 获取执行退款操作者user_id
     *
     * @return user_id - 执行退款操作者user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置执行退款操作者user_id
     *
     * @param userId 执行退款操作者user_id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
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