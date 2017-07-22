package com.fight.dt.business.common.core;

/**
 * Created by tpx on 2017/7/22.
 */
public enum MsgEnum {
    SUCCESS(0, "成功"),
    Fail(-1, "失败");

    private int code;

    private String msg;

    MsgEnum(int code, String msg) {
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
