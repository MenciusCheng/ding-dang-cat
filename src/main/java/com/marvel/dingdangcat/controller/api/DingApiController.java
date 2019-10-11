package com.marvel.dingdangcat.controller.api;

import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Marvel on 2019/9/24.
 */
@RestController
@RequestMapping("/api/v1/ding")
public class DingApiController {

    private final Logger logger = LoggerFactory.getLogger(DingApiController.class);

    private final UserService userService;
    private final DingService dingService;

    @Autowired
    public DingApiController(UserService userService, DingService dingService) {
        this.userService = userService;
        this.dingService = dingService;
    }

    @GetMapping("/noticeDingTalk")
    public String noticeDingTalk(Long dingTaskId) {
        if (dingService.sendDingTalk(dingTaskId)) {
            return "success";
        }
        return "failure";
    }
}
