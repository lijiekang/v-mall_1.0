package com.vmall.mapper.shoppingcart;

import com.vmall.pojo.VCartproduct;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {
    public int addCart(VCartproduct vCartproduct);
    public int delCart(@Param("v_cartProductId")long v_cartProductId);
}
