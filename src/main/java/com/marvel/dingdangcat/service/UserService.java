package com.marvel.dingdangcat.service;

import com.marvel.dingdangcat.domain.user.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Marvel on 2019/9/27.
 */
public interface UserService {

    Permission savePermission(Permission permission);

    Permission findPermissionById(Long id);

    Page<Permission> findAllPermissions(Pageable pageable);
}
