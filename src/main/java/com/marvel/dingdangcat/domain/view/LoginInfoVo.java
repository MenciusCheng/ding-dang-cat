package com.marvel.dingdangcat.domain.view;

import lombok.Data;

import java.util.Set;

/**
 * 登录账号信息
 *
 * Created by Marvel on 2019/9/30.
 */
@Data
public class LoginInfoVo {

    private Long id;

    /**
     * 账号名称
     */
    private String username;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 角色名称列表
     */
    private Set<String> roles;

    /**
     * 权限名称列表
     */
    private Set<String> permissions;
}
