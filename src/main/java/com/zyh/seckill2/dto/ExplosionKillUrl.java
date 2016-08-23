package com.zyh.seckill2.dto;

import java.util.Date;

/**
 * Created by Administrator on 2016/8/3.
 */
public class ExplosionKillUrl {
    private String md5;
    private boolean canKill;
    private int goodsId;
    private long beginTime;
    private long endTime;
    private long now;

    public ExplosionKillUrl(boolean canKill, long beginTime, long endTime, long now) {
        this.canKill = canKill;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.now = now;
    }

    public ExplosionKillUrl(boolean canKill, int goodsId) {
        this.canKill = canKill;
        this.goodsId = goodsId;
    }

    public ExplosionKillUrl(String md5, boolean canKill, int goodsId, long beginTime, long endTime, long now) {
        this.md5 = md5;
        this.canKill = canKill;
        this.goodsId = goodsId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.now = now;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public boolean isCanKill() {
        return canKill;
    }

    public void setCanKill(boolean canKill) {
        this.canKill = canKill;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "ExplosionKillUrl{" +
                "md5='" + md5 + '\'' +
                ", canKill=" + canKill +
                ", goodsId=" + goodsId +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", now=" + now +
                '}';
    }
}
