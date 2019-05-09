package com.vmall.vproducts.service.vproduct;

import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VProduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  VProductService {
    @Autowired
    VProductMapper vProductMapper;

    public List<VProduct> listProduct(String vProductName, Pages page) {
        return vProductMapper.listProduct(vProductName,(page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int count(String vProductName){
        return vProductMapper.count(vProductName);
    }
    public VProduct chakanvproduct(Integer id){
        return vProductMapper.chakanvproduct(id);
    }
    public int upd(VProduct product) {
        return vProductMapper.upd(product);
    }
    public int add(VProduct product) {
        return vProductMapper.add(product);
    }
    public int del(Integer id) {
        return vProductMapper.del(id);
    }
    public int updearly(VProduct vProduct){
        return vProductMapper.updearly(vProduct);
    }
    public int inventory(VProduct vProduct){
        return vProductMapper.inventory(vProduct);
    }
    public int putawayproduct(VProduct vProduct){
        return vProductMapper.putawayproduct(vProduct);
    }
    public List<VProduct> solrvProduct(String vProductName, Pages page){
        return vProductMapper.solrvProduct(vProductName,(page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
}
