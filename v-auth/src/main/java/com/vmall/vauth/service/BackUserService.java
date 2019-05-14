package com.vmall.vauth.service;

import com.vmall.mapper.user.BackUserMapper;
import com.vmall.pojo.VUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackUserService {
    @Autowired
    BackUserMapper backUserMapper;

    public List<VUser> getUserByAll(String vUsername, int currentPage){
        return backUserMapper.getUserByAll(vUsername, currentPage);
    }

    public int getTotalPageCount(String vUsername){
        return backUserMapper.getTotalPageCount(vUsername);
    }

    public List<VUser> getAllUser(String vUsername, int currentPage, int pageSzie){
        return backUserMapper.getAllUser(vUsername,currentPage, pageSzie);
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

    public List<VUser> getAllVUser(){
        return backUserMapper.getAllVUser();
    }

//    @Override
//    public UserDetails loadUserByUsername(String vUsername) throws UsernameNotFoundException {
//        VUser user=backUserMapper.loasUserByUsername(vUsername);
//        if(user==null){
//            throw new UsernameNotFoundException("账户不存在!");
//        }
//        user.setRoles(backUserMapper.getUserRolesByUid(user.getvUserId()));
//        return user;
//    }
}
