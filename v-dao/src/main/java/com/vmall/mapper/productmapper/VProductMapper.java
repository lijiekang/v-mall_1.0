package com.vmall.mapper.productmapper;

import com.vmall.pojo.VCategory;
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
    //一级分类
    List<VCategory>getVCategoryLevel1();
    //二三级分类
    List<VCategory>getVCategoryLevel2(@Param("vCategoryId") int vCategoryId);
    //删除目录
   int getDelCategoryLv1ById(@Param("vCategoryId") int vCategoryId);
   //查找不同分级
    List<VCategory>getVCategoryLevelfen(@Param("vType") int vType);
    //新增分类
    int addClassification(@Param("vType") int vType,@Param("vParentCategoryId") int vParentCategoryId,@Param("vCategoryName") String vCategoryName);
}
