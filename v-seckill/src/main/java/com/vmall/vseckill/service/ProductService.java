package com.vmall.vseckill.service;


import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.vo.SeckillProductVO;

import java.util.List;

public interface ProductService {


    /**
     * 新增秒杀商品
     * @param vSeckillProduct
     * @return
     */
    boolean addSeckillProduct(SeckillProductVO vSeckillProduct);

    /**
     * 加载所有秒杀商品
     * @return
     */
    List<VSeckillProduct> loadSeckillProducts();


    /**
     * 修改秒杀商品
     * @param vSeckillProduct
     * @return
     */
    boolean modifyProduct(VSeckillProduct vSeckillProduct);


    /**
     * 判断某一商品是是否有剩余
     * @param seckillProductId
     * @return
     */
    boolean haveRemain(Integer seckillProductId);

    /**
     * 根据商品id获取商品数量
     * @param productId
     * @return
     */
    long getProductQuantityByProductId(Integer productId);
}
