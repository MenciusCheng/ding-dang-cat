package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.domain.user.Account;
import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.domain.user.Role;
import com.marvel.dingdangcat.mapper.user.AccountMapper;
import com.marvel.dingdangcat.mapper.user.PermissionMapper;
import com.marvel.dingdangcat.mapper.user.RoleMapper;
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
    private final RoleMapper roleMapper;
    private final AccountMapper accountMapper;

    @Autowired
    public UserServiceImpl(
            PermissionMapper permissionMapper,
            RoleMapper roleMapper,
            AccountMapper accountMapper) {
        this.permissionMapper = permissionMapper;
        this.roleMapper = roleMapper;
        this.accountMapper = accountMapper;
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

    @Override
    public void createRole(Role role) {
        roleMapper.create(role);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.update(role);
    }

    @Override
    public void deleteRole(Long id) {
        roleMapper.delete(id);
    }

    @Override
    public Role findRoleById(Long id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findAllRoles() {
        return roleMapper.findAll();
    }

    @Override
    public List<Role> findRolesByRoleId(Long accountId) {
        return roleMapper.findByAccountId(accountId);
    }

    @Override
    public Account findAccountById(Long id) {
        return accountMapper.findById(id);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountMapper.findAll();
    }
}
