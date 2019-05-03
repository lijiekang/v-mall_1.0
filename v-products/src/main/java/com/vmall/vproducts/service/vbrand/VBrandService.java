package com.vmall.vproducts.service.vbrand;

import com.vmall.mapper.brandmapper.VBrandMapper;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VBrandService {
    @Autowired
    VBrandMapper vBrandMapper;
    public List<VBrand>vbrandlist(String vBrandName,Pages page){
        return vBrandMapper.vbrandlist(vBrandName,(page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int count(String vBrandName){
        return vBrandMapper.count(vBrandName);
    }
    public List<VBrand>vbrandid(Integer id){
        return vBrandMapper.vbrandid(id);
    }
    public int vbrandadd(VBrand vBrand){
        return vBrandMapper.vbrandadd(vBrand);
    }
    public int vbranddel(Integer id){
        return vBrandMapper.vbranddel(id);
    }

    public int vbrandupd(VBrand vBrand){
        return vBrandMapper.vbrandupd(vBrand);
    }
}
