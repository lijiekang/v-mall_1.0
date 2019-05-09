package com.vmall.vauth.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.LoginService;
import com.vmall.vauth.service.tool.MailService;
import com.vmall.vauth.service.tool.TokenService;
import com.vmall.vutil.SMSCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Controller
//@Api(tags = "会员登录注册接口")
public class LoginController {
    @Resource
    LoginService loginService;
    @Resource
    MailService mailService;
    @Resource
    TokenService tokenService;
//    @ApiOperation(value = "会员登录")
    @GetMapping("/login")
    public String login(HttpSession session){
        session.setAttribute("error",null);
        return "back/login";
    }
    @PostMapping("/dologin")
    @ResponseBody
    public String dologin(@RequestParam(value = "userCode")String userCode, @RequestParam("password")String password,HttpServletResponse response){
        String token=loginService.login(userCode,password);
        if(token!="false"){
            Cookie cookie=new Cookie("token",token.toString());
            cookie.setMaxAge(15*60);
            response.addCookie(cookie);
            return JSONArray.toJSONString("true");
        }
        return JSONArray.toJSONString("用户名或密码错误");
    }
    @GetMapping("/forgotPwd")
    public String forgotPwd(){
        return "back/forgot-password";
    }
//    @GetMapping("/getCode")//发送邮箱验证码
//    @ResponseBody
//    public String getCode(@RequestParam("email")String email){
//        Integer code1= new Random().nextInt(1000000);
//        mailService.sendMail(email,"验证码","您收到的验证码为:",code1);
//        return "验证码已发送";
//    }
    @GetMapping("/getEmail")
    @ResponseBody
    public String getEmail(@RequestParam("email")String email){
        if (loginService.findEmail(email)!=null){//判断是否存在有该邮箱的账号
            return "true";
        }
        return "false";
    }
    @PostMapping("/findPwd")//找回密码
    public String findPassword(@RequestParam("email")String email,@RequestParam("code")String code,@RequestParam("newpwd")String newpwd){
        loginService.findPassword(code,email,newpwd);
        return "back/login";
    }
    @GetMapping("/register")
    public String register(){
        return "back/register";
    }
    /*
    * 注册用户
    * */
    @PostMapping("/regist")
    public String regist(MultipartFile file, VUser vUesr, @RequestParam("phoneCode")String phoneCode){
        String realPath="F:/Vmall/VmallImage/userportrait/";
        File folder=new File(realPath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName=file.getOriginalFilename();
        String newName=oldName.substring(oldName.indexOf("."),oldName.length());
        try {
            file.transferTo(new File(folder,vUesr.getvUsercode()+newName));
            vUesr.setvHeadPath(vUesr.getvUsercode()+newName);
            if(loginService.register(vUesr,phoneCode)!=false){
                return "back/login";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "back/register";
    }
    /*
    * 获取手机验证码
    * */
//    @PostMapping("/getPhoneCode")
//    @ResponseBody
//    public String getPhoneCode(@RequestParam("phone")String phone){
//        Integer code1= new Random().nextInt(1000000);
//        try {
//            SMSCode.sendCode(phone,code1.toString());
//            tokenService.setCode(phone,code1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "验证码已发送";
//    }


}
