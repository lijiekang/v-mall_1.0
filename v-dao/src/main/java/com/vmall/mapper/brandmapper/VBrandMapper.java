package com.vmall.mapper.brandmapper;

import com.vmall.pojo.VBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌
 */
public interface VBrandMapper {
    List<VBrand>vbrandlist(@Param("vBrandName")String vBrandName,@Param("PageNo") Integer PageNo, @Param("pageSize")Integer pageSize);
    int count(@Param("vBrandName")String vBrandName);
    VBrand chakanbrand(@Param("id") Integer id);
    List<VBrand>vbrandid(@Param("id") Integer id);
    //修改品牌
    int vbrandupd(VBrand vBrand);
    //添加品牌
    int vbrandadd(VBrand vBrand);
    //删除品牌
    int vbranddel(@Param("id") Integer id);
    //查询所有的品牌
    List<VBrand>chabrand();
    //上架下架
    int putawayupd(VBrand vBrand);
}
