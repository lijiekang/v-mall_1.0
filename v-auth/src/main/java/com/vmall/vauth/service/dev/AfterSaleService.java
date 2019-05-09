package com.vmall.vauth.service.dev;

import com.vmall.mapper.aftersale.AfterSaleMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VAftersale;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AfterSaleService {
    @Resource
    AfterSaleMapper afterSaleMapper;
    @Resource
    TokenService tokenService;
    public List<VAftersale> aftersaleList(String token, Page page){
        VUser vUser=(VUser)tokenService.get(token);
        return afterSaleMapper.afterSaleList(vUser.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public VAftersale getAfterSale(String token,long aftersaleId){
        VUser vUser=(VUser)tokenService.get(token);
        return afterSaleMapper.getAfterSale(vUser.getvUserId(),aftersaleId);
    }
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return afterSaleMapper.count(vUser.getvUserId());
    }
}
