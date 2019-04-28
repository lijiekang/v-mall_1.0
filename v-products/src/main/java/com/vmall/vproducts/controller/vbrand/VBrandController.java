package com.vmall.vproducts.controller.vbrand;

import com.alibaba.fastjson.JSONArray;
import com.vmall.vproducts.service.vbrand.VBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@Api(tags = "商品品牌")
public class VBrandController {
    @Autowired
    VBrandService vBrandService;

    @ApiOperation(value = "添加商品品牌",notes = "添加一个商品品牌")
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
    }
}

