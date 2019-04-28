package com.vmall.mapper.vcategorymapper;

import com.vmall.pojo.VCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * create by 陈昌
 * 商品分类接口
 * 2019-4-27
 */
public interface VCategoryMapper {
    List<VCategory>listcategory(@Param("vCategoryName")String vCategoryName,@Param("PageNo") Integer PageNo, @Param("pageSize")Integer pageSize);
    int count(@Param("vCategoryName")String vCategoryName);
    //查看
    VCategory chakancategory(@Param("id")Integer id);
    //修改前
    List<VCategory>categoryid(@Param("id") Integer id);
    int del(@Param("id") Integer id);
    int upd(VCategory vCategory);
    int add(VCategory vCategory);
    //查询一级
    List<VCategory>getcategorylist();
    List<VCategory>getcategoryName(Integer vParentCategoryId);
}
