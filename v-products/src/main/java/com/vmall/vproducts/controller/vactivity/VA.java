package com.vmall.vproducts.controller.vactivity;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VActivity;
import com.vmall.vproducts.service.vactivity.VActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * 商品活动
 */
@RestController
@RequestMapping("/vactivity")
public class VA {
    @Autowired
    VActivityService vActivityService;
    @GetMapping("/activity")//查询所有的商品活动
    public void activity(@RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, HttpServletResponse httpServletResponse){
        int count=vActivityService.count();
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VActivity>vActivitielist=vActivityService.listvActivity(page);

        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vActivitielist));
            os.writeObject(JSONArray.toJSON(page.getPageNo()));
            os.writeObject(JSONArray.toJSON(page.getPageye()));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("toactivityupd")//去修改商品活动
    public void toactivityupd(String vActivityId,HttpServletResponse httpServletResponse){
        VActivity vActivity=vActivityService.vActivityid(Integer.valueOf(vActivityId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vActivity));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("activityupd")//修改商品活动
    public void activityupd(VActivity vActivity,HttpServletResponse httpServletResponse){
        String flag="";
        int upd=vActivityService.updvActivity(vActivity);
        if(upd>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/delActivity")//删除商品活动
    @ResponseBody
    public void delActivity(String vActivityId,HttpServletResponse httpServletResponse){
        String flag="";
        int del=vActivityService.delvActivity(Integer.valueOf(vActivityId));
        if (del>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /*@RequestMapping("/tovactivitieadd")
    public String tovactivitieadd(){
        return "addactivity";
    }*/
    @GetMapping("/addAcativity")//添加商品活动
    public void addAcativity(VActivity vActivity,HttpServletResponse httpServletResponse){
        String flag="";
        int add=vActivityService.addvActivity(vActivity);
        if (add>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
