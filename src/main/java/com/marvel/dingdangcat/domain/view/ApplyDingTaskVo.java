package com.marvel.dingdangcat.domain.view;

import lombok.Data;

/**
 * 申请钉钉报名任务
 *
 * Created by Marvel on 2019/9/30.
 */
@Data
public class ApplyDingTaskVo {
    /**
     * 关联的钉钉报名任务 ID
     */
    private Long dingTaskId;
    /**
     * 备注
     */
    private String remark;
}
