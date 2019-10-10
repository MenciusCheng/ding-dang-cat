package com.marvel.dingdangcat.helper;

import com.google.gson.Gson;
import com.marvel.dingdangcat.domain.dingtalk.DingTalkSendRequest;

/**
 * Created by Marvel on 2019/10/10.
 */
public class DingTalkHelper {

    public static final Gson gson = new Gson();

    public static void sendText(String message) {
        DingTalkSendRequest dingTalkSendRequest = new DingTalkSendRequest();
        DingTalkSendRequest.DingTalkText dingTalkText = dingTalkSendRequest.new DingTalkText();
        dingTalkText.setContent(message);
        dingTalkSendRequest.setMsgtype("text");
        dingTalkSendRequest.setText(dingTalkText);
        String content = gson.toJson(dingTalkSendRequest);

        System.out.println(content);
    }

    public static void main(String[] args) {
        sendText("威威猫");
    }
}
