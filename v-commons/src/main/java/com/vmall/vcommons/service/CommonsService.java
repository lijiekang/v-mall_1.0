package com.vmall.vcommons.service;

import com.vmall.mapper.commons.CommonsMapper;
import com.vmall.pojo.VCategory;
import com.vmall.pojo.VCommons;

import com.vmall.pojo.VProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CommonsService {

    @Autowired
    CommonsMapper commonsMapper;
   public List<VCommons>getAllCommonsById(@Param("vProductId") int vProductId,@Param("pageno") int pageno){
        return commonsMapper.getAllCommonsById(vProductId,pageno);
    }
    public List<VCategory>getVCategoryLevel1(){
        return commonsMapper.getVCategoryLevel1();
    }

    public List<VCategory>getVCategoryLevel2(@Param("vCategoryId") int vCategoryId){
        return commonsMapper.getVCategoryLevel2(vCategoryId);
    }

   public List<VProduct>getProductById(@Param("vCategoryLevel3") int vCategoryLevel3){
        return commonsMapper.getProductById(vCategoryLevel3);
    }

    public int getPageCount(){
        return commonsMapper.getPageCount();
    }

    public int getDelCommonts(int vCommonsId){

       return commonsMapper.getDelCommonts(vCommonsId);
    }

    public int getAddCommons(VCommons vCommons){
       return commonsMapper.getAddCommons(vCommons);
    }

    public VCommons getMoCommons(@Param("vCommonsId")int vCommonsId){
       return commonsMapper.getMoCommons(vCommonsId);
    }

    public int getUpdateCommons(@Param("vCommonsId")int vCommonsId,@Param("vContent")String vContent){
       return commonsMapper.getUpdateCommons(vCommonsId,vContent);
    }

   public int getUpdateHuiCommons(@Param("vCommonsId")int vCommonsId,@Param("vReply")String vReply){
       return commonsMapper.getUpdateHuiCommons(vCommonsId,vReply);
    }
}
