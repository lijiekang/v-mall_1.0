package com.vmall.vproducts.controller.vproduct;


import com.alibaba.fastjson.JSONArray;

import com.vmall.pojo.Pages;
import com.vmall.pojo.VCategory;
import com.vmall.pojo.VProduct;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vproduct.VProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
//@Api(tags = "商品")
public class VProductController {
    @Autowired
    VProductService vProductService;
    @Autowired
    VCategoryService vCategoryService;
    @RequestMapping("/index")
    public String toindex(){
        return "index";
    }
    @RequestMapping("/chakan")
    public String tochakan(){
        return "chakan";
    }

    @RequestMapping("/tables")
    public String totables(){
        return "redirect:/getproduct";
    }
    /*@ApiOperation(value = "搜索商品")
    @ApiImplicitParam(paramType = "path",required = true)*/
    @RequestMapping("/getproduct")
    public String getproduct(@RequestParam(value = "vProductName",required = false) String vProductName, @RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, Model model, HttpSession session){
       int count=vProductService.count(vProductName);
       int pageno=0;
       if(pageNo!=""){
           pageno=Integer.valueOf(pageNo);
       }
       Pages page=new Pages();
       page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VProduct>listproduct=vProductService.listProduct(vProductName,page);
        List<VCategory>vclevel=vCategoryService.getcategorylist();
        model.addAttribute("vProductName",vProductName);
        model.addAttribute("page",page.getPageNo());
        model.addAttribute("listproduct",listproduct);
        model.addAttribute("totalCount",page.getPageye());
        session.setAttribute("vclevel",vclevel);
        return "tables";
    }
    /*@ApiOperation(value = "查看商品",notes = "通过id查看商品")
    @GetMapping("/chakan/{id}")*/
    @RequestMapping(value = "/tochakan")
    public String chakan(String vProductId,Model model){
       VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        model.addAttribute("vproduct",vproduct);
        return "chakan";
    }
    /*@ApiResponses({
            @ApiResponse(code=200,message = "删除成功！"),
            @ApiResponse(code = 500,message = "删除失败!")
    })
    @ApiOperation(value = "删除商品",notes = "通过id删除商品")
    @DeleteMapping("/productid/{id}")*/
    @RequestMapping("/delete")
    @ResponseBody
    public Object productid(String vProductId){
        String flag="";
        int productiddel=vProductService.del(Integer.valueOf(vProductId));
        if(productiddel>0){
            flag="true";
        }else{
            flag="false";
        }
        return JSONArray.toJSONString(flag);
    }
    @RequestMapping(value="categorylevellist",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object categorylevellist(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("queryCategoryLevel1"));
        List<VCategory>categoryList=vCategoryService.getcategoryName(id);
        return JSONArray.toJSONString(categoryList);
    }
    @RequestMapping(value = "toadd",method = RequestMethod.GET)
    public String toadd(Model model){
      /*  List<VCategory>categoryList=vCategoryService.getcategoryName(Integer.valueOf(vProductId));
        model.addAttribute("categoryList",categoryList);*/
    List<VCategory>vclevel=vCategoryService.getcategorylist();
    model.addAttribute("vclevel",vclevel);
        return "add";
    }

    @RequestMapping("/add")
    public String add(MultipartFile multipartFile,VProduct vProduct){
        File file1=new File("E:\\tu");
        try {
            String filekey=UUID.randomUUID().toString();
            String fileName=multipartFile.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file1,filekey+suffix));
            if(".png".equals(suffix)||".jpg".equals(suffix)){
                //上传文件
                vProduct.setvImgUrl(filekey+suffix);
            }else{
                return "文件格式错误";
            }
            int vp=vProductService.add(vProduct);
            if (vp>0){
                return "redirect:/getproduct";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "add";
    }
    @RequestMapping("/toupd")
    public String toupd(String vProductId,Model model){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        List<VCategory>vclevel=vCategoryService.getcategorylist();
        model.addAttribute("vclevel",vclevel);
        model.addAttribute("vproduct",vproduct);
        return "upd";
    }
    @RequestMapping("upd")
    public String upd(MultipartFile multipartFile,VProduct vProduct){
        File file1=new File("E:\\tu");
        try {
            String filekey=UUID.randomUUID().toString();
            String fileName=multipartFile.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file1,filekey+suffix));
            if(".png".equals(suffix)||".jpg".equals(suffix)){
                //上传文件
                vProduct.setvImgUrl(filekey+suffix);
            }else{
                return "文件格式错误";
            }
            int vp=vProductService.upd(vProduct);
            if (vp>0){
                return "redirect:/getproduct";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "upd";
    }
    /*@ApiOperation(value = "添加商品",notes = "添加一个商品")
    @PostMapping(value = "/add",consumes = "multipart/*",headers = "content-type=multipart/form-data" )
    public Object addproduct(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile, @ModelAttribute("vProduct") VProduct vProduct){
        HashMap<String, String> resultMap = new HashMap<String, String>();
        File file1=new File("E:\\tu");
       try {
           String filekey=UUID.randomUUID().toString();
           String fileName=multipartFile.getOriginalFilename();
           String suffix=fileName.substring(fileName.indexOf("."));
           multipartFile.transferTo(new File(file1,filekey+suffix));
           if(".png".equals(suffix)||".jpg".equals(suffix)){
               //上传文件
               vProduct.setvImgUrl(filekey+suffix);
           }else{
               return "文件格式错误";
           }
           int vp=vProductService.add(vProduct);
           if(vp>0){
               resultMap.put("error","添加成功");
           }else{
               resultMap.put("error","添加失败");
           }
       }catch (IOException e){
           e.printStackTrace();
       }
        return resultMap;
    }

    @ApiOperation(value = "修改商品",notes = "修改商品,传入商品信息")
    @PutMapping(value = "/updproduct",consumes = "multipart/*",headers = "content-type=multipart/form-data")
    public Object updproduct(@ApiParam(value = "修改文件" ,required = true,name ="multipartFile" ) MultipartFile multipartFile, @ModelAttribute("vProduct") VProduct vProduct){
        HashMap<String, String> resultMap = new HashMap<String, String>();
        File file1=new File("E:\\tu");
       try {
           String filekey=UUID.randomUUID().toString();
           String fileName=multipartFile.getOriginalFilename();
           String suffix=fileName.substring(fileName.indexOf("."));
           multipartFile.transferTo(new File(file1,filekey+suffix));
           if(".png".equals(suffix)||".jpg".equals(suffix)){
               //上传文件
               vProduct.setvImgUrl(filekey+suffix);
           }else{
               return "文件格式错误";
           }
           int vp=vProductService.upd(vProduct);
           if(vp>0){
               resultMap.put("error","修改成功");
           }else{
               resultMap.put("error","修改失败");
           }
       }catch (IOException e){
           e.printStackTrace();
       }
        return resultMap;
    }*/



}
