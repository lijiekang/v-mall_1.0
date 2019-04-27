package com.vmall.vauth.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VQuestion;
import com.vmall.pojo.VUesr;
import com.vmall.vauth.service.dev.DevUserService;
import com.vmall.vauth.service.tool.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(tags = "会员中心接口")
public class MemberController {
    @Resource
    DevUserService devUserService;
    @Resource
    TokenService tokenService;
    @GetMapping("currentVuser")
    @ApiOperation("用户个人资料数据")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String Vuesr(HttpServletRequest request){
        String token=request.getHeader("token");
        VUesr vUesr=devUserService.getVUesrById(token);//空为没有这些状态的订单数量
        return JSONArray.toJSONString(vUesr);
    }
    @GetMapping("question")
    @ApiOperation("安全问题列表")
    public String questionlist(){
        List<VQuestion> list=devUserService.allQuestion();
        return JSONArray.toJSONString(list);
    }
    @GetMapping("addQuest")
    @ApiOperation("安全问题设置")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addQuest(@RequestParam("questId")String questId,@RequestParam("answer")String answer,HttpServletRequest request){
        String token=request.getHeader("token");
        devUserService.addQuestion(Integer.valueOf(questId),token,answer);
        return JSONArray.toJSONString("设置成功");
    }
    @GetMapping("tokenDisplace")
    @ApiOperation("token")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public void tokenDisplace(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("token");
        VUesr vUesr=(VUesr)tokenService.get(token);
        if(vUesr!=null) {
            tokenService.set(vUesr);
            Cookie cookie = new Cookie("token", token.toString());
            cookie.setMaxAge(15 * 60);
            response.addCookie(cookie);
            tokenService.del(token);
        }

    }
}
