package com.vmall.vweb.service;


import com.vmall.vweb.beans.VUser;
import com.vmall.vweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackUserService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String vUsername) throws UsernameNotFoundException {
        VUser user=userMapper.loasUserByUsername(vUsername);
        if(user==null){
            throw new UsernameNotFoundException("账户不存在!");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getvUserId()));
        return user;
    }
}
