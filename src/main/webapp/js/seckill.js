/**
 * Created by Administrator on 2016/8/10.
 */
var seckill={
 urls:{
  now:function(){
   return "/seckill/currentTime";
  },
  md5:function(goodsId){
   return "/seckill/getExplosion/"+goodsId;
  },
  kill:function(md5,goodsId){
   return "/seckill/"+goodsId+"/"+md5+"/killExecute";
  }
 },
 handleKill: function (goodsId) {
  $.get(seckill.urls.md5(goodsId),{},function(data){
   if(data['result']){
    var md5=data['data']['md5'];
    alert("md5="+md5);
    var killButton=$("#kill");
    killButton.removeAttr("disabled");
    killButton.click(function () {
     $.post(seckill.urls.kill(md5,goodsId),{},function(data){
      if(data['result']){
       alert("恭喜你秒杀成功");
      }
     })
    })
   }
  })
 },
 countDown:function(startTime,endTime,now,goodsId){
  alert("st="+startTime);
  alert("et="+endTime);
  alert("now="+now);
  var seckillBox=$("#seckill-box");
  if(now<startTime){
   seckillBox.countdown(startTime,function(event){
    //时间格式
    var format=event.strftime("秒杀倒计时：%D天 %H时 %M分 %S秒");
    seckillBox.html(format);
   }).on("finish.countdown",function(){
    //倒计时结束，获取秒杀地址
    seckill.handleKill(goodsId);
   })
  }
  else if(now>endTime){
   seckillBox.html("秒杀结束");
  }
  else{
   seckillBox.html("秒杀正在进行");
   seckill.handleKill(goodsId);
  }
 },
 validatePhone:function(userPhone){
  if(userPhone&&userPhone.length==11&&!isNaN(userPhone)){
   return true;
  }else{
   return false;
  }
 },
 detail:{
  init:function(params){
   var userPhone= $.cookie("userPhone");
   //检查用户有没有登录
   if(!seckill.validatePhone(userPhone)) {
    var m=$("#myModal");
    m.modal({
     show:true,
     backdrop:false
    });
    $("#modal-confirm").click(function(){
     var phone= $("#phone").val();
     if(seckill.validatePhone(phone)){
      $.cookie("userPhone",phone,{expires:7,path:"/seckill"});
      window.location.reload();
     }
     else{
      $("#message").hide().html('<label class="label label-danger">手机号错误</label>').show();
     }
    });
   }
   else{
    var goodsId=params['goodsId'];
    var startTime=params['startTime'];
    var endTime=params['endTime'];
//已登录，显示倒计时
    $.get(seckill.urls.now(),{},function(result){
     if(result&&result['result']){
      alert(2);
      seckill.countDown(startTime,endTime,result['data'],goodsId);
     }else{

     }
    })
   }
  }
 }
}