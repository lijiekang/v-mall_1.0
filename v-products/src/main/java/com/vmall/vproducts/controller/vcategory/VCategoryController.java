package com.vmall.vproducts.controller.vcategory;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Pages;
import com.vmall.pojo.VCategory;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vproduct.VProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
