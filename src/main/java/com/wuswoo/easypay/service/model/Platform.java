package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

public class Platform {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    /**
     * 服务名称
     */
    private String service;

    /**
     * 备注
     */
    private String comments;

    /**
     * 异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数
     */
    @Column(name = "ansync_wait_sec")
    private Integer ansyncWaitSec;

    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    /**
     * 是否有效 0-有效 1无效
     */
    @Column(name = "is_active")
    private Integer isActive;

    /**
     * 支付渠道 1-线上 2-线下
     */
    private Integer type;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取服务名称
     *
     * @return service - 服务名称
     */
    public String getService() {
        return service;
    }

    /**
     * 设置服务名称
     *
     * @param service 服务名称
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * 获取备注
     *
     * @return comments - 备注
     */
    public String getComments() {
        return comments;
    }

    /**
     * 设置备注
     *
     * @param comments 备注
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * 获取异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数
     *
     * @return ansync_wait_sec - 异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数
     */
    public Integer getAnsyncWaitSec() {
        return ansyncWaitSec;
    }

    /**
     * 设置异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数
     *
     * @param ansyncWaitSec 异步等待秒数
当第三方提供异步返回时，需要设置异步等待秒数
     */
    public void setAnsyncWaitSec(Integer ansyncWaitSec) {
        this.ansyncWaitSec = ansyncWaitSec;
    }

    /**
     * @return last_modify_time
     */
    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    /**
     * @param lastModifyTime
     */
    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    /**
     * 获取是否有效 0-有效 1无效
     *
     * @return is_active - 是否有效 0-有效 1无效
     */
    public Integer getIsActive() {
        return isActive;
    }

    /**
     * 设置是否有效 0-有效 1无效
     *
     * @param isActive 是否有效 0-有效 1无效
     */
    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    /**
     * 获取支付渠道 1-线上 2-线下
     *
     * @return type - 支付渠道 1-线上 2-线下
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置支付渠道 1-线上 2-线下
     *
     * @param type 支付渠道 1-线上 2-线下
     */
    public void setType(Integer type) {
        this.type = type;
    }
}