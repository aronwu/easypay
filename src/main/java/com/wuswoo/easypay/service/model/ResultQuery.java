package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "result_query")
public class ResultQuery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 交易ID
     */
    @Column(name = "trade_id")
    private Long tradeId;

    /**
     * 交易类型 0支付 1退款
     */
    @Column(name = "trade_type")
    private Byte tradeType;

    /**
     * 平台ID
     */
    @Column(name = "platform_id")
    private Integer platformId;

    /**
     * 下次查询时间
     */
    @Column(name = "next_query_time")
    private Integer nextQueryTime;

    /**
     * 查询次数
     */
    @Column(name = "query_count")
    private Byte queryCount;

    /**
     * 查询状态 1待查询 2查询失败结束 3查询成功结束
     */
    @Column(name = "query_status")
    private Byte queryStatus;

    /**
     * 查询返回内容
     */
    @Column(name = "return_content")
    private String returnContent;

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
     * 获取交易类型 0支付 1退款
     *
     * @return trade_type - 交易类型 0支付 1退款
     */
    public Byte getTradeType() {
        return tradeType;
    }

    /**
     * 设置交易类型 0支付 1退款
     *
     * @param tradeType 交易类型 0支付 1退款
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
     * 获取下次查询时间
     *
     * @return next_query_time - 下次查询时间
     */
    public Integer getNextQueryTime() {
        return nextQueryTime;
    }

    /**
     * 设置下次查询时间
     *
     * @param nextQueryTime 下次查询时间
     */
    public void setNextQueryTime(Integer nextQueryTime) {
        this.nextQueryTime = nextQueryTime;
    }

    /**
     * 获取查询次数
     *
     * @return query_count - 查询次数
     */
    public Byte getQueryCount() {
        return queryCount;
    }

    /**
     * 设置查询次数
     *
     * @param queryCount 查询次数
     */
    public void setQueryCount(Byte queryCount) {
        this.queryCount = queryCount;
    }

    /**
     * 获取查询状态 1待查询 2查询失败结束 3查询成功结束
     *
     * @return query_status - 查询状态 1待查询 2查询失败结束 3查询成功结束
     */
    public Byte getQueryStatus() {
        return queryStatus;
    }

    /**
     * 设置查询状态 1待查询 2查询失败结束 3查询成功结束
     *
     * @param queryStatus 查询状态 1待查询 2查询失败结束 3查询成功结束
     */
    public void setQueryStatus(Byte queryStatus) {
        this.queryStatus = queryStatus;
    }

    /**
     * 获取查询返回内容
     *
     * @return return_content - 查询返回内容
     */
    public String getReturnContent() {
        return returnContent;
    }

    /**
     * 设置查询返回内容
     *
     * @param returnContent 查询返回内容
     */
    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
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