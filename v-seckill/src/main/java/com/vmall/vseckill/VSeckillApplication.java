package com.vmall.vseckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.vmall.mapper.seckill")
public class VSeckillApplication {

    public static void main(String[] args) {
        SpringApplication.run(VSeckillApplication.class, args);
    }

}
