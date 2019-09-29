package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.mapper.user.PermissionMapper;
import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Marvel on 2019/9/27.
 */
@Service
public class UserServiceImpl implements UserService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public UserServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public void createPermission(Permission permission) {
        permissionMapper.create(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        permissionMapper.update(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionMapper.delete(id);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionMapper.findById(id);
    }

    @Override
    public List<Permission> findAllPermissions() {
        return permissionMapper.findAll();
    }

    @Override
    public List<Permission> findPermissionsByRoleId(Long roleId) {
        return permissionMapper.findByRoleId(roleId);
    }
}
