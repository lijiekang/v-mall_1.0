package com.vmall.vproducts.controller.vbrand;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VBrand;
import com.vmall.vproducts.service.vbrand.VBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/*@RestController
@Api(tags = "商品品牌")*/
@Controller
public class VBrandController {
    @Autowired
    VBrandService vBrandService;

    @RequestMapping("/brand")
    public String brand(String vBrandName, @RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, Model model){
        int count=vBrandService.count(vBrandName);
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VBrand>listvbrand=vBrandService.vbrandlist(vBrandName,page);
        model.addAttribute("listvbrand",listvbrand);
        model.addAttribute("page",page.getPageNo());
        model.addAttribute("totalCount",page.getPageye());
        return "brand";
    }
    @RequestMapping("tobrandchakan")
    public String tobrandchakan(String vBrandId,Model model){
        VBrand vBrand=vBrandService.chakanbrand(Integer.valueOf(vBrandId));
        model.addAttribute("vBrand",vBrand);
        return "chakanbrand";
    }
    @RequestMapping("/delbrabd")
    @ResponseBody
    public Object del(String vBrandId){
        String flag="";
        int delBrand=vBrandService.vbranddel(Integer.valueOf(vBrandId));
        if(delBrand>0){
            flag="true";
        }else{
            flag="false";
        }
        return JSONArray.toJSONString(flag);
    }
    /*public String chakan(String vProductId,Model model){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        model.addAttribute("vproduct",vproduct);
        return "chakan";
    }*/
    /*@ApiOperation(value = "添加商品品牌",notes = "添加一个商品品牌")
    @PostMapping("/vbrandadd")
    public Object vbrandadd(){
        return "";
    }

    @ApiResponses({
            @ApiResponse(code=200,message = "删除成功！"),
            @ApiResponse(code = 500,message = "删除失败!")
    })
    @ApiOperation(value = "删除商品品牌",notes = "通过id删除商品品牌")
    @DeleteMapping("/vbranddel/{id}")
    public Object vbranddel(@PathVariable Integer id){
        HashMap<String, String> resultMap = new HashMap<String, String>();
        int del=vBrandService.vbranddel(id);
        if(del>0){
            resultMap.put("error","删除成功");
        }else{
            resultMap.put("error","删除失败");

        }        return JSONArray.toJSONString(del);
    }*/
}

