package com.vmall.vauth.service.dev;

import com.vmall.mapper.collection.CollectionMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VCollection;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

@Service
public class CollectionService {
    @Resource
    CollectionMapper collectionMapper;
    @Resource
    TokenService tokenService;
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return collectionMapper.count(vUser.getvUserId());
    }
    public List<VCollection> colLsit(String token, Page page){
        VUser vUser=(VUser)tokenService.get(token);
        return collectionMapper.collectionList(vUser.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public boolean delCol(long collectionId){
        if(collectionMapper.delCollection(collectionId)>0){
            return true;
        }
        return false;
    }
}
