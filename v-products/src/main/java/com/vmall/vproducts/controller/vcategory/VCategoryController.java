package com.vmall.vproducts.controller.vcategory;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VCategory;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vproduct.VProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class VCategoryController {

    @Autowired
    VCategoryService vCategoryService;

    @RequestMapping("/commodity")
    public String commodity(@RequestParam(value = "vCategoryName",required = false) String vCategoryName, @RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, Model model){
        int count=vCategoryService.count(vCategoryName);
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VCategory>listcategory=vCategoryService.listcategory(vCategoryName,page);
        List<VCategory>categorylist=vCategoryService.getcategorylist();//查询一级
        model.addAttribute("vCategoryName",vCategoryName);
        model.addAttribute("page",page.getPageNo());
        model.addAttribute("listcategory",listcategory);
        model.addAttribute("categorylist",categorylist);
        model.addAttribute("totalCount",page.getPageye());
        return "commodity";
    }
    @RequestMapping("/tochakancategory")
    public String tochakan(String vCategoryId,Model model){
        VCategory vCategory=vCategoryService.chakancategory(Integer.valueOf(vCategoryId));
        model.addAttribute("vCategory",vCategory);
        model.addAttribute("categoryList",new VCategory());
        return "chakancategory";
    }
    @RequestMapping(value="categorylevellist1",produces="application/json;charset=UTF-8")
    @ResponseBody
    public Object categorylevellist(Integer parentId,Model model){
        List<VCategory>categoryList=vCategoryService.getcategoryName(parentId);
        model.addAttribute("categoryList",categoryList);
        return JSONArray.toJSONString(categoryList);
    }
    @RequestMapping(value = "toaddfenlei",method = RequestMethod.GET)
    public String toaddfenlei(Model model){
        List<VCategory> vclevel=vCategoryService.getcategorylist();
        model.addAttribute("vCategory",vclevel);
        model.addAttribute("categoryList",new VCategory());
        return "addfenlei";
    }
    @RequestMapping("addfenlei")
    public String addfenlei(MultipartFile multipartFile,VCategory vCategory){
        File file1=new File("E:\\tu");
        try {
            String filekey= UUID.randomUUID().toString();
            String fileName=multipartFile.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file1,filekey+suffix));
            if(".png".equals(suffix)||".jpg".equals(suffix)){
                //上传文件
                vCategory.setvIconClass(filekey+suffix);
            }else{
                return "文件格式错误";
            }
            int vc=vCategoryService.add(vCategory);
            if (vc>0){
                return "redirect:/commodity";
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return "addfenlei";
    }
    @RequestMapping("/del")
    @ResponseBody
    public Object del(String vCategoryId){
        String flag="";
        int categorydel=vCategoryService.del(Integer.valueOf(vCategoryId));
        if(categorydel>0){
            flag="true";
        }else{
            flag="false";
        }
        return JSONArray.toJSONString(flag);
    }


}
