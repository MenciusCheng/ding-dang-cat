package com.marvel.dingdangcat.service;

import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.domain.view.ApplyDingTaskVo;

import java.util.List;

/**
 * Created by Marvel on 2019/9/30.
 */
public interface DingService {

    void saveDingTask(DingTask dingTask);

    void deleteDingTask(Long dingTaskId);

    DingTask findDingTaskById(Long dingTaskId);

    List<DingTask> findAllDingTask();

    List<DingTask> findEnabledDingTasks();

    void applyDingTask(ApplyDingTaskVo applyDingTaskVo);

    void cancelApplyDingTask(Long dingTaskId);

    List<DingTaskApplyStaff> findDingTaskApplyStaffByDingTaskId(Long dingTaskId);
}
