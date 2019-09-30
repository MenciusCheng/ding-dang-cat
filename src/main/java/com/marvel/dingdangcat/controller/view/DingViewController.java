package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Marvel on 2019/9/24.
 */
@Controller
@RequestMapping("/ding")
public class DingViewController {

    private final UserService userService;

    @Autowired
    public DingViewController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 钉钉报名任务列表页
     */
    @GetMapping("/dingTask/all")
    public String findAllDingTaskPage(ModelMap modelMap) {
        modelMap.addAttribute("title", "钉钉报名任务");
        modelMap.addAttribute("page", "dingTask");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskList";
    }

    /**
     * 钉钉报名任务信息页
     */
    @GetMapping("/dingTask/info")
    public String findDingTaskByIdPage(ModelMap modelMap, Long id) {
        modelMap.addAttribute("title", "钉钉加班餐");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskInfo";
    }

    /**
     * 钉钉报名任务配置页
     */
    @GetMapping("/dingTask/config")
    public String configDingTaskByIdPage(ModelMap modelMap, @RequestParam(required = false) Long id) {
        modelMap.addAttribute("title", "配置");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskConfig";
    }
}
