package com.marvel.dingdangcat.domain.ding;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 钉钉报名任务表
 *
 * Created by Marvel on 2019/9/24.
 */
public class DingTask {

    private Long id;
    /**
     * 任务名称，长度在 [1, 30]
     */
    private String name;
    /**
     * 开始时间
     */
    private LocalTime startAt;
    /**
     * 结束时间
     */
    private LocalTime endAt;
    /**
     * 人数上限，大于等于0
     */
    private Integer maxCount;
    /**
     * 重复类型，1: 一次; 2: 工作日; 3: 每周五;
     */
    private Integer repeatType;

    /**
     * 是否启用，0: 否; 1: 是;
     */
    private Integer enabled;

    /**
     * 报名状态，1: 未开始; 2: 进行中; 3: 已结束; 4: 已停止;
     */
    private Integer applyStatus;

    /**
     * 管理人员 ID
     */
    private Integer managerId;

    /**
     * 报名描述，长度在 [0, 140]
     */
    private String description;

    /**
     * 钉钉 webhook 地址
     */
    private String dingWebhook;

    /**
     * 是否删除，0: 否; 1: 是;
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
    /**
     * 创建人
     */
    private Integer createdBy;
    /**
     * 更新人
     */
    private Integer updatedBy;

    public DingTask() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public Integer getRepeatType() {
        return repeatType;
    }

    public void setRepeatType(Integer repeatType) {
        this.repeatType = repeatType;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(Integer applyStatus) {
        this.applyStatus = applyStatus;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDingWebhook() {
        return dingWebhook;
    }

    public void setDingWebhook(String dingWebhook) {
        this.dingWebhook = dingWebhook;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }
}
