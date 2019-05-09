package com.vmall.mapper.productmapper;

import com.vmall.pojo.VProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VProductMapper {
    //库存减少
    Integer updateVProductStoreByProductId(@Param("productId") long productId, @Param("num") long num);
    //根据商品名称查询商品id
    VProduct getVProductIdByProductName(String vProductName);
    //查询所有的商品
    List<VProduct> listProduct(@Param("vProductName")String vProductName, @Param("PageNo") Integer PageNo, @Param("pageSize")Integer pageSize);
    int count(@Param("vProductName")String vProductName);
    //查看商品
    VProduct chakanvproduct(@Param("id")Integer id);
    //根据商品的id查询库存
    //修改前
    List<VProduct>productid(@Param("id") Integer id);
    //修改商品
    int upd(VProduct product);
    //添加商品
    int add(VProduct product);
    //删除商品
    int del(@Param("id") Integer id);
    //修改预警货品
    int updearly(VProduct product);
    //修改库存
    int inventory(VProduct vProduct);
    //上架下架
    int putawayproduct(VProduct vProduct);
    //用solr查询商品
    List<VProduct>solrvProduct(@Param("vProductName")String vProductName, @Param("PageNo") Integer PageNo, @Param("pageSize")Integer pageSize);
}
