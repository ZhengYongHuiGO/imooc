CREATE DATABASE seckill2;

use seckill2;

CREATE TABLE kill_goods(
  `id` INT UNIQUE NOT NULL COMMENT '秒杀商品表',
  `price` int UNIQUE NOT NULL COMMENT '价格',
  `total_num` INT UNIQUE NOT NULL COMMENT '总库存',
  `goods_name` VARCHAR(100) COMMENT '商品名称',
  `start_time` TIMESTAMP COMMENT '秒杀开始时间',
  `end_time` TIMESTAMP COMMENT '结束时间',
  `create_time` TIMESTAMP COMMENT '商品创建时间',
  PRIMARY KEY (id),
  KEY idx_start_time(start_time),
  KEY idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE =InnoDB AUTO_INCREMENT=100 DEFAULT CHAR SET =utf8 COMMENT '商品库存表';
INSERT INTO kill_goods(price,total_num,goods_name,start_time,end_time,create_time)
    VALUES
      (1000,39,'1000元秒杀iphone6','2016-07-01','2016-08-08','2016-07-28'),
      (1500,49,'1500元秒杀iphone6s','2016-07-01','2016-08-08','2016-07-28'),
      (800,48,'800元秒杀小米5','2016-07-01','2016-08-08','2016-07-28'),
      (1300,45,'700元秒杀红米note','2016-07-01','2016-08-08','2016-07-28');

CREATE TABLE success_kill_record(
  `phone` BIGINT UNIQUE NOT NULL COMMENT '用户的电话（id）',
  `goods_id` INT UNIQUE NOT NULL COMMENT '商品id',
  `state` INT UNIQUE DEFAULT -1 NOT NULL COMMENT '-1无效 0未支付 1已支付 2未发货 3 已发货',
  `kill_time` TIMESTAMP UNIQUE COMMENT '秒杀成功的时间',
  PRIMARY KEY (phone,goods_id),
  KEY id_kill_time(kill_time)
)ENGINE =InnoDB DEFAULT CHAR SET =utf8 COMMENT '秒杀成功结果记录表';
