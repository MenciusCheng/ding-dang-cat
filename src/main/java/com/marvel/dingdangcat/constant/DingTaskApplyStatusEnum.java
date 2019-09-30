package com.marvel.dingdangcat.constant;

/**
 * Created by Marvel on 2019/9/30.
 */
public enum DingTaskApplyStatusEnum {

    NOT_STARTED(1),
    DOING(2),
    OVER(3),
    STOP(4);

    private int value;

    DingTaskApplyStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
