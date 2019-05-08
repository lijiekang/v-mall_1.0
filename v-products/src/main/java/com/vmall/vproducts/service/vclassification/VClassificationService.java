package com.vmall.vproducts.service.vclassification;

import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.VCategory;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VClassificationService {
    @Autowired
    VProductMapper vProductMapper;
   public List<VCategory> getVCategoryLevel1(){
        return vProductMapper.getVCategoryLevel1();
    }

   public List<VCategory>getVCategoryLevel2(@Param("vCategoryId") int vCategoryId){
       return vProductMapper.getVCategoryLevel2(vCategoryId);
    }

   public int getDelCategoryLv1ById(@Param("vCategoryId") int vCategoryId){
       return vProductMapper.getDelCategoryLv1ById(vCategoryId);
    }

    //查找不同分级
   public List<VCategory>getVCategoryLevelfen(@Param("vType") int vType){
       return vProductMapper.getVCategoryLevelfen(vType);
    }

    //新增分类
   public int addClassification(@Param("vType") int vType,@Param("vParentCategoryId") int vParentCategoryId,@Param("vCategoryName") String vCategoryName){
       return vProductMapper.addClassification(vType,vParentCategoryId,vCategoryName);
    }
}
