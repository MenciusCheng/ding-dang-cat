package com.marvel.dingdangcat.config;

import com.marvel.dingdangcat.domain.user.Account;
import com.marvel.dingdangcat.domain.user.Permission;
import com.marvel.dingdangcat.domain.user.Role;
import com.marvel.dingdangcat.domain.view.LoginInfoVo;
import com.marvel.dingdangcat.mapper.user.AccountMapper;
import com.marvel.dingdangcat.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CustomRealm extends AuthorizingRealm {

    private final UserService userService;

    @Autowired
    public CustomRealm(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        Account account = userService.findAccountByUsername(token.getUsername());
        if (null == account) {
            throw new AccountException("用户名不正确");
        } else if (!account.getPassword().equals(new String((char[]) token.getCredentials()))) {
            throw new AccountException("密码不正确");
        }
        return new SimpleAuthenticationInfo(token.getPrincipal(), account.getPassword(), getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        LoginInfoVo loginInfo = userService.findLoginInfoByUsername(username);
        if (loginInfo != null) {
            info.setRoles(loginInfo.getRoles());
            info.setStringPermissions(loginInfo.getPermissions());
        }
        return info;
    }
}
