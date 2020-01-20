package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.domain.holiday.Holiday;
import com.marvel.dingdangcat.mapper.holiday.HolidayMapper;
import com.marvel.dingdangcat.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Created by Marvel on 2020/1/20.
 */
@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayMapper holidayMapper;

    @Autowired
    public HolidayServiceImpl(HolidayMapper holidayMapper) {
        this.holidayMapper = holidayMapper;
    }

    @Override
    public Boolean isWorkday(LocalDate date) {
        Holiday holiday = holidayMapper.findByDate(date);
        if (holiday == null) {
            // 没有找到对应假日，则周一至周五为工作日
            return date.getDayOfWeek().getValue() >= DayOfWeek.MONDAY.getValue() && date.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue();
        }
        return holiday.getWork() == 1;
    }
}
