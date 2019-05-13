package com.vmall.vproducts.controller.seckill;

import com.vmall.pojo.VCategory;
import com.vmall.pojo.VSeckillProduct;
import com.vmall.pojo.vo.CategoryVO;
import com.vmall.pojo.vo.SeckillProductVO;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vseckill.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 李秸康
 * @Description:
 * @Date created in 12:28 2019-05-07
 * @Modifyied By:
 */
@Controller
public class VSeckillProductController {

    @Autowired
    private ProductService productService;


    @Autowired
    private VCategoryService vCategoryService;

    /**
     * 秒杀列表显示
     * @param model
     * @return
     */
    @RequestMapping(value="/seckill")
    public String toSeckillProductList(Model model){
        List<VSeckillProduct> products =productService.listSeckillProductByPage();
        model.addAttribute("products",products);
        return "seckill/seckill_product";

    }


    /**
     * 跳转到添加商品页面
     * @param model
     * @return
     */
    @GetMapping(value="/seckillProduct")
    public String toSeckillProductAdd(Model model){
        List<VCategory> categories=vCategoryService.getcategorylist();
        model.addAttribute("categoryLevel1s",categories);
        return "seckill/seckill_product_add";
    }

    /**
     * 新增秒杀商品
     * @param seckillProductVO
     * @return
     */
    @PostMapping(value="/seckillProduct")
    public String doSeckillProductAdd(SeckillProductVO seckillProductVO){
        if(productService.addSeckillPorduct(seckillProductVO))
            return "redirect:/seckill";
        else
            return "redirect:seckillProduct";
    }


    /**
     * 查询分类信息
     * @param parentId
     * @return
     */
    @RequestMapping(value="/categorys")
    public @ResponseBody Object getCategoryLevelByParentId(Integer parentId){
        System.out.println(parentId);
        List<VCategory> categories=vCategoryService.getcategoryName(parentId);

        return categories;
    }


    /**
     * 根据分类查询商品
     * @param categoryVO
     * @return
     */
    @RequestMapping(value="/products")
    public @ResponseBody Object getProductByLevels(CategoryVO categoryVO){
        return productService.listProductByLevels(categoryVO);
    }


    /**
     * 查询SKU
     * @param productId 商品id
     * @return
     */
    @RequestMapping(value="/SKU")
    public @ResponseBody Object getSKUByProductId(Integer productId){
        return productService.listSkuByProductId(productId);
    }




}
