package com.marvel.dingdangcat.domain.view;

import lombok.Data;

/**
 * Created by Marvel on 2019/10/11.
 */
@Data
public class TUpdatePasswordRequest {

    private Long id;

    /**
     * 新密码
     */
    private String newPassword;
}
