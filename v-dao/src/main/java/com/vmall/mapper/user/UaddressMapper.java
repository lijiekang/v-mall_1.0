package com.vmall.mapper.user;

import com.vmall.pojo.VUserAddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UaddressMapper {
    public List<VUserAddress> addressList(@Param("userId")int userId,
                                          @Param("pageNo")int pageNo,
                                          @Param("pageSize")int pageSize);
    public int count(@Param("userId")int userId);
    public int delAddress(@Param("vAddressId")int vAddressId);
    public int updAddress(VUserAddress vUserAddress);
    public int addAddress(VUserAddress vUserAddress);
    public VUserAddress getAddressById(@Param("vAddressId")int vAddressId);
}
