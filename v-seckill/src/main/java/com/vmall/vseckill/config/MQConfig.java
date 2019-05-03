package com.vmall.vseckill.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 20:03 2019-04-30
 * @Modifyied By:
 */
@Configuration
public class MQConfig {

    public static final String QUEUE_NAME="queue";


    @Bean
    public Queue queue(){
        return new Queue(QUEUE_NAME,true);
    }



}
