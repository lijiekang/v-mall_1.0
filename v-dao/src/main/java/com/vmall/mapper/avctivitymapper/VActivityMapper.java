package com.vmall.mapper.avctivitymapper;

import com.vmall.pojo.VActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VActivityMapper {
    //查询所有商品活动
    List<VActivity> listvActivity(@Param("PageNo") Integer PageNo, @Param("pageSize")Integer pageSize);
    int count();
    int addvActivity(VActivity vActivity);
    VActivity vActivityid(Integer id);
    int updvActivity(VActivity vActivity);
    int delvActivity(Integer id);
}
