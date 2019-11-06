package com.marvel.dingdangcat.service;

/**
 * 钉钉消息服务
 *
 * Created by Marvel on 2019/11/6.
 */
public interface DingMessageService {

    /**
     * 发送钉钉报名提醒
     */
    boolean sendDingTalk(Long dingTaskId);

    /**
     * 发送钉钉报名提醒，包含报名人员信息
     */
    boolean sendDingTalkWithApply(Long dingTaskId);

    /**
     * 发送钉钉报名截止提醒
     */
    boolean sendCloseDingTalk(Long dingTaskId);
}
