package com.vmall.vtrade.config;

import com.vmall.pojo.VOrder;
import com.vmall.vtrade.service.orderservice.VOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderSchedule {

    @Autowired
    VOrderService vOrderService;
    @Scheduled(cron = "0/10 * * * * ?")
    public void shudan(){
        vOrderService.upOrderStatus();
        System.out.println("刷单中.......");
    }
}
