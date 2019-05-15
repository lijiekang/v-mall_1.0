package com.vmall.vproducts.controller.vclassification;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VCategory;
import com.vmall.vproducts.service.vclassification.VClassificationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*@Controller*/
public class VClassification {
    @Autowired
    VClassificationService vClassificationService;
    @GetMapping("/fen")
    public String getClassificaton(Model model){
            List<VCategory> lv1=vClassificationService.getVCategoryLevel1();
            List<VCategory> lv2=vClassificationService.getVCategoryLevel2(1);
            List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
            model.addAttribute("lv1",lv1);
            model.addAttribute("lv2",lv2);
            model.addAttribute("lv3",lv3);
        return "fenlei";
    }



    @GetMapping(value = "/moClassification",produces = "application/json")
    @ResponseBody
    public Object getMoClassification(Model model, @RequestParam(value = "lvl1",required = false) String lvl1, @RequestParam(value = "lvl2",required = false)String lvl2){
        Map<String,Object>map=new HashMap<>();
        if(lvl1!=null){
            int lv1=Integer.parseInt(lvl1);
            List<VCategory> lv2=vClassificationService.getVCategoryLevel2(lv1);
            long level2=lv2.get(0).getvCategoryId();
            int ls2=(int)level2;
            List<VCategory> lv3=vClassificationService.getVCategoryLevel2(ls2);
            map.put("level2",lv2);
            map.put("level3",lv3);
        }
        if(lvl2!=null){
            int lvs2=Integer.parseInt(lvl2);
            List<VCategory> lv3=vClassificationService.getVCategoryLevel2(lvs2);
            map.put("level3",lv3);
        }
        String str= JSON.toJSONString(map);
        return str;
    }

    @GetMapping("/delClassification")
    public String getDelClassification(Model model, @RequestParam(value = "lvl1",required = false) String lvl1, HttpServletResponse response){
        response.setContentType("text/html;charset=gb2312");
        try {
            PrintWriter out=response.getWriter();
            if(lvl1!=null){
                int lv1=Integer.parseInt(lvl1);
                List<VCategory> lv2=vClassificationService.getVCategoryLevel2(lv1);
                if(lv2.size()>0){
                    List<VCategory> lv1s=vClassificationService.getVCategoryLevel1();
                    List<VCategory> lv2s=vClassificationService.getVCategoryLevel2(1);
                    List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
                    model.addAttribute("lv1",lv1s);
                    model.addAttribute("lv2",lv2s);
                    model.addAttribute("lv3",lv3);
                    out.print("<script language=\"javascript\">alert('该目录下存在子目录！！');</script>");
                }else {
                    int lev1=Integer.parseInt(lvl1);
                    int result=vClassificationService.getDelCategoryLv1ById(lev1);
                    List<VCategory> lv1s=vClassificationService.getVCategoryLevel1();
                    List<VCategory> lv2s=vClassificationService.getVCategoryLevel2(1);
                    List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
                    model.addAttribute("lv1",lv1s);
                    model.addAttribute("lv2",lv2s);
                    model.addAttribute("lv3",lv3);
                    out.print("<script language=\"javascript\">alert('删除成功！！');</script>");
                    System.out.println(result);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "fenlei.html";
    }

    @PostMapping("/addFenleiLevel")
        public String addFenleiLevel(Model model,@RequestParam(value = "vtype")String vtype,@RequestParam(value = "vpid",required = false)String vpid,@RequestParam(value = "vCategoryName")String vCategoryName){
        int vtp=Integer.parseInt(vtype);
        int vpids=0;
        int result=0;
        if(vtp==1){
             result=vClassificationService.addClassification(vtp,0,vCategoryName);
        }else {
            vpids=Integer.parseInt(vpid);
             result=vClassificationService.addClassification(vtp,vpids,vCategoryName);
        }
        List<VCategory> lv1=vClassificationService.getVCategoryLevel1();
        List<VCategory> lv2=vClassificationService.getVCategoryLevel2(1);
        List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
        model.addAttribute("lv1",lv1);
        model.addAttribute("lv2",lv2);
        model.addAttribute("lv3",lv3);
        System.out.println(result);
        return "fenlei.html";
    }
    @GetMapping("/inAddFenleiLevel")
    public String inAddFenleiLevel(Model model,@RequestParam(value = "vtype")String vtype){
        int vtp=Integer.parseInt(vtype);
        List<VCategory>allLevel=null;
        int parentId=0;
        model.addAttribute("vtype",vtp);
        if(vtp==1){
            return "addonefen.html";
        }
        if(vtp>1){
            parentId=vtp-1;
            allLevel=vClassificationService.getVCategoryLevelfen(parentId);
        }
        model.addAttribute("molevel",allLevel);
        return "addtwoorthreefen.html";
    }
}
