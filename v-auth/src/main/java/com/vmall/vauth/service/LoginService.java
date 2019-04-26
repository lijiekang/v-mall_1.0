package com.vmall.vauth.service;

import com.vmall.mapper.User.UserMapper;
import com.vmall.pojo.VUesr;
import com.vmall.vutil.SMSCode;
import jdk.nashorn.internal.parser.Token;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LoginService {
    @Resource
    UserMapper userMapper;
    @Resource
    TokenService tokenService;
    public boolean login(String userCode,String password){
        VUesr uesr=userMapper.login(userCode,password);
        if (uesr!=null){
            tokenService.set(uesr);
            return true;
        }
        return false;
    }
    public VUesr findEmail(String email){
        return userMapper.findEmail(email);
    }
    public boolean findPassword(String code,String email,String password){
        String obj=(String)tokenService.get(email);
        if(obj==code&&obj!=null){
            VUesr uesr=userMapper.findEmail(email);
            userMapper.findPassword(uesr.getvUserId(),password);
            return true;
        }else {
//            long timeout=tokenService.getexpire(code);
//            if (timeout<1){
//                return "验证码已过期";
//            }
            return false;
        }
    }
    public boolean register(VUesr vUesr,String phonecode){
        String code=(String)tokenService.get(vUesr.getvPhone());
        if(phonecode!=""&&phonecode!=null){
            if(code!=null&&code==phonecode){
                vUesr.setvIsActive(1);
                userMapper.register(vUesr);
            }else {
                return false;
            }
        }
        return true;
    }
}
