package com.zyh.seckill2.dao;

import com.zyh.seckill2.entity.SuccessKillRecord;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.*;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2016/7/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config/spring-dao-config.xml"})
public class SuccessKillRecordDaoTest {
    @Resource
    private SuccessKillRecordDao successKillRecordDao;
    @Test
    public void testInserSuccessKillRecord() throws Exception {
        SuccessKillRecord successKillRecord=new SuccessKillRecord(1000000000,11,1,new Date());
        System.out.println(successKillRecordDao.inserSuccessKillRecord(successKillRecord));
    }

    @Test
    public void testGetSuccessKillRecord() throws Exception {
        System.out.println(successKillRecordDao.getSuccessKillRecord(1000000000,11).toString());
    }

    @Test
    public void testGetMySuccessKillRecords() throws Exception {

    }
}