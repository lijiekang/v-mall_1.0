package com.vmall.vproducts.service.vcategory;

import com.vmall.mapper.vcategorymapper.VCategoryMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VCategoryService {
    @Autowired
    VCategoryMapper vCategoryMapper;

    public List<VCategory> listcategory(String vCategoryName, Pages page) {
        return vCategoryMapper.listcategory(vCategoryName,(page.getPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int count(String vCategoryName){
        return vCategoryMapper.count(vCategoryName);
    }
    public VCategory chakancategory(Integer id){
        return vCategoryMapper.chakancategory(id);
    }
    public List<VCategory>categoryid(Integer id){
        return vCategoryMapper.categoryid(id);
    }
    public int del(Integer id){
        return vCategoryMapper.del(id);
    }
    public int upd(VCategory vCategory){
        return vCategoryMapper.upd(vCategory);
    }
    public int add(VCategory vCategory){
        return vCategoryMapper.add(vCategory);
    }
    public List<VCategory>getcategorylist(){
        return vCategoryMapper.getcategorylist();
    }
    public List<VCategory>getcategoryName(Integer vParentCategoryId){
        return vCategoryMapper.getcategoryName(vParentCategoryId);
    }
}
