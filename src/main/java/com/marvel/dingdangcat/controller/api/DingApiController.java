package com.marvel.dingdangcat.controller.api;

import com.marvel.dingdangcat.service.DingMessageService;
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

    private final DingMessageService dingMessageService;

    @Autowired
    public DingApiController(DingMessageService dingMessageService) {
        this.dingMessageService = dingMessageService;
    }

    @GetMapping("/noticeDingTalk")
    public String noticeDingTalk(Long dingTaskId) {
        if (dingMessageService.sendDingTalk(dingTaskId)) {
            return "success";
        }
        return "failure";
    }
}
