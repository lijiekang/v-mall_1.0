package com.vmall.dao;

import com.vmall.pojo.VUesr;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public VUesr login(@Param("userCode")String userCode,@Param("password")String password);
    Integer updateUserByGrade(@Param("v_userId")long v_userId,@Param("grade") double grade);//修改用户积分
    VUesr getUserIdByUserName(String userName);//根据用户名称查询用户id

}
