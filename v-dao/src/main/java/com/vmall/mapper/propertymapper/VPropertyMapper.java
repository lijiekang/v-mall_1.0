package com.vmall.mapper.propertymapper;

import com.vmall.pojo.VProperty;

import java.util.List;

public interface VPropertyMapper {
    List<VProperty> findAllVProperty(Integer vParentId);//根据属性id查询属性值
}
