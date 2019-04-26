package com.vmall.vauth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
<<<<<<< HEAD
@MapperScan("com.vmall.mapper")
=======
@MapperScan(value = {"com.v_mall.dao"})
>>>>>>> 5696fbfb8c6a8f3165efbc3cbbcf59655bfa48fc
public class VAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(VAuthApplication.class, args);
    }

}
