package com.vmall.mapper.productmapper;

import com.vmall.pojo.VProduct;
import org.apache.ibatis.annotations.Param;

public interface VProductMapper {

    Integer updateVProductStoreByProductId(@Param("productId") long productId, @Param("num") long num);//库存减少
    VProduct getVProductIdByProductName(String vProductName);//根据商品名称查询商品id
}
