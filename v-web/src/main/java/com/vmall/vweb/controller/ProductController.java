package com.vmall.vweb.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.vutil.HttpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 14:40 2019-05-11
 * @Modifyied By:
 */
@RequestMapping(value="/pro")
@Controller
public class ProductController {


    @RequestMapping(value="/seckill")
    public String toSeckillProductList(Model model){
        try {
            List<VSeckillProduct> products= JSONArray.parseArray(HttpUtils.get("http://localhost:8081/seckillJSON/seckill"),VSeckillProduct.class);
            model.addAttribute("products",products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "products/seckill_product";
    }
}
