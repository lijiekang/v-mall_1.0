package com.vmall.vproducts.controller.vactivity;

import com.vmall.pojo.Pages;
import com.vmall.pojo.VActivity;
import com.vmall.vproducts.service.vactivity.VActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class VActivityController {
    @Autowired
    VActivityService vActivityService;
    @RequestMapping("/activity")
    public String activity(@RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, Model model){
        int count=vActivityService.count();
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VActivity>vActivitielist=vActivityService.listvActivity(page);
        model.addAttribute("vActivitielist","vActivitielist");
        model.addAttribute("page",page.getPageNo());
        model.addAttribute("totalCount",page.getPageye());
        return "activity";
    }
}
