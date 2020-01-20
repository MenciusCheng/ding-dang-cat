package com.marvel.dingdangcat.service;

import java.time.LocalDate;

/**
 * Created by Marvel on 2020/1/20.
 */
public interface HolidayService {

    Boolean isWorkday(LocalDate date);
}
