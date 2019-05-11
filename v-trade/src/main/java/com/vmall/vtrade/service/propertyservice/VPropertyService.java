package com.vmall.vtrade.service.propertyservice;

import com.vmall.mapper.propertymapper.VPropertyMapper;
import com.vmall.pojo.VProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VPropertyService {
    @Autowired
    VPropertyMapper vPropertyMapper;

    public List<VProperty> findAllVProperty(Integer vParentId){
        return vPropertyMapper.findAllVProperty(vParentId);
    }//根据属性id查询属性值
}
