package com.vmall.vtrade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.vmall.mapper","com.vmall.dao"})
public class VTradeApplication {

    public static void main(String[] args) {
        SpringApplication.run(VTradeApplication.class, args);
    }

}
