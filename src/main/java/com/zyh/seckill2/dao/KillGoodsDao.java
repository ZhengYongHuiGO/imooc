package com.zyh.seckill2.dao;

import com.zyh.seckill2.entity.KillGoods;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface KillGoodsDao {
    int reduceNum(@Param("id") int id, @Param("currentTime")Date currentTime);
    KillGoods getKillGoodsById(int id);
    List<KillGoods> getAllKillGoods(@Param("start")int start,@Param("end") int end);
}
