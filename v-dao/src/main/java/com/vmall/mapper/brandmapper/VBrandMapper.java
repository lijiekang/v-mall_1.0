package com.vmall.mapper.brandmapper;

import com.vmall.pojo.VBrand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品品牌
 */
public interface VBrandMapper {
    //添加品牌
    int vbrandadd(VBrand vBrand);
    //删除品牌
    int vbranddel(@Param("id") Integer id);
    List<VBrand>vbrandlist(@Param("id") Integer id);
    //修改品牌
    int vbrandupd(VBrand vBrand);
}
