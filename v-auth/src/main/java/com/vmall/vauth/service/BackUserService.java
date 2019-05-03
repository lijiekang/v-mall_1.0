package com.vmall.vauth.service;

import com.vmall.mapper.User.BackUserMapper;
import com.vmall.pojo.VUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackUserService {
    @Autowired
    BackUserMapper backUserMapper;

    public List<VUser> getUserByAll(String vUsercode, int currentPage){
        return backUserMapper.getUserByAll(vUsercode, currentPage);
    }

    public int getTotalPageCount(String vUserCode){
        return backUserMapper.getTotalPageCount(vUserCode);
    }

    public List<VUser> getAllUser(String vUserCode, int currentPage, int pageSzie){
        return backUserMapper.getAllUser(vUserCode,currentPage, pageSzie);
    }

    public VUser getUserById(int id){
        return backUserMapper.getUserById(id);
    }

    public int delUser(int id){
        return backUserMapper.delUser(id);
    }

    public int addUser(VUser vUesr){
        return backUserMapper.addUser(vUesr);
    }

    public int getUpdate(VUser vUesr){
        return backUserMapper.getUpdate(vUesr);
    }
}
