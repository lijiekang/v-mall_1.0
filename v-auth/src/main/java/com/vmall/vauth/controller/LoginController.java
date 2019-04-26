package com.vmall.vauth.controller;

import com.sun.xml.internal.ws.wsdl.writer.document.ParamType;
import com.vmall.pojo.VUesr;
import com.vmall.pojo.VUserAddress;
import com.vmall.vauth.service.LoginService;
import com.vmall.vauth.service.MailService;
import com.vmall.vauth.service.TokenService;
import com.vmall.vutil.SMSCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
        return "login";
    }
    @PostMapping("/dologin")
    public String dologin(@RequestParam(value = "userCode",required = true)String userCode, @RequestParam("password")String password, HttpSession session){
        if(loginService.login(userCode,password)!=false){
            return "redirect:getproduct";
        }
        session.setAttribute("error","用户名或密码错误");
        return "login";
    }
    @GetMapping("/forgotPwd")
    public String forgotPwd(){
        return "forgot-password";
    }
    @GetMapping("/getCode")
    @ResponseBody
    public String getCode(@RequestParam("email")String email){
        Integer code1= new Random().nextInt(1000000);
        mailService.sendMail(email,"验证码","您收到的验证码为:",code1);
        return "验证码已发送";
    }
    @GetMapping("/getEmail")
    @ResponseBody
    public String getEmail(@RequestParam("email")String email){
        if (loginService.findEmail(email)!=null){
            return "true";
        }
        return "false";
    }
    @PostMapping("/findPwd")
    public String findPassword(@RequestParam("email")String email,@RequestParam("code")String code,@RequestParam("newpwd")String newpwd){
        loginService.findPassword(code,email,newpwd);
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/regist")
    public String regist(MultipartFile file,VUesr vUesr,@RequestParam("phoneCode")String phoneCode){
        String realPath="F:/Vmall/VmallImage/userportrait/";
        File folder=new File(realPath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String oldName=file.getOriginalFilename();
        String newName=oldName.substring(oldName.indexOf("."),oldName.length());
        try {
            file.transferTo(new File(folder,newName));
            vUesr.setvHeadPath(newName);
            if(loginService.register(vUesr,phoneCode)!=false){
                return "login";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "register";
    }
    @GetMapping("/getPhoneCode")
    @ResponseBody
    public String getPhoneCode(@RequestParam("phone")String phone){
        Integer code1= new Random().nextInt(1000000);
        try {
            SMSCode.sendCode(phone,code1.toString());
            tokenService.setCode(phone,code1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "验证码已发送";
    }

    public void test(){

    }
}
