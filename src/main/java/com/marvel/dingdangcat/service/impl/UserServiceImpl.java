package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.domain.user.Account;
import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.domain.user.Role;
import com.marvel.dingdangcat.domain.view.LoginInfoVo;
import com.marvel.dingdangcat.domain.view.TUpdatePasswordRequest;
import com.marvel.dingdangcat.mapper.user.AccountMapper;
import com.marvel.dingdangcat.mapper.user.PermissionMapper;
import com.marvel.dingdangcat.mapper.user.RoleMapper;
import com.marvel.dingdangcat.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public LoginInfoVo findLoginInfoByUsername(String username) {
        Account account = accountMapper.findByUsername(username);
        if (account == null) {
            return null;
        }
        List<Role> roles = roleMapper.findByAccountId(account.getId());
        Set<String> stringRoles = roles.stream().map(Role::getName).collect(Collectors.toSet());
        Set<Permission> permissions = new HashSet<>();
        for (Role r : roles) {
            List<Permission> ps = permissionMapper.findByRoleId(r.getId());
            permissions.addAll(ps);
        }
        Set<String> stringPermissions = permissions.stream().map(Permission::getName).collect(Collectors.toSet());

        LoginInfoVo loginInfoVo = new LoginInfoVo();
        loginInfoVo.setId(account.getId());
        loginInfoVo.setUsername(account.getUsername());
        loginInfoVo.setPhone(account.getPhone());
        loginInfoVo.setRoles(stringRoles);
        loginInfoVo.setPermissions(stringPermissions);
        return loginInfoVo;
    }

    @Override
    public LoginInfoVo addLoginInfo(ModelMap modelMap) {
        LoginInfoVo loginInfo = findCurrentLoginInfo();
        // 账号信息
        modelMap.addAttribute("account", loginInfo);
        return loginInfo;
    }

    @Override
    public LoginInfoVo findCurrentLoginInfo() {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        return findLoginInfoByUsername(username);
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
    public List<Role> findRolesByAccountId(Long accountId) {
        return roleMapper.findByAccountId(accountId);
    }

    @Override
    public Account findAccountById(Long id) {
        return accountMapper.findById(id);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return accountMapper.findByUsername(username);
    }

    @Override
    public Account findAccountByPhone(String phone) {
        return accountMapper.findByPhone(phone);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountMapper.findAll();
    }

    @Override
    public Map<Long, String> findAccountUsernameMap() {
        return findAllAccounts().stream().collect(Collectors.toMap(Account::getId, Account::getUsername));
    }

    @Override
    public void updatePassword(TUpdatePasswordRequest request) {
        accountMapper.updatePassword(request.getId(), request.getNewPassword());
    }
}
