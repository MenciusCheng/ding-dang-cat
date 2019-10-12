package com.marvel.dingdangcat.controller.view;

import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.domain.user.Account;
import com.marvel.dingdangcat.domain.view.*;
import com.marvel.dingdangcat.exception.NotFoundException;
import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        modelMap.addAttribute("title", "报名列表");
        modelMap.addAttribute("page", "dingTask");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskList";
    }

    /**
     * 钉钉报名任务信息页
     */
    @GetMapping("/dingTask/info")
    public String findDingTaskByIdPage(ModelMap modelMap, @RequestParam Long dingTaskId) {
        Map<Long, String> usernameMap = userService.findAccountUsernameMap();

        DingTask dingTask = dingService.findDingTaskById(dingTaskId);
        if (dingTask == null) {
            throw new NotFoundException("DingTask Not Found");
        }

        TDingTaskResponse dingTaskResponse = new TDingTaskResponse();
        BeanUtils.copyProperties(dingTask, dingTaskResponse);
        dingTaskResponse.setManagerName(usernameMap.getOrDefault(dingTaskResponse.getManagerId(), ""));
        dingTaskResponse.setCreatedByName(usernameMap.getOrDefault(dingTaskResponse.getCreatedBy(), ""));
        dingTaskResponse.setUpdatedByName(usernameMap.getOrDefault(dingTaskResponse.getUpdatedBy(), ""));
        modelMap.addAttribute("dingTask", dingTaskResponse);

        List<DingTaskApplyStaff> applyStaffList = dingService.findDingTaskApplyStaffByDingTaskId(dingTaskId);
        List<DingTaskApplyStaffVo> applyStaffVofList = new ArrayList<>();
        LoginInfoVo loginInfo = userService.findCurrentLoginInfo();

        for (DingTaskApplyStaff applyInfo : applyStaffList) {
            DingTaskApplyStaffVo applyStaffVo = new DingTaskApplyStaffVo();
            BeanUtils.copyProperties(applyInfo, applyStaffVo);
            applyStaffVo.setStaffName(usernameMap.getOrDefault(applyStaffVo.getStaffId(), ""));

            if (loginInfo != null && applyInfo.getStaffId().equals(loginInfo.getId())) {
                // 登录人报名信息
                modelMap.addAttribute("myApplyInfo", applyStaffVo);
            }
            applyStaffVofList.add(applyStaffVo);
        }
        // 报名人员列表
        modelMap.addAttribute("applyStaffList", applyStaffVofList);

        modelMap.addAttribute("title", dingTask.getName());
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

        List<Account> allAccounts = userService.findAllAccounts();
        modelMap.addAttribute("allAccounts", allAccounts);

        modelMap.addAttribute("title", "配置");
        userService.addLoginInfo(modelMap);
        return "ding/dingTaskConfig";
    }

    /**
     * 保存钉钉报名任务
     */
    @PostMapping("/dingTask/save")
    public String saveDingTask(TDingTaskRequest dingTask) {
        return "redirect:/ding/dingTask/info?dingTaskId=" + dingService.saveDingTask(dingTask);
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
