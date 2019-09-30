package com.marvel.dingdangcat.domain.ding;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 钉钉报名任务表
 *
 * Created by Marvel on 2019/9/24.
 */
@Data
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
    private Long managerId;

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
    private Long createdBy;
    /**
     * 更新人
     */
    private Long updatedBy;
}
