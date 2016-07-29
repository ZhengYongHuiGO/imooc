package com.zyh.seckill2.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/7/28.
 */
public class SuccessKillRecord {
    private long phone;
    private int goodsId;
    private int state; //-1无效 0未支付 1已支付 2未发货 3 已发货
    private Date killTime;
    private KillGoods killGoods;
    SuccessKillRecord(){}
    public SuccessKillRecord(long phone, int goodsId, int state, Date killTime) {
        this.phone = phone;
        this.goodsId = goodsId;
        this.state = state;
        this.killTime = killTime;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
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

    public Date getKillTime() {
        return killTime;
    }

    public void setKillTime(Date killTime) {
        this.killTime = killTime;
    }

    public KillGoods getKillGoods() {
        return killGoods;
    }

    public void setKillGoods(KillGoods killGoods) {
        this.killGoods = killGoods;
    }

    @Override
    public String toString() {
        return "SuccessKillRecord{" +
                "phone=" + phone +
                ", goodsId=" + goodsId +
                ", state=" + state +
                ", killTime=" + killTime +
                ", killGoods=" + killGoods +
                '}';
    }
}
