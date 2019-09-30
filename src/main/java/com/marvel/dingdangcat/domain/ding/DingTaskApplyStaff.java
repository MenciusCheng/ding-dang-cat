package com.marvel.dingdangcat.domain.ding;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 钉钉报名任务申请人员表
 *
 * Created by Marvel on 2019/9/26.
 */
@Data
public class DingTaskApplyStaff {

    private Long id;

    /**
     * 关联的钉钉报名任务申请 ID
     */
    private Long dingTaskApplyId;

    /**
     * 申请人 ID
     */
    private Long staffId;

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
}
