package com.vmall.mapper.user;

import com.vmall.pojo.VUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackUserMapper {

    List<VUser> getAllUser(@Param("vUserCode")String vUserCode, @Param("currentPage") int currentPage, @Param("pageSize") Integer pageSzie);

    List<VUser> getUserByAll(@Param("vUserCode")String vUsercode,@Param("currentPage") Integer currentPage);

    int getTotalPageCount(@Param("vUserCode") String vUserCode);

    VUser getUserById(int id);

    int addUser(VUser vUesr);

    int delUser(int id);

    int getUpdate(VUser vUesr);

}
