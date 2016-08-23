package com.zyh.seckill2.service.impl;

import com.zyh.seckill2.dao.KillGoodsDao;
import com.zyh.seckill2.dao.SuccessKillRecordDao;
import com.zyh.seckill2.dto.ExplosionKillUrl;
import com.zyh.seckill2.dto.KillExecution;
import com.zyh.seckill2.entity.KillGoods;
import com.zyh.seckill2.entity.SuccessKillRecord;
import com.zyh.seckill2.enums.KillExecutionStateEnum;
import com.zyh.seckill2.exception.RepeatKillException;
import com.zyh.seckill2.exception.SecKillException;
import com.zyh.seckill2.exception.UnlegalRequestExcepttion;
import com.zyh.seckill2.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/3.
 */
@Service("seckillService")
public class SeckillServiceImpl implements SeckillService {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KillGoodsDao killGoodsDao;
    @Autowired
    private SuccessKillRecordDao successKillRecordDao;

    private String getMd5(int goodsId){
        String salt="dfoerndlfk3k45l34$#^%F$%&%GD%^%"+"/"+goodsId;
        String md5= DigestUtils.md5DigestAsHex(salt.getBytes());
        return md5;
    }
    public ExplosionKillUrl getKillUrl(int goodsId) {
        KillGoods killGoods=killGoodsDao.getKillGoodsById(goodsId);
        if(null==killGoods){
            return new ExplosionKillUrl(false,goodsId);
        }
        Date now =new Date();
        if(now.getTime()<killGoods.getStartTime().getTime()||now.getTime()>killGoods.getEndTime().getTime()){
            return new ExplosionKillUrl(false,killGoods.getStartTime().getTime(),killGoods.getEndTime().getTime(),now.getTime());
        }
        String md5=getMd5(goodsId);
        return  new ExplosionKillUrl(md5,true,goodsId,killGoods.getStartTime().getTime(),killGoods.getEndTime().getTime(),now.getTime());
    }
    @Override
    @Transactional
    public KillExecution executeSeckill(String md5, long userPhone, int seckillId)throws RepeatKillException,UnlegalRequestExcepttion, SecKillException{
        try{
            if(null==md5||!md5.equals(getMd5(seckillId))){
                throw new UnlegalRequestExcepttion("不合法请求");
            }
            //减库存
            int updateNum=killGoodsDao.reduceNum(seckillId,new Date());
            if(updateNum<=0){
                throw new SecKillException("秒杀已结束");
            }else{
                //保存秒杀成功记录
                int insertNum=successKillRecordDao.inserSuccessKillRecord(new SuccessKillRecord(seckillId,userPhone,new Date()));
                if(insertNum<=0){
                    throw new RepeatKillException("重复秒杀");
                }else{
                    SuccessKillRecord successKillRecord=successKillRecordDao.getSuccessKillRecord(userPhone,seckillId);
                    return new KillExecution(seckillId,KillExecutionStateEnum.SUCCESS,successKillRecord);
                }

            }
        }catch(UnlegalRequestExcepttion e1){
            throw e1;
        }catch (RepeatKillException e2){
            logger.info("repeatkillexception:"+e2.getMessage());
            throw e2;
        }catch(Exception e){
            System.out.println("--------------------------------------------");
            logger.error(e.getMessage(),e);
            throw new SecKillException("内部错误");
        }
    }

    public List<KillGoods> getAllKillGoods(int start,int end) {
        return killGoodsDao.getAllKillGoods(start,end);
    }

    public List<SuccessKillRecord> getMySuccessKillRecords(long userPhone) {
        return successKillRecordDao.getMySuccessKillRecords(userPhone);
    }

    public SuccessKillRecord getSuccessKillRecord(long userPhone, int goodsId) {
        return successKillRecordDao.getSuccessKillRecord(userPhone,goodsId);
    }

    public List<SuccessKillRecord> getSuccessKillRecords(int goodsId, int begin, int end) {
        return successKillRecordDao.getSuccessKillRecords(goodsId, begin, end);
    }

    public KillGoods getKillGoodsById(int goodsId) {
        return killGoodsDao.getKillGoodsById(goodsId);
    }
}
