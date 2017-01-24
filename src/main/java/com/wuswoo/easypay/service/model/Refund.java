package com.wuswoo.easypay.service.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
