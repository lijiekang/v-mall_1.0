package com.vmall.vproducts.service.vproduct;

import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VOrder;
import com.vmall.pojo.VOrderDetails;
import com.vmall.pojo.VCategory;
import com.vmall.pojo.VProduct;

import org.apache.ibatis.annotations.Param;
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
    public List<VProduct> productid(Integer id) {
        return vProductMapper.productid(id);
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
    public List<VCategory>vcategoryid(Integer id){
        return vProductMapper.vcategoryid(id);
    }
    //查找所有订单内容
    public List<VOrder>allOrder(){
        return vProductMapper.allOrder();
    }
    //某订单下的订单详情
    public List<VOrderDetails>allOrderDetails(int vOrderId){
        return vProductMapper.allOrderDetails(vOrderId);
    }
    //修改某商品的销售总量
    public int updateProductVolume(@Param("vSalesVolume") int vSalesVolume, @Param("vSalesVolume")int vProductId){
        return vProductMapper.updateProductVolume(vSalesVolume,vProductId);
    }

    //根据ID查商品
    public VProduct getSelectProductById(int vProductId){
        return vProductMapper.getSelectProductById(vProductId);
    }

    //根据条件筛选推荐商品
    public List<VProduct>getSelectProductTop(@Param("vSalesVolume")String vSalesVolume,@Param("vCreateDate")String vCreateDate,@Param("vCommonsCount")String vCommonsCount){
        return vProductMapper.getSelectProductTop(vSalesVolume,vCreateDate,vCommonsCount);
    }


    /**
     * 异常：找到到mapper方法，所以我注释了（李秸康）
     */
//    public List<VCategory>vcategoryid(Integer id){
//        return vProductMapper.vcategoryid(id);
//    }
}
