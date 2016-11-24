package com.wuswoo.easypay.service.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "platform_code")
public class PlatformCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 返回码
     */
    private String code;

    /**
     * 描述
     */
    private String description;

    /**
     * 第三方支付平台ID
     */
    @Column(name = "platform_id")
    private Integer platformId;

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
     * 获取返回码
     *
     * @return code - 返回码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置返回码
     *
     * @param code 返回码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取第三方支付平台ID
     *
     * @return platform_id - 第三方支付平台ID
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 设置第三方支付平台ID
     *
     * @param platformId 第三方支付平台ID
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
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