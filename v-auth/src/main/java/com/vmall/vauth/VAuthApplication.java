package com.vmall.vauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vmall.mapper")
public class VAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(VAuthApplication.class, args);
    }

}
