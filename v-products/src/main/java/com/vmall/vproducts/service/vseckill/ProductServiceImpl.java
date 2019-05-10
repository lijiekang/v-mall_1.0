package com.vmall.vproducts.service.vseckill;

import com.vmall.mapper.productmapper.VProductMapper;
import com.vmall.mapper.seckill.SeckillProductMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VProduct;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.VSku;
import com.vmall.pojo.vo.CategoryVO;
import com.vmall.pojo.vo.SeckillProductVO;
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
    @Autowired
    private VProductMapper vProductMapper;

    @Override
    public List<VSeckillProduct> listSeckillProductByPage() {
        return seckillProductMapper.listSeckillProducts();
    }

    @Override
    public boolean addSeckillPorduct(SeckillProductVO seckillProductVO) {
        return seckillProductMapper.insertSeckillProduct(seckillProductVO)==1?true:false;
    }


    @Override
    public List<VProduct> listProductByLevels(CategoryVO categoryVO) {
        return vProductMapper.listProductsByLevels(categoryVO);
    }


    @Override
    public List<VSku> listSkuByProductId(Integer productId) {
        return vProductMapper.listSkuByProductId(productId);
    }
}
