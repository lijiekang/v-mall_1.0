package com.vmall.vproducts.controller.seckill;

import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.vproducts.service.vseckill.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 12:28 2019-05-07
 * @Modifyied By:
 */
@Controller
public class VSeckillProductController {

    @Autowired
    private ProductService productService;


    /**
     * 秒杀列表显示
     * @param model
     * @return
     */
    @RequestMapping(value="/seckill")
    public String toSeckillProductList(Model model){
        List<VSeckillProduct> products =productService.listSeckillProductByPage();
        model.addAttribute("products",products);
        return "seckill/seckill_product";

    }



    @GetMapping(value="/seckillProduct")
    public String toSeckillProductAdd(){
        return "seckill/seckill_product_add";
    }

    @GetMapping(value="/seckillorderlist")
    public String seckillorderlist(){
        return "seckill/seckill_order_list";
    }
}
