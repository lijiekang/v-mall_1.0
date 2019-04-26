package com.vmall.mapper.seckill;

import com.vmall.pojo.VSeckillProduct;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface SeckillProductMapper {

    /**
     * 新增秒杀商品
     * @param vSeckillProduct
     * @return
     */
    int insertSeckillProduct(VSeckillProduct vSeckillProduct);


    /**
     * 读取所有秒杀商品
     * @return
     */
    List<VSeckillProduct> listSeckillProducts();


    /**
     * 修改秒杀商品信息
     * @param vSeckillProduct
     * @return
     */
    int updateSeckillProduct(VSeckillProduct vSeckillProduct);


    /**
     * 根据秒杀商品id获取该秒杀商品的数量
     * @param seckillProductId 秒杀商品id
     * @return 剩余数量
     */
    int getSeckillProductRemain(Integer seckillProductId);




}
