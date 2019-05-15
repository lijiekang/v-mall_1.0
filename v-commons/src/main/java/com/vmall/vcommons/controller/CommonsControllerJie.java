package com.vmall.vcommons.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pagezhang;
import com.vmall.pojo.VCategory;
import com.vmall.pojo.VCommons;
import com.vmall.pojo.VProduct;
import com.vmall.pojo.vo.CommonsVO;
import com.vmall.vcommons.service.CommonsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.*;

/**
 * 评论接口
 * 张振宇
 */
//@RestController
public class CommonsControllerJie {
    @Autowired
    CommonsService commonsService;

    /**
     * 进入评论界面
     * @param id
     * @param pageno 传递页数
     */
    @RequestMapping("/incommons")
    public String getCommonsTest( @RequestParam(value = "id",required = false)String id, @RequestParam(value = "pageno",required = false)String pageno){
        int pagen=0;
        int pid=0;
        /**
         * 评论总数
         */
        int pageC=commonsService.getPageCount();
        Map<String,Object>map=new HashMap<>();
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
        /**
         * 一级分类集合
         */
        List<VCategory> level1=commonsService.getVCategoryLevel1();
        /**
         * 评论集合
         */
        List<VCommons>commonsList=commonsService.getAllCommonsById(pid,index);
        CommonsVO commonsVO=new CommonsVO();
        commonsVO.setPage(page);
        commonsVO.setLevel1(level1);
        commonsVO.setCommonsList(commonsList);


        return JSON.toJSONString(commonsVO);
        /**
         * 返回页面
         */
//        return "tables";
    }

    /**
     * 返回二三级分类集合
     * @param lv
     */
    @GetMapping(value = "/tlevel/{lv}",produces = "application/json;charset=UTF-8")
    public String getTitleLevel(@PathVariable String lv){
        /**
         * 分类ID
         */
        int lv2=Integer.parseInt(lv);
        /**
         * 获得下级分类集合
         */
        List<VCategory>level2=commonsService.getVCategoryLevel2(lv2);
//        String str= JSONArray.toJSONString(level2);
       return JSON.toJSONString(level2);
//        return str;
    }
    //tproduct

    /**
     * 三级分类下的商品列表
     * @param lv 三级分类的ID
     */
    @GetMapping(value = "/tproduct/{lv}",produces = "application/json;charset=UTF-8")
    public String getProductList(@PathVariable String lv){
        int pid=Integer.parseInt(lv);
        List<VProduct>productList=commonsService.getProductById(pid);
        String str= JSONArray.toJSONString(productList);

        return str;
    }

    /**
     *
     * @param lv
     */
    @GetMapping(value = "/tcommons/{lv}",produces = "application/json;charset=UTF-8")
    public String getCommonsList(@PathVariable String lv){
        int pid=Integer.parseInt(lv);
        List<VCommons>commonsList=commonsService.getAllCommonsById(pid,0);
        String str= JSONArray.toJSONString(commonsList);
       return str;
    }

    /**
     * 删除评论
     * @param vCommonsId 评论ID
     * @return
     */
    @GetMapping(value = "/delCommons/{vCommonsId}",produces = "application/json;charset=UTF-8")
    public String getDelCommons(@PathVariable String vCommonsId){
       Map<String,Object>map=new HashMap<>();
        List<VCategory>level1=commonsService.getVCategoryLevel1();
        List<VCommons>commonsList=commonsService.getAllCommonsById(0,0);
        int vComm=Integer.parseInt(vCommonsId);
        VCommons vCommons=commonsService.getMoCommons(vComm);
        int vCommon= commonsService.getSelectInProduct((int)vCommons.getvUserId(),(int)vCommons.getvOrderId(),(int)vCommons.getvProductId());
        int result=0;
        result=commonsService.getDelCommonts(vComm);
        if(vCommon>0){
            commonsService.getUpdateCommonsCount(vCommon-1,(int)vCommons.getvProductId());
        }
        CommonsVO commonsVO=new CommonsVO();
        commonsVO.setLevel1(level1);
        commonsVO.setCommonsList(commonsList);
        return JSON.toJSONString(commonsVO);
        /**
         * 返回页面
         */
//        return "redirect:/incommons";
    }

    /**
     * 新增评论
     * @param vCommons 评论信息
     */
    @PostMapping(value = "/addCommons")
    public String getAddCommons(@ModelAttribute VCommons vCommons){
        int result=0;
        result=commonsService.getAddCommons(vCommons);
        List<VCategory>level1=commonsService.getVCategoryLevel1();
        List<VCommons>commonsList=commonsService.getAllCommonsById(0,0);
//        model.addAttribute("level1",level1);
//        model.addAttribute("commonsList",commonsList);
        CommonsVO commonsVO=new CommonsVO();
        commonsVO.setLevel1(level1);
        commonsVO.setCommonsList(commonsList);
        return JSON.toJSONString(commonsVO);
        /**
         * 返回页面
         */
        //return "tables";
    }

    /**
     * 修改页面
     * @param vCommonsId 评论ID
     * @param vContent 评论信息
     */
    @PostMapping(value = "/updateCommons")
    public String getUpdateCommons(@RequestParam("vCommonsId")String vCommonsId,@RequestParam("vContent")String vContent){
        int pid=Integer.parseInt(vCommonsId);
        int result=commonsService.getUpdateCommons(pid,vContent);

        /**
         * 返回页面
         */
       return "redirect:/incommons";
    }

    /**
     * 进入某条评论的修改页面
     * @param vCommonsId 评论ID
     * @return
     */
    @GetMapping(value = "/upCommons/{vCommonsId}")
    public String upCommons(@PathVariable String vCommonsId){
        int pid=Integer.parseInt(vCommonsId);
//        Map<String,Object>map=new HashMap<>();
        VCommons vCommons=commonsService.getMoCommons(pid);
//        model.addAttribute("vCommons",vCommons);
//        map.put("vCommons",vCommons);
        return JSON.toJSONString(vCommons);
        /**
         * 返回页面
         */
//        return "update";
    }

    /**
     * 进入新增评论页面
     * @param vOrderId 订单ID，通过订单ID获取订单下商品信息进行评论
     * @param httpServletResponse
     */
    @GetMapping(value = "add/{vOrderId}")
    public String addCommons(@PathVariable String vOrderId,HttpServletResponse httpServletResponse){
        Map<String,Object>map=new HashMap<>();
        int oid=Integer.parseInt(vOrderId);
        /**
         * 返回页面
         */
        return "add";
    }

    @GetMapping("/addCommons")
//    @ResponseBody
    public String addCommonsList(String vProductId,String vContent,String vOrderId,String vUserId,String vGrade,String vIsOK){
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
        int resultss=commonsService.getSelectInProduct(vuid,vorderid,vpid);

        int result=commonsService.getAddCommons(vCommons);
        if(resultss>0){
            int re=commonsService.getUpdateCommonsCount(resultss+1,vpid);
        }else {
            int re=commonsService.getUpdateCommonsCount(1,vpid);
        }
        if(result>0){
            molist.put("mm","succes");
        }else {
            molist.put("mm","nosucces");
        }
        String str=JSONArray.toJSONString(molist);

        /**
         * 返回数据
         */
        return str;
    }

    /**
     * 进入回复评论页面
     * @param vCommonsId 评论ID
     * @return
     */
    @GetMapping(value = "/upHuiCommons/{vCommonsId}")
    public String upHuiCommons(@PathVariable String vCommonsId){
        int pid=Integer.parseInt(vCommonsId);
        Map<String,Object>map=new HashMap<>();
        VCommons vCommons=commonsService.getMoCommons(pid);
        map.put("vCommons",vCommons);
//        model.addAttribute("vCommons",vCommons);
        return JSON.toJSONString(map);
//        return "huifucommons";
    }
    @PostMapping(value = "/updateHuiCommons")
    public String getUpdateHuiCommons(Model model,@RequestParam("vCommonsId")String vCommonsId,@RequestParam("vReply")String vReply){
        int pid=Integer.parseInt(vCommonsId);
        Map<String,Object>map=new HashMap<>();
        int result=commonsService.getUpdateHuiCommons(pid,vReply);

        /**
         * 返回页面
         */
        return "redirect:/incommons";
    }
    //某件商品下某位用户和商家的全部评论
//    @ResponseBody

    /**
     * 进行追评，并获取某件商品下某位用户和商家的全部评论
     * @param userId
     * @param OrderId
     * @param productId
     * @return
     */
    @GetMapping("/getZhui")
    public String getVcommZhui(@RequestParam("userId") String userId,@RequestParam("OrderId")String OrderId,@RequestParam("productId")String productId){
        Map<String,Object>map=new HashMap<>();
        int uid=0;
        int oid=0;
        int pid=0;
        List<VCommons>getVcomm=new ArrayList<>();
        if(userId!=null){
            uid=Integer.parseInt(userId);
        }
        if(OrderId!=null){
            oid=Integer.parseInt(OrderId);
        }
        if(productId!=null){
            pid=Integer.parseInt(productId);
        }
        getVcomm=commonsService.getVcommZhui(uid,oid,pid);
        String str= JSON.toJSONString(getVcomm);

        return str;
    }

    //删除某件商品下的某个商品模块
//    @ResponseBody

    /**
     * 删除某件商品下的某个商品模块
     * @param userId 用户ID
     * @param OrderId 订单ID
     * @param productId 商品ID
     * @return
     */
    @GetMapping("/getDelMoping")
    public String getDelMoping(@RequestParam("userId") String userId,@RequestParam("OrderId")String OrderId,@RequestParam("productId")String productId){
        Map<String,Object>map=new HashMap<>();
        int uid=0;
        int oid=0;
        int pid=0;
        int result=0;
        if(userId!=null){
            uid=Integer.parseInt(userId);
        }
        if(OrderId!=null){
            oid=Integer.parseInt(OrderId);
        }
        if(productId!=null){
            pid=Integer.parseInt(productId);
        }
        result=commonsService.getDelMoping(uid,oid,pid);
        if(result>0){
            map.put("shan","删除成功");
        }else {
            map.put("shan","删除失败");
        }
       String str= JSON.toJSONString(map);
        /**
         * 返回判断数据
         */
        return str;
    }
}
