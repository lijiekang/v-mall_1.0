package com.vmall.mapper.user;

import com.vmall.pojo.VUesr;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    public VUesr login(@Param("userCode") String userCode);
    public VUesr findEmail(@Param("email") String email);
    public int findPassword(@Param("id") long id, @Param("password") String password, @Param("salt") String salt);
    public int register(VUesr vUesr);
    public int add();
    public VUesr getVUesrById(@Param("userId") long id);
}
