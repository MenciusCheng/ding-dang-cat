package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marvel on 2019/9/30.
 */
@Controller
public class CommonViewController {

    private final UserService userService;

    @Autowired
    public CommonViewController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 首页
     */
    @GetMapping("/")
    public String homePage() {
        return "redirect:/ding/dingTask/all";
    }

    /**
     * 关于页
     */
    @GetMapping("/about")
    public String aboutPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "关于");
        modelMap.addAttribute("page", "about");
        userService.addLoginInfo(modelMap);
        return "common/about";
    }
}
