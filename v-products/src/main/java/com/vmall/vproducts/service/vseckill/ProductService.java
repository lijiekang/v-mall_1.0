package com.vmall.vproducts.service.vseckill;

import com.vmall.pojo.Page;
import com.vmall.pojo.VProduct;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.VSku;
import com.vmall.pojo.vo.CategoryVO;
import com.vmall.pojo.vo.SeckillProductVO;

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
     * 添加秒杀商品
     * @param seckillProductVO
     * @return
     */
    boolean addSeckillPorduct(SeckillProductVO seckillProductVO);


    /**
     * 查询指定分类下的商品信息
     * @param categoryVO
     * @return
     */
    List<VProduct> listProductByLevels(CategoryVO categoryVO);


    /**
     * 查询某个商品所有的SKU
     * @param productId
     * @return
     */
    List<VSku> listSkuByProductId(Integer productId);




}
