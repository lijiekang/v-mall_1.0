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
}
