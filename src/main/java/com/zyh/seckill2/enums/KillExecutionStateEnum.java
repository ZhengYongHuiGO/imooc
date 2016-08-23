package com.zyh.seckill2.enums;

/**
 * Created by Administrator on 2016/8/8.
 */
public enum KillExecutionStateEnum {
    SUCCESS(1,"秒杀成功"),
    END(2,"秒杀结束"),
    INTERNAL_ERROR(3,"系统错误"),
    REQUEST_ERROR(4,"请求参数错误"),
    REPEAT_REQUEST(5,"重复秒杀");


    private String stateInfo;
    private int state;
    KillExecutionStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
