package com.vmall.vweb;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan({"com.vmall.mapper.menu","com.vmall.vweb.mapper"})
//@EnableConfigurationProperties
public class VWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(VWebApplication.class, args);
    }
}
