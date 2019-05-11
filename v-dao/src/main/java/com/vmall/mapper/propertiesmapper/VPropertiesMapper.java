package com.vmall.mapper.propertiesmapper;

import com.vmall.pojo.VProperties;

import java.util.List;

public interface VPropertiesMapper {
    List<VProperties> findAllVProperties(Integer v_properties_id);//根据propertiesid查询属性
}
