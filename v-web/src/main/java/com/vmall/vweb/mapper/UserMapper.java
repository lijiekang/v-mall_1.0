package com.vmall.vweb.mapper;

import com.vmall.pojo.VRole;
import com.vmall.vweb.beans.VUser;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 22:39 2019-05-11
 * @Modifyied By:
 */
@Mapper
public interface UserMapper {

    //安全验证
    VUser loasUserByUsername(String vUsername);
    List<VRole> getUserRolesByUid(long id);
}
