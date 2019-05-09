package com.vmall.vauth.config;

import com.vmall.mapper.user.UserMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ScheduConfig {
    @Resource
    UserMapper userMapper;
    @Scheduled(cron="0 0 1 * * ?")
    public void delFootHistory(){
        userMapper.delFootHistory();
        System.out.println("删除成功");
    }
}
