package com.fight.dt.business.common.core;

/**
 * Created by tpx on 2017/7/29.
 */
public enum ItemTaskStatusEnum {
    SUCCESS(1, "成功"),
    WAIT(0, "任务等待执行"),
    START(2, "任务开始执行"),
    FAIL(-1, "任务执行失败");

    private int code;

    private String msg;

    ItemTaskStatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
