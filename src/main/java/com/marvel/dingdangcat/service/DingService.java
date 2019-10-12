package com.marvel.dingdangcat.service;

import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApply;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.domain.view.ApplyDingTaskVo;
import com.marvel.dingdangcat.domain.view.TDingTaskRequest;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Marvel on 2019/9/30.
 */
public interface DingService {

    Long saveDingTask(TDingTaskRequest dingTask);

    void deleteDingTask(Long dingTaskId);

    DingTask findDingTaskById(Long dingTaskId);

    List<DingTask> findAllDingTask();

    List<DingTask> findEnabledDingTasks();

    void applyDingTask(ApplyDingTaskVo applyDingTaskVo);

    void cancelApplyDingTask(Long dingTaskId);

    List<DingTaskApply> findDingTaskApplyByDingTaskId(Long dingTaskId);

    List<DingTaskApplyStaff> findTodayDingTaskApplyStaffByDingTaskId(Long dingTaskId);

    List<DingTaskApplyStaff> findDingTaskApplyStaffByDingTaskIdAndApplyDate(Long dingTaskId, LocalDate applyDate);

    boolean sendDingTalk(Long dingTaskId);
}
