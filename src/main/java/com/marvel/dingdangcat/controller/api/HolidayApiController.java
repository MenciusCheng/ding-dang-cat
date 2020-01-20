package com.marvel.dingdangcat.controller.api;

import com.marvel.dingdangcat.service.HolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by Marvel on 2020/1/20.
 */
@RestController
@RequestMapping("/api/v1/holiday")
public class HolidayApiController {

    private final HolidayService holidayService;

    @Autowired
    public HolidayApiController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/isWorkday")
    public Boolean isWorkday(@RequestParam(value = "date") LocalDate date) {
        return holidayService.isWorkday(date);
    }
}
