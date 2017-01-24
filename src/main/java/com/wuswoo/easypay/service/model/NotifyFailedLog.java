package com.wuswoo.easypay.service.model;

import javax.persistence.*;
import java.util.Date;

@Table(name = "notify_failed_log")
public class NotifyFailedLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * payment或者refund ID
     */
    @Column(name = "flow_id")
    private Integer flowId;

    /**
     * 返回结果类型 1支付结果 2退款结果
     */
    @Column(name = "result_type")
    private Byte resultType;

    /**
     * 已通知次数
     */
    @Column(name = "notify_count")
    private Byte notifyCount;

    /**
     * 最后一次通知时间
     */
    @Column(name = "last_notify_time")
    private Date lastNotifyTime;

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
     * 获取payment或者refund ID
     *
     * @return flow_id - payment或者refund ID
     */
    public Integer getFlowId() {
        return flowId;
    }

    /**
     * 设置payment或者refund ID
     *
     * @param flowId payment或者refund ID
     */
    public void setFlowId(Integer flowId) {
        this.flowId = flowId;
    }

    /**
     * 获取返回结果类型 1支付结果 2退款结果
     *
     * @return result_type - 返回结果类型 1支付结果 2退款结果
     */
    public Byte getResultType() {
        return resultType;
    }

    /**
     * 设置返回结果类型 1支付结果 2退款结果
     *
     * @param resultType 返回结果类型 1支付结果 2退款结果
     */
    public void setResultType(Byte resultType) {
        this.resultType = resultType;
    }

    /**
     * 获取已通知次数
     *
     * @return notify_count - 已通知次数
     */
    public Byte getNotifyCount() {
        return notifyCount;
    }

    /**
     * 设置已通知次数
     *
     * @param notifyCount 已通知次数
     */
    public void setNotifyCount(Byte notifyCount) {
        this.notifyCount = notifyCount;
    }

    /**
     * 获取最后一次通知时间
     *
     * @return last_notify_time - 最后一次通知时间
     */
    public Date getLastNotifyTime() {
        return lastNotifyTime;
    }

    /**
     * 设置最后一次通知时间
     *
     * @param lastNotifyTime 最后一次通知时间
     */
    public void setLastNotifyTime(Date lastNotifyTime) {
        this.lastNotifyTime = lastNotifyTime;
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
