package com.vmall.vseckill.service;

import com.alibaba.fastjson.JSON;
import com.vmall.pojo.message.MQMessage;
import com.vmall.vseckill.config.MQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 21:36 2019-04-30
 * @Modifyied By:
 */
@Service
public class MQReceiver {


    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    private Logger log= LoggerFactory.getLogger(this.getClass());

    //标识：是否售完 false 为未售完
    public static boolean flag=false;

    @RabbitListener(queues = MQConfig.QUEUE_NAME)
    public void recevice(String str){
        MQMessage mqMessage= JSON.parseObject(str,MQMessage.class);


        //从redis中预减库存
        if(!productService.haveRemain(mqMessage.getProductId())){
            return;
        }

        //判断用户是否重复秒杀得到
        if(orderService.isSeckilled(mqMessage.getvUesr().getvUserId(),mqMessage.getProductId())){
            return;
        }

        //进行下单操作
        try {
            orderService.seckillProduct((int)mqMessage.getvUesr().getvUserId(),mqMessage.getProductId());
        } catch (Exception e) {
            log.info(mqMessage.getvUesr().getvUserId()+"秒杀失败");
            log.debug("未知错误:"+e.getMessage());
        }


    }
}
