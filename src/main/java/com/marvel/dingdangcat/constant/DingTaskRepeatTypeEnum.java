package com.marvel.dingdangcat.constant;

/**
 * Created by Marvel on 2019/9/30.
 */
public enum DingTaskRepeatTypeEnum {

    ONCE(1),
    WORKDAY(2),
    FRIDAY(3);

    private int value;

    DingTaskRepeatTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
