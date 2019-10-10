package com.marvel.dingdangcat.domain.dingtalk;

import lombok.Data;

import java.util.List;

/**
 * Created by Marvel on 2019/10/10.
 */
@Data
public class DingTalkSendRequest {

    @Data
    public class DingTalkText {
        private String content;
    }

    @Data
    public class DingTalkAt {
        private List<String> atMobiles;
        private Boolean isAtAll;
    }

    private String msgtype;
    private DingTalkText text;
    private DingTalkAt at;
}
