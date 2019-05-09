package com.vmall.vauth.service.dev;

import com.vmall.mapper.user.ComMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VCommons;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommonsService {
    @Resource
    ComMapper comMapper;
    @Resource
    TokenService tokenService;
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return comMapper.count(vUser.getvUserId());
    }
    public List<VCommons> comList(String token, Page page){
        VUser vUser=(VUser)tokenService.get(token);
        return comMapper.commonsList(vUser.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
}
