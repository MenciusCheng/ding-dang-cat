package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.domain.view.LoginInfoVo;
import com.marvel.dingdangcat.domain.view.TUpdatePasswordRequest;
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
    public String login(String username, String password) {
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
        // 执行认证登陆
        subject.login(token);
        return "redirect:/ding/dingTask/all";
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
    public String loginPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "欢迎登录");
        modelMap.addAttribute("page", "login");
        userService.addLoginInfo(modelMap);
        return "user/login";
    }

    /**
     * 个人中心页
     */
    @GetMapping("/userCenter")
    public String userCenterPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "个人中心");
        modelMap.addAttribute("page", "login");
        userService.addLoginInfo(modelMap);
        return "user/userCenter";
    }

    /**
     * 无权限提示页
     */
    @GetMapping("/unauthorized")
    public String unauthorizedPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "403");
        userService.addLoginInfo(modelMap);
        return "user/unauthorized";
    }

    /**
     * 修改密码页
     */
    @GetMapping("/updatePassword")
    public String updatePasswordPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "修改密码");
        userService.addLoginInfo(modelMap);
        return "user/updatePassword";
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public String updatePassword(TUpdatePasswordRequest request) {
        LoginInfoVo loginInfo = userService.findCurrentLoginInfo();
        request.setId(loginInfo.getId());
        userService.updatePassword(request);
        return "redirect:/user/userCenter";
    }
}
