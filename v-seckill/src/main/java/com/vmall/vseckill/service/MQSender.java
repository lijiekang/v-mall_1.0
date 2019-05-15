package com.vmall.vseckill.service;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.message.MQMessage;
import com.vmall.vseckill.config.MQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 李秸康
 * @Description: 消息发送者
 * @Date created in 20:20 2019-04-30
 * @Modifyied By:
 */
@Service
public class MQSender {
    @Autowired
    private AmqpTemplate amqpTemplate;


    public void send(MQMessage mqMessage){
        amqpTemplate.convertAndSend(MQConfig.QUEUE_NAME, JSONArray.toJSONString(mqMessage));
    }
}
