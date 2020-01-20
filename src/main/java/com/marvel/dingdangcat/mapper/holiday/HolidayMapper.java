package com.marvel.dingdangcat.mapper.holiday;

import com.marvel.dingdangcat.domain.holiday.Holiday;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * Created by Marvel on 2020/1/20.
 */
@Repository
public interface HolidayMapper {

    Holiday findByDate(LocalDate date);
}
