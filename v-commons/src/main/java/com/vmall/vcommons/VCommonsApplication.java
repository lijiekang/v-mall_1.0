package com.vmall.vcommons;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vmall")
public class VCommonsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VCommonsApplication.class, args);
    }

}
