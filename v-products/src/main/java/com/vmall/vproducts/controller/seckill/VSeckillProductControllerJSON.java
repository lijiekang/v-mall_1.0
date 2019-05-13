package com.vmall.vproducts.controller.seckill;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vseckill.ProductService;
import com.vmall.vutil.HttpUtils;
import com.vmall.vutil.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 16:38 2019-05-11
 * @Modifyied By:
 */
@RequestMapping(value="/seckillJSON")
@RestController
public class VSeckillProductControllerJSON {

    @Autowired
    private ProductService productService;


    @Autowired
    private VCategoryService vCategoryService;


    /**
     * 返回所有秒杀商品数据
     * @param httpServletResponse
     */
    @RequestMapping(value="/seckill")
    public String getSeckillProductList(HttpServletResponse httpServletResponse){
        List<VSeckillProduct> products =productService.listSeckillProductByPage();
        try {
            return JSONArray.toJSONString(products);
//            HttpUtils.writeData(IOUtils.getObjectOutputStream(httpServletResponse.getOutputStream(),x->new ObjectOutputStream(x)),products);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
