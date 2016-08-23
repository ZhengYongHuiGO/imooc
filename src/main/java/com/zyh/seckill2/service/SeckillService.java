package com.zyh.seckill2.service;

import com.zyh.seckill2.dto.ExplosionKillUrl;
import com.zyh.seckill2.dto.KillExecution;
import com.zyh.seckill2.entity.KillGoods;
import com.zyh.seckill2.entity.SuccessKillRecord;
import com.zyh.seckill2.exception.RepeatKillException;
import com.zyh.seckill2.exception.SecKillException;
import com.zyh.seckill2.exception.UnlegalRequestExcepttion;

import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
public interface SeckillService {
    /**
     * 获取秒杀地址
     * @param goodsId 秒杀商品的id
     * @return
     */
    ExplosionKillUrl getKillUrl(int goodsId);

    /**
     * 执行秒杀
     * @param userPhone 用户id
     * @param seckillId 秒杀商品id
     */
    KillExecution executeSeckill(String md5, long userPhone, int seckillId)throws RepeatKillException,UnlegalRequestExcepttion ,SecKillException;

    /**
     * 获得所有秒杀的商品
     * @return
     */
    List<KillGoods> getAllKillGoods(int start,int end);

    /**
     * 获取用户的秒杀成功记录
     * @param userPhone 用户id
     * @return
     */
    List<SuccessKillRecord> getMySuccessKillRecords(long userPhone);

    /**
     * 获取一个产品的秒杀成功记录
     * @param begin 偏移位置
     * @param end
     * @return
     */
    List<SuccessKillRecord> getSuccessKillRecords(int goodsId,int begin,int end);

    SuccessKillRecord getSuccessKillRecord(long userPhone,int goodsId);
    /**
     * 根据id获取秒杀商品
     * @param goodsId
     * @return
     */
    KillGoods getKillGoodsById(int goodsId);
}
