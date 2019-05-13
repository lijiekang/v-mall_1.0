package com.vmall.vtrade.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vmall.vproducts.service.vproduct_trade.VProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 23:41 2019-05-10
 * @Modifyied By:
 */
@RestController
public class TestController {

    @Reference(version = "1.0.0")
    VProductService vProductService;

    @GetMapping(value = "/test")
    public String hello(){
        System.out.println(vProductService);
        return "fsdfds";
    }
}
