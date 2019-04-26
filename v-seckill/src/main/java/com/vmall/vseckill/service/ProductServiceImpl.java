package com.vmall.vseckill.service;

import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.VSeckillProduct;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private SeckillProductMapper seckillProductMapper;


    @Override
    public boolean addSeckillProduct(VSeckillProduct vSeckillProduct) {
        return seckillProductMapper.insertSeckillProduct(vSeckillProduct)==1?true:false;
    }

    @Override
    public List<VSeckillProduct> loadSeckillProducts() {
        return seckillProductMapper.listSeckillProducts();
    }

    @Override
    public boolean modifyProduct(VSeckillProduct vSeckillProduct) {
        return seckillProductMapper.updateSeckillProduct(vSeckillProduct)==1?true:false;
    }

    @Override
    public boolean haveRemain(Integer seckillProductId) {
        System.out.println(seckillProductMapper.getSeckillProductRemain(seckillProductId));
        return seckillProductMapper.getSeckillProductRemain(seckillProductId)>0;
    }
}
