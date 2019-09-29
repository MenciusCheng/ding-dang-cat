package com.marvel.dingdangcat.domain.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色表
 *
 * Created by Marvel on 2019/9/27.
 */
@Data
public class Role {

    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
