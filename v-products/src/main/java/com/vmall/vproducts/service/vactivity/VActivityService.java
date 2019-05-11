package com.vmall.vproducts.service.vactivity;

import com.vmall.mapper.avctivitymapper.VActivityMapper;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VActivityService {
    @Autowired
    VActivityMapper vActivityMapper;
    public List<VActivity>listvActivity(Pages page){
        return vActivityMapper.listvActivity((page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int count(){
        return vActivityMapper.count();
    }
    public int addvActivity(VActivity vActivity){
        return vActivityMapper.addvActivity(vActivity);
    }
    public VActivity vActivityid(Integer id){
        return vActivityMapper.vActivityid(id);
    }
    public int updvActivity(VActivity vActivity){
        return vActivityMapper.updvActivity(vActivity);
    }
    public int delvActivity(Integer id){
        return vActivityMapper.delvActivity(id);
    }
}
