package com.marvel.dingdangcat.helper;

import com.marvel.dingdangcat.DingDangCatApplication;
import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.constant.DingTaskRepeatTypeEnum;
import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.service.HolidayService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Marvel on 2019/10/10.
 */
public class DingHelper {

    /**
     * 计算报名状态
     * 1. 未开始：
     *      1.1 已启用，当天有效，当前时间小于开始时间今天有效
     *      1.2 已启用，当天无效（周期为重复）
     * 2. 进行中：已启用，当天有效，当前时间大于等于开始时间，小于等于结束时间
     * 3. 已结束：已启用，当天有效，当前时间大于结束时间
     * 4. 已停止：未启用
     */
    public static Integer calApplyStatus(LocalTime current, DingTask dingTask) {
        if (dingTask.getEnabled() == 0) {
            return DingTaskApplyStatusEnum.STOP.getValue();
        }
        if (!isTodayEnabled(dingTask.getRepeatType())) {
            return DingTaskApplyStatusEnum.NOT_STARTED.getValue();
        }
        if (current.isBefore(dingTask.getStartAt())) {
            return DingTaskApplyStatusEnum.NOT_STARTED.getValue();
        } else if (current.isAfter(dingTask.getEndAt())) {
            return DingTaskApplyStatusEnum.OVER.getValue();
        } else {
            return DingTaskApplyStatusEnum.DOING.getValue();
        }
    }

    /**
     * 返回任务今天是否有效
     */
    public static boolean isTodayEnabled(Integer repeatType) {
        if (DingTaskRepeatTypeEnum.WORKDAY.getValue() == repeatType) {
            return isWorkday(LocalDate.now());
        } else if (DingTaskRepeatTypeEnum.FRIDAY.getValue() == repeatType) {
            return isFriday(LocalDate.now());
        }
        return true;
    }

    private static boolean isWorkday(LocalDate localDate) {
        HolidayService holidayService = DingDangCatApplication.getApplicationContext().getBean(HolidayService.class);
        return holidayService.isWorkday(localDate);
    }

    private static boolean isFriday(LocalDate localDate) {
        return localDate.getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
