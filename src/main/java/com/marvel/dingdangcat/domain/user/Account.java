package com.marvel.dingdangcat.domain.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 账号表
 *
 * Created by Marvel on 2019/9/27.
 */
@Data
public class Account {

    private Long id;

    /**
     * 账号名称
     */
    private String username;

    /**
     * 账号密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 最近登录时间
     */
    private LocalDateTime lastLoginAt;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
