package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marvel on 2019/9/29.
 */
@Controller
@RequestMapping("/user")
public class UserViewController {

    private final UserService userService;

    @Autowired
    public UserViewController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(ModelMap modelMap, String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        // 账号信息
        modelMap.addAttribute("account", userService.findAccountByUsername(username));
        return "redirect:/user/userCenter";
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/user/login";
    }

    /**
     * 欢迎登录页
     */
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    /**
     * 个人中心页
     */
    @GetMapping("/userCenter")
    public String userCenterPage(ModelMap modelMap) {
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        // 账号信息
        modelMap.addAttribute("account", userService.findAccountByUsername(username));
        return "user/userCenter";
    }
}
