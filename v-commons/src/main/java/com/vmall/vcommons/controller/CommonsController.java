package com.vmall.vcommons.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pagezhang;
import com.vmall.pojo.VCategory;
import com.vmall.pojo.VCommons;
import com.vmall.pojo.VProduct;
import com.vmall.vcommons.service.CommonsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.util.*;

/**
 * create by 张振宇
 * 评论接口
 *
 */

@Controller
public class CommonsController {

    @Autowired
    CommonsService commonsService;
    @RequestMapping("/incommons")
    public String getCommonsTest(Model model,@RequestParam(value = "id",required = false)String id,@RequestParam(value = "pageno",required = false)String pageno){
        int pagen=0;
        int pid=0;
        int pageC=commonsService.getPageCount();
        System.out.println(pageC);
        Pagezhang page=new Pagezhang();
        page.setRecordCount(pageC);
        if(pageno!=null&&pageno!=""){
            pagen=Integer.parseInt(pageno);
            page.setPageno(pagen);
        }
        int index=pagen*10;
        if(id!=null&&id!=""){
            pid=Integer.parseInt(id);
        }
        List<VCategory>level1=commonsService.getVCategoryLevel1();
        List<VCommons>commonsList=commonsService.getAllCommonsById(pid,index);
        model.addAttribute("page",page);
        model.addAttribute("level1",level1);
        model.addAttribute("commonsList",commonsList);
        return "tables";
    }

    @GetMapping(value = "/tlevel/{lv}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getTitleLevel(@PathVariable String lv){
        int lv2=Integer.parseInt(lv);
        List<VCategory>level2=commonsService.getVCategoryLevel2(lv2);
        String str= JSONArray.toJSONString(level2);
        return str;
    }
    //tproduct
    @GetMapping(value = "/tproduct/{lv}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getProductList(@PathVariable String lv){
        int pid=Integer.parseInt(lv);
       List<VProduct>productList=commonsService.getProductById(pid);
        String str= JSONArray.toJSONString(productList);
        return str;
    }

    @GetMapping(value = "/tcommons/{lv}",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object getCommonsList(@PathVariable String lv){
        int pid=Integer.parseInt(lv);
        List<VCommons>commonsList=commonsService.getAllCommonsById(pid,0);
        String str= JSONArray.toJSONString(commonsList);
        return str;
    }
    @GetMapping(value = "/delCommons/{vCommonsId}",produces = "application/json;charset=UTF-8")
    public String getDelCommons(Model model,@PathVariable String vCommonsId){
        System.out.println(vCommonsId);
    List<VCategory>level1=commonsService.getVCategoryLevel1();
    List<VCommons>commonsList=commonsService.getAllCommonsById(0,0);
    int vComm=Integer.parseInt(vCommonsId);
    int result=0;
        result=commonsService.getDelCommonts(vComm);
        System.out.println(result);
        model.addAttribute("level1",level1);
        model.addAttribute("commonsList",commonsList);
        return "redirect:/incommons";
    }
    @PostMapping(value = "/addCommons")
    public String getAddCommons(Model model,@ModelAttribute VCommons vCommons){
        int result=0;
        result=commonsService.getAddCommons(vCommons);
        List<VCategory>level1=commonsService.getVCategoryLevel1();
        List<VCommons>commonsList=commonsService.getAllCommonsById(0,0);
        model.addAttribute("level1",level1);
        model.addAttribute("commonsList",commonsList);
        return "tables";
    }
    @PostMapping(value = "/updateCommons")
    public String getUpdateCommons(Model model,@RequestParam("vCommonsId")String vCommonsId,@RequestParam("vContent")String vContent){
        int pid=Integer.parseInt(vCommonsId);
        int result=commonsService.getUpdateCommons(pid,vContent);
        System.out.println(result);
        return "redirect:/incommons";
    }
    @GetMapping(value = "/upCommons/{vCommonsId}")
    public String upCommons(Model model,@PathVariable String vCommonsId){
        int pid=Integer.parseInt(vCommonsId);
        VCommons vCommons=commonsService.getMoCommons(pid);
        model.addAttribute("vCommons",vCommons);
        return "update";
    }
    @GetMapping(value = "add/{vOrderId}")
    public String addCommons(@PathVariable String vOrderId){
        int oid=Integer.parseInt(vOrderId);
        return "add";
    }

    @GetMapping("/addCommons")
    @ResponseBody
    public Object addCommonsList(String vProductId,String vContent,String vOrderId,String vUserId,String vGrade,String vIsOK){
        Map<String,Object> molist=new HashMap<>();
        int vpid=Integer.parseInt(vProductId);
        int vorderid=Integer.parseInt(vOrderId);
        int vuid=Integer.parseInt(vUserId);
        int vgrade=Integer.parseInt(vGrade);
        int visok=Integer.parseInt(vIsOK);
        VCommons vCommons=new VCommons();
        vCommons.setvContent(vContent);
        vCommons.setvProductId(vpid);
        vCommons.setvOrderId(vorderid);
        vCommons.setvUserId(vuid);
        vCommons.setvGrade(vgrade);
        vCommons.setvIsOk(visok);
        vCommons.setvCreateDate(new Timestamp(new Date().getTime()));
        int result=commonsService.getAddCommons(vCommons);
        if(result>0){
            molist.put("mm","succes");
        }else {
            molist.put("mm","nosucces");
        }
        String str=JSONArray.toJSONString(molist);
        return str;
    }
    @GetMapping(value = "/upHuiCommons/{vCommonsId}")
    public String upHuiCommons(Model model,@PathVariable String vCommonsId){
        int pid=Integer.parseInt(vCommonsId);
        VCommons vCommons=commonsService.getMoCommons(pid);
        model.addAttribute("vCommons",vCommons);
        return "huifucommons";
    }
    @PostMapping(value = "/updateHuiCommons")
    public String getUpdateHuiCommons(Model model,@RequestParam("vCommonsId")String vCommonsId,@RequestParam("vReply")String vReply){
        int pid=Integer.parseInt(vCommonsId);
        int result=commonsService.getUpdateHuiCommons(pid,vReply);
        System.out.println(result);
        return "redirect:/incommons";
    }
}
