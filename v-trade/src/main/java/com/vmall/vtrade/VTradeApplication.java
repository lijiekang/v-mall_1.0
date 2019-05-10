package com.vmall.vtrade;

import com.unionpay.acp.sdk.SDKConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.vmall.mapper")
@EnableScheduling
public class VTradeApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(VTradeApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}
