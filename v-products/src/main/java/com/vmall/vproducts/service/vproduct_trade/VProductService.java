package com.vmall.vproducts.service.vproduct_trade;

import com.vmall.pojo.VProduct;



public interface VProductService {

     Integer updateVProductStoreByProductId(long productId,long num);//库存减少

     VProduct getVProductIdByProductName(String vProductName);//根据商品名称查询商品id

     VProduct chakanvproduct(Integer id);

     Integer shuStore(Integer productId,Integer num);//根据商品id修改库存

    VProduct getVProductNameByProductId(String productId);//根据商品id查询商品名称

}
