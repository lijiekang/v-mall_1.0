package com.vmall.vproducts.service.vseckill;

import com.vmall.mapper.seckill.SeckillOrderMapper;
import com.vmall.pojo.Page;
import com.vmall.pojo.VSeckillOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {
    @Resource
    SeckillOrderMapper seckillOrderMapper;

    public List<VSeckillOrder> seckillOrderList(long statusId, long serialNumber, String productName, Page page) {
        return seckillOrderMapper.seckillOrderList(statusId,serialNumber,productName,(page
                .getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public int count(long statusId, long serialNumber, String productName){
        return seckillOrderMapper.count(statusId,serialNumber,productName);
    }
}
