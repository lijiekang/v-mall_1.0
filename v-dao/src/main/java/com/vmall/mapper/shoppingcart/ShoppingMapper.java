package com.vmall.mapper.shoppingcart;

import com.vmall.pojo.VShoppingcart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingMapper {
    public List<VShoppingcart> cartList(@Param("userId")long userId,
                                        @Param("pageNo")long pageNo,
                                        @Param("pageSize")long pageSize);
    public int addShopping(@Param("cartProductId")long cartProductId,@Param("userId")long userId);
    public int delShopping(@Param("v_cartId")long v_cartId);
    public int count(@Param("userId")long userId);
}
