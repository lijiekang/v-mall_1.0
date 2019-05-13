package com.vmall.mapper.user;
import com.vmall.pojo.VRole;

import com.vmall.pojo.VUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackUserMapper {

    List<VUser> getAllUser(@Param("vUsername")String vUsername, @Param("currentPage") int currentPage, @Param("pageSize") Integer pageSzie);

    List<VUser> getUserByAll(@Param("vUsername")String vUsername,@Param("currentPage") Integer currentPage);

    int getTotalPageCount(@Param("vUsername") String vUsername);

    VUser getUserById(int id);

    int addUser(VUser vUesr);

    int delUser(int id);

    int getUpdate(VUser vUesr);

    //导入导出
    List<VUser> getAllVUser();



}
