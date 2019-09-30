package com.marvel.dingdangcat.domain.ding;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 钉钉报名任务申请表
 *
 * Created by Marvel on 2019/9/26.
 */
@Data
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
}
