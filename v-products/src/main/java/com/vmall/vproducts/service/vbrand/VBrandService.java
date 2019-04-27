package com.vmall.vproducts.service.vbrand;

import com.vmall.mapper.brandmapper.VBrandMapper;
import com.vmall.pojo.VBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VBrandService {
    @Autowired
    VBrandMapper vBrandMapper;
    public int vbrandadd(VBrand vBrand){
        return vBrandMapper.vbrandadd(vBrand);
    }
    public int vbranddel(Integer id){
        return vBrandMapper.vbranddel(id);
    }
    public List<VBrand>vbrandlist(Integer id){
        return vBrandMapper.vbrandlist(id);
    }
    public int vbrandupd(VBrand vBrand){
        return vBrandMapper.vbrandupd(vBrand);
    }
}
