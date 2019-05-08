package com.vmall.mapper.user;

import com.vmall.pojo.VBrowsinghistory;
import com.vmall.pojo.VUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    public VUser login(@Param("userCode") String userCode);
    public VUser findEmail(@Param("email") String email);
    public int findPassword(@Param("id") long id, @Param("password") String password, @Param("salt") String salt);
    public int register(VUser vUesr);
    public int add();
    public VUser getVUesrById(@Param("userId") long id);
    public int addFootHistory(VBrowsinghistory vBrowsinghistory);
    public int updProfile(VUser vUser);
    public List<VBrowsinghistory> MyfootHistory(@Param("vUserId")long vUserId,
                                                @Param("pageNo")long pageNo,
                                                @Param("pageSize")long pageSize);
    public int count(@Param("vUserId")long vUserId);
    public int delFootHistory();
    public int updEmail(@Param("email")String email,@Param("userId")long userId);
    public int updPhoneNumber(@Param("phoneNumber")String phoneNumber,@Param("userId")long userId);
    public int findPhoneNumber(@Param("phoneNumber")String phoneNumber);
}
