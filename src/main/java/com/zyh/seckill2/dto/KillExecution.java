package com.zyh.seckill2.dto;

import com.zyh.seckill2.entity.SuccessKillRecord;
import com.zyh.seckill2.enums.KillExecutionStateEnum;

/**
 * Created by Administrator on 2016/8/8.
 */
public class KillExecution {
    private int goodsId;
    private int state;
    private String stateInfo;
    private SuccessKillRecord successKillRecord;

    public KillExecution(int goodsId, KillExecutionStateEnum stateEnum, SuccessKillRecord successKillRecord) {
        this.goodsId = goodsId;
        this.state = stateEnum.getState();
        this.stateInfo=stateEnum.getStateInfo();
        this.successKillRecord = successKillRecord;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
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

    public SuccessKillRecord getSuccessKillRecord() {
        return successKillRecord;
    }

    public void setSuccessKillRecord(SuccessKillRecord successKillRecord) {
        this.successKillRecord = successKillRecord;
    }

    @Override
    public String toString() {
        return "KillExecution{" +
                "goodsId=" + goodsId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKillRecord=" + successKillRecord +
                '}';
    }
}
