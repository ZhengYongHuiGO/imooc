package com.zyh.seckill2.controller;

import com.zyh.seckill2.dto.ExplosionKillUrl;
import com.zyh.seckill2.dto.KillExecution;
import com.zyh.seckill2.dto.SeckillResult;
import com.zyh.seckill2.entity.KillGoods;
import com.zyh.seckill2.exception.RepeatKillException;
import com.zyh.seckill2.exception.SecKillException;
import com.zyh.seckill2.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    @RequestMapping(value="/killGoodsList",method= RequestMethod.GET)
    public String getKillGoods(Model model,Integer start,Integer end){
        if(start==null){
            start=0;
        }
        if(end==null){
            end=10;
        }
        List<KillGoods> killGoodses=seckillService.getAllKillGoods(start,end);
        model.addAttribute("killGoodses",killGoodses);
        return "seckill";
    }
    @RequestMapping(value="/killGoods/{goodsId}",method = RequestMethod.GET)
    public String getKillGoodsById(@PathVariable(value="goodsId") Integer goodsId,Model model){
        System.out.println(goodsId);
        if(goodsId==null){
            System.out.println(1);
            return "redirect:/seckill/killGoodsList";
        }
        KillGoods killGoods=seckillService.getKillGoodsById(goodsId);
        if(killGoods==null){
            return "forward:/killGoodsList";
        }
        else{
            model.addAttribute("killGoods",killGoods);
            return "seckill-detail";
        }
    }
    @RequestMapping(value="/getExplosion/{goodsId}",method=RequestMethod.GET)
    @ResponseBody
    public  SeckillResult<ExplosionKillUrl> getExplosion(@PathVariable(value="goodsId") int goodsId){
        ExplosionKillUrl explosionKillUrl=seckillService.getKillUrl(goodsId);
        return new SeckillResult<ExplosionKillUrl>(true,"success",explosionKillUrl);
    }
    @RequestMapping(value="/currentTime",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> currentTime(){
        return new SeckillResult<Long>(true,"success",new Date().getTime());
    }
    @RequestMapping(value="/{goodsId}/{md5}/killExecute",method=RequestMethod.POST)
    @ResponseBody
    public SeckillResult<KillExecution> killExecute(@PathVariable(value="goodsId") int goodsId, @PathVariable(value="md5") String md5, @CookieValue(value="userPhone",required = false)Long userPhone){
        if(userPhone==null){
            return new SeckillResult<KillExecution>(false,"未登录");
        }

        try {
            KillExecution killExecution=seckillService.executeSeckill(md5,userPhone,goodsId);
            return new SeckillResult<KillExecution>(true,"success",killExecution);
        }catch(SecKillException e) {
            return new SeckillResult<KillExecution>(false,"秒杀错误",null);
        }
    }
}
