package com.vmall.vtrade.service.propertiesskuservice;

import com.vmall.mapper.propertiesskumapper.VPropertiesSkuMapper;
import com.vmall.pojo.VPropertiesSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VPropertiesSkuService {
    @Autowired
    VPropertiesSkuMapper vPropertiesSkuMapper;

    public List<Integer> vPropertList(Integer v_sku_id){
        return vPropertiesSkuMapper.vPropertList(v_sku_id);
    }//根据skuid查询规格
}
