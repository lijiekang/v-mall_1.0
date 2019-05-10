package com.vmall.vtrade.service.userservice;

import com.vmall.mapper.user.UserMapper;
import com.vmall.pojo.VUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Integer updateUserByGrade(long v_userId,double grade){
        return userMapper.updateUserByGrade(v_userId,grade);
    }//修改用户积分

    public VUser getUserIdByUserName(String userName){
        return userMapper.getUserIdByUserName(userName);
    }//根据用户名称查询用户id

    public VUser getVUesrById(@Param("userId") long id){
        return userMapper.getVUesrById(id);
    }
}
