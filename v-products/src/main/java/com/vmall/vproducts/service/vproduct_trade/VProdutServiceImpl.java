package com.vmall.vproducts.service.vproduct_trade;


import com.alibaba.dubbo.config.annotation.Service;
import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.VProduct;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 15:06 2019-05-10
 * @Modifyied By:
 */
@Service(version = "1.0.0",timeout = 1200000)
public class VProdutServiceImpl implements VProductService{

    @Autowired
    VProductMapper vProductMapper;

    @Override
    public Integer updateVProductStoreByProductId(long productId,long num){
        return vProductMapper.updateVProductStoreByProductId(productId,num);
    }//库存减少

    @Override
    public VProduct getVProductIdByProductName(String vProductName){
        return vProductMapper.getVProductIdByProductName(vProductName);
    }//根据商品名称查询商品id

    @Override
    public VProduct chakanvproduct(Integer id){
        return vProductMapper.chakanvproduct(id);
    }

    @Override
    public Integer shuStore(Integer productId,Integer num){
        return vProductMapper.shuStore(productId,num);
    }//根据商品id修改库存
    @Override
    public VProduct getVProductNameByProductId(String productId){
        return vProductMapper.getVProductNameByProductId(productId);
    }//根据商品id查询商品名称
}
