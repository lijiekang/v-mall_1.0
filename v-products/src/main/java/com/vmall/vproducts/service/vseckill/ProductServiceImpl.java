package com.vmall.vproducts.service.vseckill;

import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 12:25 2019-05-07
 * @Modifyied By:
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SeckillProductMapper seckillProductMapper;


    @Override
    public List<VSeckillProduct> listSeckillProductByPage() {
        return seckillProductMapper.listSeckillProducts();
    }

    @Override
    public boolean addSeckillPorduct(VSeckillProduct vSeckillProduct) {
        return seckillProductMapper.insertSeckillProduct(vSeckillProduct)==1?true:false;
    }


}
