package com.vmall.vauth.service.dev;

import com.vmall.mapper.questionmapper.QuesUserMapper;
import com.vmall.mapper.questionmapper.QuestionMapper;
import com.vmall.mapper.user.UserMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VBrowsinghistory;
import com.vmall.pojo.VQuestion;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.tool.TokenService;
import com.vmall.vutil.MD5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Service
public class DevUserService {
    @Resource
    TokenService tokenService;
    @Resource
    UserMapper userMapper;
    @Resource
    QuestionMapper questionMapper;
    @Resource
    QuesUserMapper quesUserMapper;
    public int count(){
        return questionMapper.count();
    }
    public int historycount(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return userMapper.count(vUser.getvUserId());
    }
    public VUser getVUesrById(String token){
        VUser vUesr=(VUser)tokenService.get(token);
        return userMapper.getVUesrById(vUesr.getvUserId());
    }
    public List<VQuestion> allQuestion(Page page){
        return questionMapper.allQuestion((page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int addQuestion(int quesId,String token,String answer){
        VUser vUesr=(VUser)tokenService.get(token);
        return quesUserMapper.insert(vUesr.getvUserId(),quesId,answer);
    }
    public int updProfile(VUser vUesr){
        return userMapper.updProfile(vUesr);
    }
    public boolean updPwd(String token,String password){
        VUser vUesr=(VUser)tokenService.get(token);
        String onePwd= MD5Utils.oneEncryption(password);
        String salt = "";
        for (int i = 0;i<3;i++){
            salt = salt+ (char)(Math.random()*26+'A');
        }
        salt+=new Random().nextInt(1000);
        String towPwd=MD5Utils.twoEncryption(onePwd,salt);
        if(userMapper.findPassword(vUesr.getvUserId(),towPwd,salt)>0){
            return true;
        }
        return false;
    }
    public List<VBrowsinghistory> myFootHistory(String token, Page page){
        VUser uesr=(VUser) tokenService.get(token);
        return userMapper.MyfootHistory(uesr.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public VBrowsinghistory addFootHistory(String token,VBrowsinghistory history){
        VUser uesr=(VUser) tokenService.get(token);
        history.setvUserId(uesr.getvUserId());
        userMapper.addFootHistory(history);
        return history;
    }
    public String updPhoneNumber(String token,String phoneNumber,String phoneCode){
        VUser vUser=(VUser)tokenService.get(token);
        Object phonecode=tokenService.get(phoneNumber);
        if(userMapper.findPhoneNumber(phoneNumber)>0){
            return "手机号已存在";
        }
        if(phonecode!=null&&phoneCode!=""&&phoneCode.equals(phonecode.toString())){
            if(userMapper.updPhoneNumber(phoneNumber,vUser.getvUserId())>0){
                return "绑定成功";
            }
        }
        return "验证码不存在";
    }
    public String updEmail(String token,String email,String emailCode){
        VUser vUser=(VUser)tokenService.get(token);
        Object emailcode=tokenService.get(email);
        if(userMapper.findEmail(email)!=null){
            return "邮箱已存在";
        }
        if(emailcode.toString().equals(emailCode)&&emailcode!=null){
            if(userMapper.updEmail(email,vUser.getvUserId())>0){
                return "绑定成功";
            }
        }
        return "验证码错误";
    }
}
