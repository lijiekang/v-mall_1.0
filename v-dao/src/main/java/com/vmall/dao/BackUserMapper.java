package com.vmall.dao;

import com.vmall.pojo.VUesr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackUserMapper {

    List<VUesr> getAllUser(@Param("vUserCode")String vUserCode, @Param("currentPage") int currentPage, @Param("pageSize") Integer pageSzie);

    List<VUesr> getUserByAll(@Param("vUserCode")String vUsercode,@Param("currentPage") Integer currentPage);

    int getTotalPageCount(@Param("vUserCode") String vUserCode);

    VUesr getUserById(int id);

    int addUser(VUesr vUesr);

    int delUser(int id);

    int getUpdate(VUesr vUesr);

}
