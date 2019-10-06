package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.domain.view.ApplyDingTaskVo;
import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Marvel on 2019/9/24.
 */
@Controller
@RequestMapping("/ding")
public class DingViewController {

    private final Logger logger = LoggerFactory.getLogger(DingViewController.class);

    private final UserService userService;
    private final DingService dingService;

    @Autowired
    public DingViewController(UserService userService, DingService dingService) {
        this.userService = userService;
        this.dingService = dingService;
    }

    /**
     * 钉钉报名任务列表页
     */
    @GetMapping("/dingTask/all")
    public String findAllDingTaskPage(ModelMap modelMap) {
        List<DingTask> dingTaskList = dingService.findAllDingTask();
        modelMap.addAttribute("dingTaskList", dingTaskList);

        modelMap.addAttribute("title", "钉钉报名任务");
        modelMap.addAttribute("page", "dingTask");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskList";
    }

    /**
     * 钉钉报名任务信息页
     */
    @GetMapping("/dingTask/info")
    public String findDingTaskByIdPage(ModelMap modelMap, @RequestParam Long dingTaskId) {
        DingTask dingTask = dingService.findDingTaskById(dingTaskId);
        modelMap.addAttribute("dingTask", dingTask);
        List<DingTaskApplyStaff> applyStaffList = dingService.findDingTaskApplyStaffByDingTaskId(dingTaskId);
        modelMap.addAttribute("applyStaffList", applyStaffList);

        modelMap.addAttribute("title", "钉钉加班餐");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskInfo";
    }

    /**
     * 钉钉报名任务配置页
     */
    @GetMapping("/dingTask/config")
    public String configDingTaskByIdPage(ModelMap modelMap, @RequestParam(required = false) Long dingTaskId) {
        if (dingTaskId != null && dingTaskId > 0) {
            DingTask dingTask = dingService.findDingTaskById(dingTaskId);
            modelMap.addAttribute("dingTask", dingTask);
        }

        modelMap.addAttribute("title", "配置");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskConfig";
    }

    /**
     * 保存钉钉报名任务
     */
    @PostMapping("/dingTask/save")
    public String saveDingTask(DingTask dingTask) {
        dingService.saveDingTask(dingTask);
        return "redirect:/ding/dingTask/info?dingTaskId=" + dingTask.getId();
    }

    /**
     * 删除钉钉报名任务
     */
    @PostMapping("/dingTask/delete")
    public String deleteDingTask(Long dingTaskId) {
        dingService.deleteDingTask(dingTaskId);
        return "redirect:/ding/dingTask/all";
    }

    /**
     * 申请钉钉报名任务
     */
    @PostMapping("/dingTask/apply")
    public String applyDingTask(ApplyDingTaskVo applyDingTaskVo) {
        dingService.applyDingTask(applyDingTaskVo);
        return "redirect:/ding/dingTask/info?dingTaskId=" + applyDingTaskVo.getDingTaskId();
    }

    /**
     * 取消申请钉钉报名任务
     */
    @PostMapping("/dingTask/cancelApply")
    public String cancelApplyDingTask(Long dingTaskId) {
        dingService.cancelApplyDingTask(dingTaskId);
        return "redirect:/ding/dingTask/info?dingTaskId=" + dingTaskId;
    }
}
