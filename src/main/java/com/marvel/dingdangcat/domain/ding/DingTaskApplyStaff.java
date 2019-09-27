package com.marvel.dingdangcat.domain.ding;

import java.time.LocalDateTime;

/**
 * 钉钉报名任务申请人员表
 *
 * Created by Marvel on 2019/9/26.
 */
public class DingTaskApplyStaff {

    private Long id;

    /**
     * 关联的钉钉报名任务申请 ID
     */
    private Long dingTaskApplyId;

    /**
     * 申请人 ID
     */
    private Integer staffId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否取消，0: 否; 1: 是;
     */
    private Integer cancelled;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DingTaskApplyStaff() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDingTaskApplyId() {
        return dingTaskApplyId;
    }

    public void setDingTaskApplyId(Long dingTaskApplyId) {
        this.dingTaskApplyId = dingTaskApplyId;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCancelled() {
        return cancelled;
    }

    public void setCancelled(Integer cancelled) {
        this.cancelled = cancelled;
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
