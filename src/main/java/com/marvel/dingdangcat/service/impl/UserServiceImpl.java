package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.dao.PermissionRepository;
import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Marvel on 2019/9/27.
 */
@Service
public class UserServiceImpl implements UserService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public UserServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return permissionRepository.getOne(id);
    }

    @Override
    public Page<Permission> findAllPermissions(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }
}
