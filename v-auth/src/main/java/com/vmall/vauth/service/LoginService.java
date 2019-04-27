package com.vmall.vauth.service;

import com.vmall.mapper.User.UserMapper;
import com.vmall.pojo.VUesr;
import com.vmall.vauth.service.tool.TokenService;
import com.vmall.vutil.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class LoginService {
    @Resource
    UserMapper userMapper;
    @Resource
    TokenService tokenService;
    public String login(String userCode,String password){
        VUesr uesr=userMapper.login(userCode);
        String pwd=MD5Utils.twoEncryption(MD5Utils.oneEncryption(password),uesr.getvSalt());
        if (pwd.equals(uesr.getvPassword())&&uesr!=null){
            return tokenService.set(uesr);
        }
        return "false";
    }
    public VUesr findEmail(String email){
        return userMapper.findEmail(email);
    }
    public boolean findPassword(String code,String email,String password){
        Object obj=tokenService.get(email);
        if(String.valueOf(obj).equals(code)&&obj!=null){
            VUesr uesr=userMapper.findEmail(email);
            String onePwd=MD5Utils.oneEncryption(password);
            String salt = "";
            for (int i = 0;i<3;i++){
                salt = salt+ (char)(Math.random()*26+'A');
            }
            salt+=new Random().nextInt(1000);
            String towPwd=MD5Utils.twoEncryption(onePwd,salt);
            userMapper.findPassword(uesr.getvUserId(),towPwd,salt);
            return true;
        }else {
            return false;
        }
    }
    public boolean register(VUesr vUesr,String phonecode){
        Object code= tokenService.get(vUesr.getvPhone());
        if(phonecode!=""&&phonecode!=null){
            if(code!=null&&phonecode.equals(String.valueOf(code))){
                vUesr.setvIsActive(1);
                String onePwd=MD5Utils.oneEncryption(vUesr.getvPassword());
                String salt = "";
                for (int i = 0;i<3;i++){
                    salt = salt+ (char)(Math.random()*26+'A');
                }
                salt+=new Random().nextInt(1000);
                String towPwd=MD5Utils.twoEncryption(onePwd,salt);
                vUesr.setvPassword(towPwd);
                vUesr.setvSalt(salt);
                userMapper.register(vUesr);
            }else {
                return false;
            }
        }
        return true;
    }
}
