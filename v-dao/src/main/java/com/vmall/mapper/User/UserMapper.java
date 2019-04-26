package com.vmall.mapper.User;

import com.vmall.pojo.VUesr;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public VUesr login(@Param("userCode")String userCode,@Param("password")String password);
    public VUesr findEmail(@Param("email")String email);
    public int findPassword(@Param("id")long id,@Param("password")String password);
    public int register(VUesr vUesr);
}
