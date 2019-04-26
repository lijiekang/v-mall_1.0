package com.vmall.vtrade;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vmall.mapper")
public class VTradeApplication {

    public static void main(String[] args) {

        System.out.println();
        SpringApplication.run(VTradeApplication.class, args);
    }
}
