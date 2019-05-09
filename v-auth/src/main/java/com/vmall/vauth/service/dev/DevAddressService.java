package com.vmall.vauth.service.dev;

import com.vmall.mapper.user.UaddressMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VUser;
import com.vmall.pojo.VUserAddress;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class DevAddressService {
    @Resource
    UaddressMapper uaddressMapper;
    @Resource
    TokenService tokenService;
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return uaddressMapper.count((int)vUser.getvUserId());
    }
    public List<VUserAddress> allAddList(String token, Page page){
        VUser vUesr=(VUser)tokenService.get(token);
        return uaddressMapper.addressList((int) vUesr.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public VUserAddress getAddressById(int addressId){
        return uaddressMapper.getAddressById(addressId);
    }
    public int addAddress(VUserAddress vUserAddress,String token){
        vUserAddress.setvCreateDate(new Timestamp(System.currentTimeMillis()));
        vUserAddress.setvModifyDate(new Timestamp(System.currentTimeMillis()));
        VUser v=(VUser)tokenService.get(token);
        vUserAddress.setvUserId(v.getvUserId());
        return uaddressMapper.addAddress(vUserAddress);
    }
    public int updAddress(VUserAddress vUserAddress){
        vUserAddress.setvModifyDate(new Timestamp(System.currentTimeMillis()));
        return uaddressMapper.updAddress(vUserAddress);
    }
    public int delAddress(int addressId){
        return uaddressMapper.delAddress(addressId);
    }
}
