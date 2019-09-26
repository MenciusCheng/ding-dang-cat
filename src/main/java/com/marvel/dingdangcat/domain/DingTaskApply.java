package com.marvel.dingdangcat.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 钉钉报名任务申请表
 *
 * Created by Marvel on 2019/9/26.
 */
public class DingTaskApply {

    private Long id;

    /**
     * 关联的钉钉报名任务 ID
     */
    private Long dingTaskId;

    /**
     * 关联的钉钉报名任务名称
     */
    private String dingTaskName;

    /**
     * 报名日期
     */
    private LocalDate applyDate;

    /**
     * 是否完成，0: 否; 1: 是;
     */
    private Integer completed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DingTaskApply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDingTaskId() {
        return dingTaskId;
    }

    public void setDingTaskId(Long dingTaskId) {
        this.dingTaskId = dingTaskId;
    }

    public String getDingTaskName() {
        return dingTaskName;
    }

    public void setDingTaskName(String dingTaskName) {
        this.dingTaskName = dingTaskName;
    }

    public LocalDate getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(LocalDate applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
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
}
