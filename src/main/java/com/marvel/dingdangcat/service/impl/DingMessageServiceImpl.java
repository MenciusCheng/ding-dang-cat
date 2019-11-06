package com.marvel.dingdangcat.service.impl;

import com.marvel.dingdangcat.constant.DingTaskApplyStatusEnum;
import com.marvel.dingdangcat.constant.DingTaskNoticeTypeEnum;
import com.marvel.dingdangcat.converter.StringToLocalTimeConverter;
import com.marvel.dingdangcat.domain.ding.DingTask;
import com.marvel.dingdangcat.domain.ding.DingTaskApplyStaff;
import com.marvel.dingdangcat.helper.DingTalkHelper;
import com.marvel.dingdangcat.service.DingMessageService;
import com.marvel.dingdangcat.service.DingService;
import com.marvel.dingdangcat.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Marvel on 2019/11/6.
 */
@Service
public class DingMessageServiceImpl implements DingMessageService {

    private final Logger logger = LoggerFactory.getLogger(DingMessageServiceImpl.class);

    @Value("${ding.domain.name}")
    private String domainName;

    private final DingService dingService;
    private final UserService userService;

    @Autowired
    @Lazy
    public DingMessageServiceImpl(DingService dingService, UserService userService) {
        this.dingService = dingService;
        this.userService = userService;
    }

    @Override
    public boolean sendDingTalk(Long dingTaskId) {
        DingTask dingTask = dingService.findDingTaskById(dingTaskId);

        // 状态不为进行中
        boolean isNotDoing = dingTask.getApplyStatus() != DingTaskApplyStatusEnum.DOING.getValue();
        // 没有配置钉钉地址
        boolean isEmptyDingWebhook = dingTask.getDingWebhook() == null || dingTask.getDingWebhook().length() == 0;

        if (isNotDoing || isEmptyDingWebhook) {
            return false;
        }

        // 是否提醒所有人
        boolean isAtAllSend = dingService.containNoticeType(dingTask.getNoticeType(), DingTaskNoticeTypeEnum.AT_ALL.getValue());

        DingTalkHelper.sendText(dingTask.getDingWebhook(), getDoingMessage(dingTask), isAtAllSend);
        return true;
    }

    @Override
    public boolean sendDingTalkWithApply(Long dingTaskId) {
        DingTask dingTask = dingService.findDingTaskById(dingTaskId);

        // 状态不为进行中
        boolean isNotDoing = dingTask.getApplyStatus() != DingTaskApplyStatusEnum.DOING.getValue();
        // 没有配置钉钉地址
        boolean isEmptyDingWebhook = dingTask.getDingWebhook() == null || dingTask.getDingWebhook().length() == 0;

        if (isNotDoing || isEmptyDingWebhook) {
            return false;
        }

        // 是否提醒所有人
        boolean isAtAllSend = dingService.containNoticeType(dingTask.getNoticeType(), DingTaskNoticeTypeEnum.AT_ALL.getValue());

        DingTalkHelper.sendText(dingTask.getDingWebhook(), getDoingMessageWithApply(dingTask), isAtAllSend);
        return true;
    }

    @Override
    public boolean sendCloseDingTalk(Long dingTaskId) {
        DingTask dingTask = dingService.findDingTaskById(dingTaskId);

        // 状态不为已结束
        boolean isNotOver = dingTask.getApplyStatus() != DingTaskApplyStatusEnum.OVER.getValue();
        // 没有配置钉钉地址
        boolean isEmptyDingWebhook = dingTask.getDingWebhook() == null || dingTask.getDingWebhook().length() == 0;

        if (isNotOver || isEmptyDingWebhook) {
            return false;
        }

        // 是否提醒所有人
        boolean isAtAllSend = dingService.containNoticeType(dingTask.getNoticeType(), DingTaskNoticeTypeEnum.AT_ALL.getValue());

        DingTalkHelper.sendText(dingTask.getDingWebhook(), getCloseMessage(dingTask), isAtAllSend);
        return true;
    }

    /**
     * 报名进行信息
     */
    private String getDoingMessage(DingTask dingTask) {
        return getHeaderMessage(dingTask) + "\n";
    }

    /**
     * 报名进行信息，包含报名人员信息
     */
    private String getDoingMessageWithApply(DingTask dingTask) {
        return getHeaderMessage(dingTask) + "\n" + getApplyStaffMessage(dingTask.getId()) + "\n";
    }

    /**
     * 报名截止信息
     */
    private String getCloseMessage(DingTask dingTask) {
        return "报名截止！\n" + getHeaderMessage(dingTask) + "\n" + getApplyStaffMessage(dingTask.getId()) + "\n";
    }

    /**
     * 报名通用头部信息
     */
    private String getHeaderMessage(DingTask dingTask) {
        String description = dingTask.getDescription().replaceAll("\r\n", "\n");
        String applyTime = "报名时间：" + dingTask.getStartAt().format(StringToLocalTimeConverter.hhmmFormatter) + " ~ " + dingTask.getEndAt().format(StringToLocalTimeConverter.hhmmFormatter);
        String applyUrl = getUrlMessage(dingTask.getId());

        List<String> messageList = new ArrayList<>();
        messageList.add(dingTask.getName());
        messageList.add(description);
        messageList.add(applyTime);
        messageList.add(applyUrl);
        return String.join("\n", messageList);
    }

    /**
     * 报名链接信息
     */
    private String getUrlMessage(Long dingTaskId) {
        String api = "/ding/dingTask/info?dingTaskId=";
        String url = domainName + api + dingTaskId.toString();
        return "报名链接：" + url;
    }

    /**
     * 报名人员列表信息
     */
    private String getApplyStaffMessage(Long dingTaskId) {
        StringBuilder applyStringBuilder = new StringBuilder();
        List<DingTaskApplyStaff> applyStaffList = dingService.findTodayDingTaskApplyStaffByDingTaskId(dingTaskId);
        if (applyStaffList != null && applyStaffList.size() > 0) {
            Map<Long, String> usernameMap = userService.findAccountUsernameMap();
            applyStringBuilder.append("当前报名人员：");
            for (int i = 0; i < applyStaffList.size(); i++) {
                String username = usernameMap.getOrDefault(applyStaffList.get(i).getStaffId(), "");
                String remark = applyStaffList.get(i).getRemark();
                if (remark != null && remark.length() > 0) {
                    remark = " 备注：" + remark;
                } else {
                    remark = "";
                }
                applyStringBuilder.append("\n").append(String.valueOf(i + 1)).append(". ").append(username).append(remark);
            }
        }
        return applyStringBuilder.toString();
    }

}
