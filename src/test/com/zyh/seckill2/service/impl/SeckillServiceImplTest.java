package com.zyh.seckill2.service.impl;

import com.zyh.seckill2.dto.ExplosionKillUrl;
import com.zyh.seckill2.dto.KillExecution;
import com.zyh.seckill2.entity.KillGoods;
import com.zyh.seckill2.entity.SuccessKillRecord;
import com.zyh.seckill2.exception.RepeatKillException;
import com.zyh.seckill2.exception.SecKillException;
import com.zyh.seckill2.exception.UnlegalRequestExcepttion;
import com.zyh.seckill2.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/8/9.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config/spring-service-config.xml","classpath:spring-config/spring-dao-config.xml"})
public class SeckillServiceImplTest  {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @Test
    public void testKill() throws Exception {
        try {
            ExplosionKillUrl e = seckillService.getKillUrl(100);
            System.out.println(e.toString());
            if (e.isCanKill()) {
                KillExecution killExecution = seckillService.executeSeckill(e.getMd5(), 1111111111112L, e.getGoodsId());
                System.out.println(killExecution.toString());
            }
        } catch (UnlegalRequestExcepttion e1) {
            logger.info("存在不合法请求");
            System.out.println(e1.getMessage());
        } catch (RepeatKillException e2) {
            logger.warn("用户重复请求");
        } catch (SecKillException e) {
            logger.error(e.getMessage());
            throw e;
        }

    }

    public ExplosionKillUrl testGetKillUrl() {
        ExplosionKillUrl ek = seckillService.getKillUrl(100);
        return ek;
    }

    public KillExecution testExecuteSeckill(String md5, long phone, int seckillId) throws UnlegalRequestExcepttion, RepeatKillException, SecKillException {
        KillExecution ke = seckillService.executeSeckill(md5, phone, seckillId);
        return ke;
    }

    @Test
    public void testGetAllKillGoods() throws Exception {
        List<KillGoods> killGoods = seckillService.getAllKillGoods(0, 10);
        logger.info("list of killGoods:{}", killGoods);
    }

    @Test
    public void testGetMySuccessKillRecords() throws Exception {
        List<SuccessKillRecord> successKillRecords = seckillService.getMySuccessKillRecords(18989080000L);
        logger.info("list of mySuccessKillRecords:{}", successKillRecords);
    }

    @Test
    public void testGetSuccessKillRecord() throws Exception {
        List<SuccessKillRecord> successKillRecords = seckillService.getSuccessKillRecords(100, 0, 10);
        logger.info("list of successKillRecords:{}", successKillRecords);
    }

    @Test
    public void testGetSuccessKillRecords() throws Exception {

    }

    @Test
    public void testGetKillGoodsById() throws Exception {
        KillGoods kg = seckillService.getKillGoodsById(101);
        System.out.println(kg.toString());
    }

}