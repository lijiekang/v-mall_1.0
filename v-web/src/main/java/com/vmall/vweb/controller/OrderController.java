package com.vmall.vweb.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Page;
import com.vmall.pojo.VOrder;
import com.vmall.pojo.VSku;
import com.vmall.vutil.HttpUtils;
import com.vmall.vweb.beans.URLSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 14:39 2019-05-11
 * @Modifyied By:
 */
@RequestMapping(value="/vorder")
@Controller
public class OrderController {

    @Autowired
    private URLSettings urlSettings;


    /**
     * 返回订单列表信息（JSON）
     * @param no
     * @param size
     * @param usernames
     * @return
     */
    @RequestMapping(value="/orders")
    public String toOrderList(@RequestParam(value = "no",required = false)String no,
                              @RequestParam(value = "size",required = false)String size,
                              @RequestParam(value = "usernames",required = false)String usernames,
                              Model model){
        Map<String,Object> conditions=new HashMap<>();
        conditions.put("no",no);
        conditions.put("size",size);
        conditions.put("usernames",usernames);
        try {
            Page<VOrder> page= JSON.parseObject(HttpUtils.post(urlSettings.getOrder_list_url(),conditions),Page.class);
            model.addAttribute("page",page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "orders/order_list";
    }


    @GetMapping(value="/order")
    public String toAdd(Model model){
        try {
            List<VSku> skuList= JSONArray.parseArray(HttpUtils.get(urlSettings.getSku_list_url()),VSku.class);
            model.addAttribute("skuList",skuList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "orders/addorder";
    }


    /**
     * 订单新增（补单）
     * @param username
     * @param vUsername
     * @param productName
     * @param vPhone
     * @param vCost
     * @param num
     * @param wen
     * @param vUserAddress
     * @return
     */
    @PostMapping(value="/order")
    public String doAdd( @RequestParam("username")String username,
                         @RequestParam("vUsername")String vUsername,
                         @RequestParam("productName")String productName,
                         @RequestParam("vPhone")String vPhone,
                         @RequestParam("vCost")String vCost,
                         @RequestParam("num")String num,
                         @RequestParam("wen")String wen,
                         @RequestParam("vUserAddress")String vUserAddress){

        Map<String,Object> conditions=new HashMap<>();
        conditions.put("username",username);
        conditions.put("vUsername",vUsername);
        conditions.put("productName",productName);
        conditions.put("vPhone",vPhone);
        conditions.put("vCost",vCost);
        conditions.put("num",num);
        conditions.put("wen",wen);
        conditions.put("vUserAddress",vUserAddress);

        try {
            String result=HttpUtils.post(urlSettings.getAdd_url(),conditions);
            return "redirect:orders";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }


    /**
     * 获取属性信息
     * @param spmc
     * @return
     */
    @RequestMapping(value="/properties")
    public @ResponseBody Object getProperties(@RequestParam("spmc")String spmc){
        String nodeStr=null;
        try {
            Map<String,Object> conditions=new HashMap<>();
            conditions.put("spmc",spmc);
            nodeStr=HttpUtils.post(urlSettings.getProperties_url(),conditions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nodeStr;
    }
}
