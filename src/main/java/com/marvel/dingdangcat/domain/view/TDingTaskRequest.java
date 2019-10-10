package com.marvel.dingdangcat.domain.view;

import lombok.Data;

import java.time.LocalTime;

/**
 * 钉钉报名任务请求体
 *
 * Created by Marvel on 2019/9/24.
 */
@Data
public class TDingTaskRequest {

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
}
