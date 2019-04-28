package com.vmall.vproducts;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.vmall.mapper.*")
public class VProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(VProductsApplication.class, args);
    }

}
