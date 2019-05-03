package com.vmall.vauth.service;

import com.vmall.mapper.user.UserMapper;
import com.vmall.pojo.VUser;
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
        VUser uesr=userMapper.login(userCode);
        String pwd=MD5Utils.twoEncryption(MD5Utils.oneEncryption(password),uesr.getvSalt());
        if (pwd.equals(uesr.getvPassword())&&uesr!=null){
            return tokenService.set(uesr);
        }
        return "false";
    }
    public VUser findEmail(String email){
        return userMapper.findEmail(email);
    }
    public boolean findPassword(String code,String email,String password){
        Object obj=tokenService.get(email);
        if(String.valueOf(obj).equals(code)&&obj!=null){
            VUser uesr=userMapper.findEmail(email);
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
    public boolean register(VUser vUesr,String phonecode){
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
