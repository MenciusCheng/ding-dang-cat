package com.marvel.dingdangcat.task;

import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.constant.DingTaskRepeatTypeEnum;
import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.helper.DingHelper;
import com.marvel.dingdangcat.mapper.ding.DingTaskMapper;
import com.marvel.dingdangcat.service.DingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 钉钉报名任务定时更新
 * <p>
 * Created by Marvel on 2019/10/9.
 */
@Component
public class DingTaskScheduler {

    private final Logger logger = LoggerFactory.getLogger(DingTaskScheduler.class);

    private final DingService dingService;
    private final DingTaskMapper dingTaskMapper;

    @Autowired
    public DingTaskScheduler(DingService dingService, DingTaskMapper dingTaskMapper) {
        this.dingService = dingService;
        this.dingTaskMapper = dingTaskMapper;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    private void process() {
        logger.info("======== DingTaskScheduler Start");

        // 已开启的任务
        List<DingTask> enabledDingTasks = dingService.findEnabledDingTasks();

        LocalTime now = LocalTime.now();
//        boolean isWorkday = isWorkday(LocalDate.now());
//        boolean isFriday = isFriday(LocalDate.now());

        // 未开始，当前时间小于开始时间今天有效
        List<Long> notStartedDingTaskIds = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.NOT_STARTED.getValue() && DingHelper.calApplyStatus(now, dt) == DingTaskApplyStatusEnum.NOT_STARTED.getValue())
                .map(DingTask::getId)
                .collect(Collectors.toList());

        // 进行中，当前时间大于等于开始时间，小于等于结束时间，今天有效
        List<Long> doingDingTaskIds = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.DOING.getValue() && DingHelper.calApplyStatus(now, dt) == DingTaskApplyStatusEnum.DOING.getValue())
                .map(DingTask::getId)
                .collect(Collectors.toList());

        // 已结束，当前时间大于结束时间，今天有效
        List<DingTask> overDingTasks = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.OVER.getValue() && DingHelper.calApplyStatus(now, dt) == DingTaskApplyStatusEnum.OVER.getValue())
                .collect(Collectors.toList());
        List<Long> overDingTaskIds = overDingTasks.stream().map(DingTask::getId).collect(Collectors.toList());

        // 已结束，当前时间大于结束时间，今天有效，重复为一次
        List<Long> overOnceDingTaskIds = overDingTasks.stream()
                .filter(dt -> DingTaskRepeatTypeEnum.ONCE.getValue() == dt.getRepeatType())
                .map(DingTask::getId)
                .collect(Collectors.toList());

        // 未开始，今天无效
        List<Long> notTodayDingTaskIds = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.NOT_STARTED.getValue() && !DingHelper.isTodayEnabled(dt.getRepeatType()))
                .map(DingTask::getId)
                .collect(Collectors.toList());

        logger.info("notStartedDingTaskIds: " + notStartedDingTaskIds.toString());
        logger.info("doingDingTaskIds: " + doingDingTaskIds.toString());
        logger.info("overDingTaskIds: " + overDingTaskIds.toString());
        logger.info("overOnceDingTaskIds: " + overOnceDingTaskIds.toString());
        logger.info("notTodayDingTaskIds: " + notTodayDingTaskIds.toString());

        // 更新报名状态
        if (!notStartedDingTaskIds.isEmpty()) {
            dingTaskMapper.updateApplyStatusByIds(notStartedDingTaskIds, DingTaskApplyStatusEnum.NOT_STARTED.getValue());
        }
        if (!doingDingTaskIds.isEmpty()) {
            dingTaskMapper.updateApplyStatusByIds(doingDingTaskIds, DingTaskApplyStatusEnum.DOING.getValue());
            // 通过定时器变为进行中时，发送钉钉提醒
            for (Long dingTaskId : doingDingTaskIds) {
                dingService.sendDingTalk(dingTaskId);
            }
        }
        if (!overDingTaskIds.isEmpty()) {
            dingTaskMapper.updateApplyStatusByIds(overDingTaskIds, DingTaskApplyStatusEnum.OVER.getValue());
        }
        if (!notTodayDingTaskIds.isEmpty()) {
            dingTaskMapper.updateApplyStatusByIds(notTodayDingTaskIds, DingTaskApplyStatusEnum.NOT_STARTED.getValue());
        }

        // 关闭启用
        if (!overOnceDingTaskIds.isEmpty()) {
            dingTaskMapper.updateEnabledByIds(overOnceDingTaskIds, 0);
        }

        // TODO 状态因定时更新为已结束，且有人报名时，更新报名父表为已完成。

        logger.info("======== DingTaskScheduler Finish");
    }
}
