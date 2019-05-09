package com.vmall.vauth.service.dev;

import com.vmall.mapper.shoppingcart.CartMapper;
import com.vmall.mapper.shoppingcart.ShoppingMapper;
import com.vmall.mapper.sku.SkuMapper;
import com.vmall.pojo.*;
import com.vmall.vauth.service.tool.TokenService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShoppingService {
    @Resource
    ShoppingMapper shoppingMapper;
    @Resource
    CartMapper cartMapper;
    @Resource
    SkuMapper skuMapper;
    @Resource
    TokenService tokenService;
    public int count(String token){
        VUser vUser=(VUser)tokenService.get(token);
        return shoppingMapper.count(vUser.getvUserId());
    }
    public List<VShoppingcart> shoppingcartList(String token, Page page){
        VUser vUser=(VUser)tokenService.get(token);
        return shoppingMapper.cartList(vUser.getvUserId(),(page.getCurrentPageNo()-1)*page.getPageSize(),page.getPageSize());
    }
    public boolean addShoppingCart(String token,String skuCode,int num){
        VUser vUser=(VUser)tokenService.get(token);
        VSku sku=skuMapper.getVSkuBySkuCode(skuCode);
        VCartproduct cartproduct=new VCartproduct();
        cartproduct.setVSkuId(sku.getVSkuId());
        cartproduct.setVNum(num);
        if(cartMapper.addCart(cartproduct)>0){
            if (shoppingMapper.addShopping(cartproduct.getVCartProductId(),vUser.getvUserId())>0){
                return true;
            }
        }
        return false;
    }
    public boolean delShoppingCart(int cartId,int cartProductId){
        return shoppingMapper.delShopping(cartId)>0&&cartMapper.delCart(cartProductId)>0;
    }
}
