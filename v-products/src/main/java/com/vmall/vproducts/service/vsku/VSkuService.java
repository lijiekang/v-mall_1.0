package com.vmall.vproducts.service.vsku;

import com.vmall.mapper.skumapper.VSkuMapper;
import com.vmall.pojo.VProperties;
import com.vmall.pojo.VProperty;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class VSkuService {

    @Autowired
    VSkuMapper vSkuMapper;

   public List<VProperties> getAllSku(int vPropertiesId){

        return vSkuMapper.getAllSku(vPropertiesId);
    }

   public int getDelSku(@Param("vPropertiesId") int vPropertiesId){
       return vSkuMapper.getDelSku(vPropertiesId);
    }
   public int getDelSkuChild(@Param("vParentId")int vParentId){
       return vSkuMapper.getDelSkuChild(vParentId);
    }

    public int getDelSkuMoChild(@Param("vPropertiesId")int vPropertiesId){
       return vSkuMapper.getDelSkuMoChild(vPropertiesId);
    }

    //添加规格
    public int getInsertSku(@Param("vPropertiesName")String vPropertiesName){
        return vSkuMapper.getInsertSku(vPropertiesName);
    }
    //修改规格
    public int getUpdateSku(@Param("vPropertiesName")String vPropertiesName,@Param("vPropertiesId")int vPropertiesId){
        return vSkuMapper.getUpdateSku(vPropertiesName,vPropertiesId);
    }

    public List<VProperty>getmoSku(int vParentId){
       return vSkuMapper.getmoSku(vParentId);
    }
   public int getAddSkuGui(@Param("vPropertiesName")String vPropertiesName,@Param("vPropertiesId")int vPropertiesId ){
       return vSkuMapper.getAddSkuGui(vPropertiesName,vPropertiesId);
    }
}
