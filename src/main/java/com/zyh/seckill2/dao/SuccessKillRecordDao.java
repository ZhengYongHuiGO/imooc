package com.zyh.seckill2.dao;

import com.zyh.seckill2.entity.SuccessKillRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/7/28.
 */
public interface SuccessKillRecordDao {
    int inserSuccessKillRecord(@Param("successKillRecord") SuccessKillRecord successKillRecord);
    SuccessKillRecord getSuccessKillRecord(@Param("userPhone")long userPhone,@Param("goodsId")int goodsId);
    List<SuccessKillRecord> getMySuccessKillRecords(long userPhone);
}
