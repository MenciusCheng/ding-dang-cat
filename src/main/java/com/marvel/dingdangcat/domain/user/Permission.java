package com.marvel.dingdangcat.domain.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限表
 *
 * Created by Marvel on 2019/9/27.
 */
@Data
public class Permission {

    private Long id;

    /**
     * 权限名称
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
