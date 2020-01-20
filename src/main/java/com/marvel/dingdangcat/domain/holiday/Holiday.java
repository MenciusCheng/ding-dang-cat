package com.marvel.dingdangcat.domain.holiday;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 节日休假调休表
 * Created by Marvel on 2020/1/20.
 */
@Data
public class Holiday {
    private Long id;
    /**
     * 休/班名称
     */
    private String name;
    /**
     * 起始日期
     */
    private LocalDate startDate;
    /**
     * 截止日期
     */
    private LocalDate endDate;
    /**
     * 是否正常上班,0否/1是
     */
    private Integer work;
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
