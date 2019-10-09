package com.marvel.dingdangcat.task;

import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.constant.DingTaskRepeatTypeEnum;
import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.service.DingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
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

    @Autowired
    public DingTaskScheduler(DingService dingService) {
        this.dingService = dingService;
    }

    @Scheduled(cron = "0 0/1 * * * ?")
    private void process() {
        // 已开启的任务
        List<DingTask> enabledDingTasks = dingService.findEnabledDingTasks();

        LocalTime now = LocalTime.now();
        boolean isWorkday = isWorkday(LocalDate.now());
        boolean isFriday = isFriday(LocalDate.now());

        // 未开始，小于开始时间
        List<DingTask> notStartedDingTasks = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.NOT_STARTED.getValue() && now.isBefore(dt.getStartAt()) && isTodayEnabled(dt))
                .collect(Collectors.toList());

        // 进行中，大于等于开始时间，小于等于结束时间
        List<DingTask> doingDingTasks = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.DOING.getValue() && now.compareTo(dt.getStartAt()) >= 0 && now.compareTo(dt.getEndAt()) <= 0 && isTodayEnabled(dt))
                .collect(Collectors.toList());

        // 已结束，大于结束时间
        List<DingTask> overDingTasks = enabledDingTasks.stream()
                .filter(dt -> dt.getApplyStatus() != DingTaskApplyStatusEnum.OVER.getValue() && now.isAfter(dt.getEndAt()) && isTodayEnabled(dt))
                .collect(Collectors.toList());


        logger.info("定时任务 DingTaskScheduler");
    }

    /**
     * 返回任务今天是否有效
     */
    private boolean isTodayEnabled(DingTask dingTask) {
        if (DingTaskRepeatTypeEnum.WORKDAY.getValue() == dingTask.getRepeatType()) {
            return isWorkday(LocalDate.now());
        } else if (DingTaskRepeatTypeEnum.FRIDAY.getValue() == dingTask.getRepeatType()) {
            return isFriday(LocalDate.now());
        }
        return true;
    }

    private boolean isWorkday(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue() >= DayOfWeek.MONDAY.getValue() && localDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue();
    }

    private boolean isFriday(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
