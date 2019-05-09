package com.vmall.vproducts.service.vseckill;

import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillProduct;

import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 10:55 2019-05-07
 * @Modifyied By:
 */
public interface ProductService {

    /**
     * 查询秒杀商品列表
     * @return
     */
    List<VSeckillProduct> listSeckillProductByPage();


    /**
     *
     * @param vSeckillProduct
     * @return
     */
    boolean addSeckillPorduct(VSeckillProduct vSeckillProduct);







}
