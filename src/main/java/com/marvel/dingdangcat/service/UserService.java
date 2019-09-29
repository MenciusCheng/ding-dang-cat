package com.marvel.dingdangcat.service;

import com.marvel.dingdangcat.domain.user.Permission;
import java.util.List;

/**
 * Created by Marvel on 2019/9/27.
 */
public interface UserService {

    void createPermission(Permission permission);

    void updatePermission(Permission permission);

    void deletePermission(Long id);

    Permission findPermissionById(Long id);

    List<Permission> findAllPermissions();

    List<Permission> findPermissionsByRoleId(Long roleId);
}
