package com.marvel.dingdangcat.service;

import com.marvel.dingdangcat.domain.user.Account;
import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.domain.user.Role;
import com.marvel.dingdangcat.domain.view.LoginInfoVo;
import org.springframework.ui.ModelMap;

import java.util.List;

/**
 * Created by Marvel on 2019/9/27.
 */
public interface UserService {

    LoginInfoVo findLoginInfoByUsername(String username);

    LoginInfoVo addLoginInfo(ModelMap modelMap);

    LoginInfoVo findCurrentLoginInfo();

    void createPermission(Permission permission);

    void updatePermission(Permission permission);

    void deletePermission(Long id);

    Permission findPermissionById(Long id);

    List<Permission> findAllPermissions();

    List<Permission> findPermissionsByRoleId(Long roleId);

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Long id);

    Role findRoleById(Long id);

    List<Role> findAllRoles();

    List<Role> findRolesByAccountId(Long accountId);

    Account findAccountById(Long id);

    Account findAccountByUsername(String username);

    Account findAccountByPhone(String phone);

    List<Account> findAllAccounts();
}
