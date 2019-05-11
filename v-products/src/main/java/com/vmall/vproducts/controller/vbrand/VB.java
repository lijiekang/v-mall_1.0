package com.vmall.vproducts.controller.vbrand;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VBrand;
import com.vmall.vproducts.service.vbrand.VBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ObjectOutputStream;
import java.util.List;

/*@RestController
@Api(tags = "商品品牌")*/
@RestController
@RequestMapping("/vbrand")
public class VB {
    @Autowired
    VBrandService vBrandService;

    @GetMapping("/brand")//查询所有商品品牌列表
    public void brand(String vBrandName,@RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, HttpServletResponse httpServletResponse){
        int count=vBrandService.count(vBrandName);
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VBrand>listvbrand=vBrandService.vbrandlist(vBrandName,page);
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(listvbrand));
            os.writeObject(JSONArray.toJSON(page.getPageNo()));
            os.writeObject(JSONArray.toJSON(page.getPageye()));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("tobrandchakan")//查看商品品牌
    public void tobrandchakan(String vBrandId,HttpServletResponse httpServletResponse){
        VBrand vBrand=vBrandService.chakanbrand(Integer.valueOf(vBrandId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vBrand));

            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/delbrabd")//商品品牌删除
    @ResponseBody
    public void del(String vBrandId, HttpServletResponse httpServletResponse){
        String flag="";
        int delBrand=vBrandService.vbranddel(Integer.valueOf(vBrandId));
        if(delBrand>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @GetMapping("/addbrand")//商品品牌添加
    public void addbrand(VBrand vBrand, HttpServletResponse httpServletResponse){
        int add=vBrandService.vbrandadd(vBrand);
        String flag="";
        if(add>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/tobrandupd")//去修改商品品牌
    public void tobrandupd(String vBrandId,HttpServletResponse httpServletResponse){
        VBrand vBrand=vBrandService.chakanbrand(Integer.valueOf(vBrandId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vBrand));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/brandupd")//修改商品品牌
    public void brandupd(VBrand vBrand,HttpServletResponse httpServletResponse){
        int upd=vBrandService.vbrandupd(vBrand);
        String flag="";
        if(upd>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("putawayupd")//修改上架
    @ResponseBody
    public void putawayupd(VBrand vBrand, HttpServletResponse httpServletResponse){
        String flag="";
        int upd=vBrandService.putawayupd(vBrand);
        if (upd>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RequestMapping("putawayupdxia")//修改下架
    @ResponseBody
    public void putawayupdxia(VBrand vBrand,HttpServletResponse httpServletResponse){
        vBrand.setvPutaway(1);
        String flag="";
        int upd=vBrandService.putawayupd(vBrand);
        if (upd>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(flag));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /*@GetMapping("a")
    public Map<String,Object> name(){
        return SolrUtil.SolrName()
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

