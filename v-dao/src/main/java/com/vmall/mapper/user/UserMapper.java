package com.vmall.mapper.user;

import com.vmall.pojo.VUser;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public VUser login(@Param("userCode") String userCode);
    public VUser findEmail(@Param("email") String email);
    public int findPassword(@Param("id") long id, @Param("password") String password, @Param("salt") String salt);
    public int register(VUser vUesr);
    public int add();
    public VUser getVUesrById(@Param("userId") long id);
    public Integer updateUserByGrade(@Param("v_userId")long v_userId,@Param("grade") double grade);//修改用户积分
    public VUser getUserIdByUserName(String userName);//根据用户名称查询用户id

}
