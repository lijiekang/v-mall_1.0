package com.vmall.vtrade.service.productservice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.VProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VProductService {


    @Autowired
    VProductMapper vProductMapper;

    @Reference(version = "1.0.0")
    com.vmall.vproducts.service.vproduct_trade.VProductService vProductService;



    public Integer updateVProductStoreByProductId(long productId,long num){
        return vProductService.updateVProductStoreByProductId(productId,num);
    }//库存减少

    public VProduct getVProductIdByProductName(String vProductName){
        return vProductService.getVProductIdByProductName(vProductName);
    }//根据商品名称查询商品id

    public VProduct chakanvproduct(Integer id){
        return vProductService.chakanvproduct(id);
    }

    public Integer shuStore(Integer productId,Integer num){
        return vProductService.shuStore(productId,num);
    }//根据商品id修改库存
    public VProduct getVProductNameByProductId(String productId){
        return vProductService.getVProductNameByProductId(productId);
    }//根据商品id查询商品名称

}
