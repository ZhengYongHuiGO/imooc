package com.zyh.seckill2.dao;

import com.zyh.seckill2.entity.KillGoods;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config/spring-dao-config.xml"})
public class KillGoodsDaoTest {
    @Resource
    private KillGoodsDao killGoodsDao;
    @Test
    public void testReduceNum() throws Exception {
        int result=killGoodsDao.reduceNum(10,new Date());
        System.out.println("减内存结果="+result);

    }

    @Test
    public void testGetKillGoodsById() throws Exception {
        System.out.println(killGoodsDao.getKillGoodsById(10).toString());
    }

    @Test
    public void testGetAllKillGoods() throws Exception {
        List<KillGoods> killGoodses=killGoodsDao.getAllKillGoods(0,10);
        for(KillGoods killGoods:killGoodses){
            System.out.println(killGoods.toString());
        }

    }
}