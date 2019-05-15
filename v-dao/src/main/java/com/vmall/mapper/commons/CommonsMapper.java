package com.vmall.mapper.commons;

import com.vmall.pojo.VCategory;
import com.vmall.pojo.VCommons;
import com.vmall.pojo.VProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonsMapper {

     //根据商品ID获取评论信息
     List<VCommons>getAllCommonsById(@Param("vProductId") int vProductId,@Param("pageno") int pageno);
     //一级分类
     List<VCategory>getVCategoryLevel1();
     //二三级分类
     List<VCategory>getVCategoryLevel2(@Param("vCategoryId") int vCategoryId);
     //获取商品信息
     List<VProduct>getProductById(@Param("vCategoryLevel3") int vCategoryLevel3);
     //获取总页数
     int getPageCount();
     //删除评论
     int getDelCommonts(int vCommonsId);
     //修改评论
     int getUpdateCommons(@Param("vCommonsId")int vCommonsId,@Param("vContent")String vContent);
     //新增评论
     int getAddCommons(VCommons vCommons);
     //查询某条评论
     VCommons getMoCommons(@Param("vCommonsId")int vCommonsId);
     //回复某条评论
     int getUpdateHuiCommons(@Param("vCommonsId")int vCommonsId,@Param("vReply")String vReply);
     //某件商品下某位用户和商家的全部评论
     List<VCommons>getVcommZhui(@Param("vUserId")int vUserId,@Param("vOrderId")int vOrderId,@Param("vProductId")int vProductId);
     //删除某件商品下的某个商品模块
     int getDelMoping(@Param("vUserId")int vUserId,@Param("vOrderId")int vOrderId,@Param("vProductId")int vProductId);
     //查询某件商品下某位用户的评论总数
     int getSelectInProduct(@Param("vUserId")int vUserId,@Param("vOrderId")int vOrderId,@Param("vProductId")int vProductId);
     //更新评论下的评论总数
     int getUpdateCommonsCount(@Param("vCommonsCount")int vCommonsCount,@Param("vProductId")int vProductId);
}
