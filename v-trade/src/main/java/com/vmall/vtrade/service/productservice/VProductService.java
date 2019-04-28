package com.vmall.vtrade.service.productservice;

import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.VProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VProductService {
    @Autowired
    VProductMapper vProductMapper;

    public Integer updateVProductStoreByProductId(long productId,long num){
        return vProductMapper.updateVProductStoreByProductId(productId,num);
    }//库存减少

    public VProduct getVProductIdByProductName(String vProductName){
        return vProductMapper.getVProductIdByProductName(vProductName);
    }//根据商品名称查询商品id

    public VProduct getVProductNameByProductId(String productId){
        return vProductMapper.getVProductNameByProductId(productId);
    }//根据商品id查询商品名称
}
