package com.vmall.mapper.skumapper;

import com.vmall.pojo.VSku;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SkuMapper {
    public VSku getVSkuBySkuCode(@Param("skuCode")String skuCode);
    List<VSku> findAllSku();//查询所有sku
    List<Integer> findskuId(Integer productId);//根据商品id查询skuid
    VSku findskuIdBySkuName(String skuName);//根据shuname查询skuid
    VSku findProductIdBySkuId(Integer Skuid);//根据skuid查询商品id
}
