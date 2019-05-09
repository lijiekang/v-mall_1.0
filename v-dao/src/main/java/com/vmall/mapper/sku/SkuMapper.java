package com.vmall.mapper.sku;

import com.vmall.pojo.VSku;
import org.apache.ibatis.annotations.Param;

public interface SkuMapper {
    public VSku getVSkuBySkuCode(@Param("skuCode")String skuCode);
}
