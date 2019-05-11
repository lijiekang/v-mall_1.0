package com.vmall.vproducts.controller.vactivity;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VActivity;
import com.vmall.vproducts.service.vactivity.VActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class VActivityController {
    @Autowired
    VActivityService vActivityService;
    @RequestMapping("/activity")
    public String activity(@RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, Model model){
        int count=vActivityService.count();
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VActivity>vActivitielist=vActivityService.listvActivity(page);
        model.addAttribute("vActivitielist",vActivitielist);
        model.addAttribute("page",page.getPageNo());
        model.addAttribute("totalCount",page.getPageye());
        return "activity";
    }
    @RequestMapping("toactivityupd")
    public String toactivityupd(String vActivityId,Model model){
        VActivity vActivity=vActivityService.vActivityid(Integer.valueOf(vActivityId));
        model.addAttribute("vActivity",vActivity);
        return "updactivity";
    }
    @RequestMapping("activityupd")
    public String activityupd(VActivity vActivity){
        int upd=vActivityService.updvActivity(vActivity);
        if(upd>0){
            return "redirect:/activity";
        }
        return "updactivity";
    }
    @RequestMapping("/delActivity")
    @ResponseBody
    public Object delActivity(String vActivityId){
        String flag="";
        int del=vActivityService.delvActivity(Integer.valueOf(vActivityId));
        if (del>0){
            flag="true";
        }else{
            flag="false";
        }
        return JSONArray.toJSONString(flag);
    }
    @RequestMapping("/tovactivitieadd")
    public String tovactivitieadd(){
        return "addactivity";
    }
    @RequestMapping("/addAcativity")
    public String addAcativity(VActivity vActivity){
        int add=vActivityService.addvActivity(vActivity);
        if(add>0){
            return "redirect:/activity";
        }
        return "addactivity";
    }
}
