package com.vmall.vproducts.controller.vsku;

import com.alibaba.fastjson.JSONArray;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.vmall.pojo.VProperties;
import com.vmall.pojo.VProperty;
import com.vmall.vproducts.service.vsku.VSkuService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class VSkuController {
    @Autowired
    VSkuService vSkuService;

    //进入SKU页面
    @GetMapping("/inAllSku")
    public String allSku(Model model,@RequestParam(value = "vPropertiesId",required = false) String vPropertiesId){
        int pid=0;
        if(vPropertiesId!=null){
            pid=Integer.parseInt(vPropertiesId);
        }
        List<VProperties>allpro=vSkuService.getAllSku(pid);
        model.addAttribute("allpro",allpro);
        return "allSKU.html";
    }

    //进入添加SKU界面
    @GetMapping("/inaddSku")
    public String inaddSku(){
        return "addSKU.html";
    }
    //添加SKU
    @PostMapping("/addSku")
    public Object getAddSuk(Model model,@RequestParam(value = "vPropertiesName")String vPropertiesName, HttpServletResponse response){
        response.setContentType("text/html;charset=gb2312");
        try {
            PrintWriter out=response.getWriter();
            int result=vSkuService.getInsertSku(vPropertiesName);
            if(result>0){
                List<VProperties>allpro=vSkuService.getAllSku(0);
                model.addAttribute("allpro",allpro);
                out.print("<script language=\"javascript\">alert('添加成功！！');</script>");
                return "allSKU.html";
            }else {
                out.print("<script language=\"javascript\">alert('添加失败！！');</script>");
                return "addSKU.html";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "allSKU.html";

    }
    //进入修改规格界面
    @GetMapping("/updateSkuGui")
    public String updateSkuGui(Model model,String pid,String pname){
        model.addAttribute("pid",pid);model.addAttribute("pname",pname);
        return "updateSKU.html";
    }
    //修改规格
    @PostMapping("/updateSkuG")
    public String updateSkuG(Model model,@RequestParam(value = "vPropertiesId")String vPropertiesId,@RequestParam(value = "vPropertiesName")String vPropertiesName){
        int pid=Integer.parseInt(vPropertiesId);
        int result=vSkuService.getUpdateSku(vPropertiesName,pid);
        if(result>0){
            List<VProperties>allpro=vSkuService.getAllSku(0);
            model.addAttribute("allpro",allpro);
            return "allSKU.html";
        }
        return "updateSKU.html";
    }
    //进入修改规格值
    @GetMapping("/updateSkuGuiZhi")
    public String updateSkuGuiZhi(Model model,@RequestParam(value = "pid")String pid){
        int pids=Integer.parseInt(pid);
        List<VProperties>allpro=vSkuService.getAllSku(pids);
        List<VProperty>allp=vSkuService.getmoSku(pids);
        model.addAttribute("pids",pids);
        model.addAttribute("allprogui",allp);
        return "allSKUgui.html";
    }
    //进入添加规格值页面
    @GetMapping("/addSkuGuiZhi")
    public String addSkuGuizhi(Model model, String pid){
        int ppid=Integer.parseInt(pid);
        model.addAttribute("ppid",ppid);
        return "addSKUgui.html";
    }
    @PostMapping("/addSkugui")
    public String addSkugui(@RequestParam(value = "vPropertiesId")String vPropertiesId,@RequestParam(value = "vPropertiesValue")String vPropertiesName){
        int vpid=Integer.parseInt(vPropertiesId);
        vSkuService.getAddSkuGui(vPropertiesName,vpid);
        return "allSKU.html";
    }
    //删除规格值
    @GetMapping("delSkuGui")
    public String delSkuGui(Model model,@RequestParam(value = "pid")String pid){
        return "";
    }
    @RequestMapping(method = RequestMethod.GET, value = "allSuk")
    @ResponseBody
    public Object getAllSuk(@RequestParam(value = "vPropertiesId",required = false) String vPropertiesId){
        int pid=0;
        if(vPropertiesId!=null){
            pid=Integer.parseInt(vPropertiesId);
        }
        List<VProperties>allpro=vSkuService.getAllSku(pid);
        String str= JSONArray.toJSONString(allpro);
        return str;
    }
    @RequestMapping(method = RequestMethod.GET, value = "delSuk")
    @ResponseBody
    public Object getDelSuk(@RequestParam(value = "vPropertiesId",required = false)String vPropertiesId,@RequestParam(value = "vParentId",required = false)String vParentId){
        int pid=0;
        int parentid=0;
        Map<String,Object>map=new HashMap<>();
        if(vPropertiesId!=null){
            pid=Integer.parseInt(vPropertiesId);
            int result=vSkuService.getDelSkuChild(pid);
            int res=vSkuService.getDelSku(pid);
            map.put("pan","删除总规格成功");
        }

        if(vParentId!=null){
            parentid=Integer.parseInt(vParentId);
            int res=vSkuService.getDelSkuMoChild(parentid);
            map.put("pan","删除子规格成功");
        }
        String str=JSONArray.toJSONString(map);
        return str;
    }
    @RequestMapping(method = RequestMethod.GET, value = "insertSuk")
    @ResponseBody
    public Object getInsertSuk(@RequestParam(value = "vPropertiesName")String vPropertiesName){
        int result=vSkuService.getInsertSku(vPropertiesName);
        Map<String,Object>map=new HashMap<>();
        if(result>0){
            map.put("add","新增成功");
        }
        String str=JSONArray.toJSONString(map);
        return str;
    }
    @RequestMapping(method = RequestMethod.GET, value = "updateSuk")
    @ResponseBody
    public Object getUpdateSuk(@RequestParam(value = "vPropertiesId")String vPropertiesId,@RequestParam(value = "vPropertiesName")String vPropertiesName){
       int pid=Integer.parseInt(vPropertiesId);
        int result=vSkuService.getUpdateSku(vPropertiesName,pid);
        Map<String,Object>map=new HashMap<>();
        if(result>0){
            map.put("add","修改成功");
        }
        String str=JSONArray.toJSONString(map);
        return str;
    }
    //批量删除
    @RequestMapping(method = RequestMethod.GET, value = "piDelSuk")
    @ResponseBody
    public Object getDelPiSuk(String str){
        Map<String,Object>map=new HashMap<>();
        String[]arges=str.split(",");
        for (int i=0;i<arges.length;i++){
            int idi= Integer.parseInt(arges[i]);
            System.out.println(idi);
            int result=vSkuService.getDelSkuChild(idi);
            int res=vSkuService.getDelSku(idi);
        }
        map.put("pidel","批量删除成功");
        String strss=JSONArray.toJSONString(map);
        return strss;
    }
}
