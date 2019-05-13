package com.vmall.mapper.menu;

import com.vmall.pojo.VMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<VMenu> getAllMenus();
}
