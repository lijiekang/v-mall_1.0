package com.vmall.vtrade.service.skuservice;

import com.vmall.mapper.skumapper.VSkuMapper;
import com.vmall.pojo.VSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkuService {
    @Autowired
    VSkuMapper skuMapper;

    public List<VSku> findAllSku(){
        return skuMapper.findAllSku();
    }//查询所有sku

    public List<Integer> findskuId(Integer productId){
        return skuMapper.findskuId(productId);
    }//根据商品id查询skuid

    public  VSku findskuIdBySkuName(String skuName){
        return skuMapper.findskuIdBySkuName(skuName);
    }//根据shuname查询skuid

    public VSku findProductIdBySkuId(Integer Skuid){
        return skuMapper.findProductIdBySkuId(Skuid);
    }//根据skuid查询商品id
}
