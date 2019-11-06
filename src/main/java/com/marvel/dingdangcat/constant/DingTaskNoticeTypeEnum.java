package com.marvel.dingdangcat.constant;

/**
 * 提醒类型
 *
 * Created by Marvel on 2019/9/30.
 */
public enum DingTaskNoticeTypeEnum {
    // 触发条件
    START(1), // 开始时
    CLOSE(2), // 结束时
    APPLY(3), // 有报名时
    WHOLE(4), // 整点

    // 附加功能
    AT_ALL(11); // 通知所有人

    private int value;

    DingTaskNoticeTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
