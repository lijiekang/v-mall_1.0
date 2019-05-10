package com.vmall.vtrade.service.propertiesservice;

import com.vmall.mapper.propertiesmapper.VPropertiesMapper;
import com.vmall.pojo.VProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VPropertiesService {
    @Autowired
    VPropertiesMapper vPropertiesMapper;

    public List<VProperties> findAllVProperties(Integer v_properties_id){
        return vPropertiesMapper.findAllVProperties(v_properties_id);
    }//根据propertiesid查询属性
}
