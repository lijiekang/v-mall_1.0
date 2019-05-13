package com.vmall.vproducts.controller.vclassification;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.VCategory;
import com.vmall.vproducts.service.vclassification.VClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class VClassificationJieController {
    @Autowired
    VClassificationService vClassificationService;

    /**
     * 进入分类管理页面
     * lv1一级标题
     * lv2二级标题
     * lv3三级标题
     * @param httpServletResponse
     */
    @GetMapping("/infen")
    public void getinClassificaton(HttpServletResponse httpServletResponse){
        List<VCategory> lv1=vClassificationService.getVCategoryLevel1();
        List<VCategory> lv2=vClassificationService.getVCategoryLevel2(1);
        List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
        Map<String,Object> map=new HashMap<>();
        map.put("lv1",lv1);
        map.put("lv2",lv2);
        map.put("lv3",lv3);
        try {
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过传入ID判断当前分类等级
     * @param lvl1 判断是否为一级标题
     * @param lvl2 通过传入标题父级ID，判断为几级标签，进行新增处理
     * @param httpServletResponse
     */
    @GetMapping(value = "/moClassification",produces = "application/json")
    public void getMoClassification( @RequestParam(value = "lvl1",required = false) String lvl1, @RequestParam(value = "lvl2",required = false)String lvl2,HttpServletResponse httpServletResponse){
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
        try {
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param lvl1 传入标题ID,根据ID判断该分类ID下是否存在子目录，存在就不执行删除操作，不存在就执行
     * @param response
     * @return
     */
    @GetMapping("/delClassification")
    public void getDelClassification(@RequestParam(value = "lvl1",required = false) String lvl1, HttpServletResponse response){
        response.setContentType("text/html;charset=gb2312");
        try {
            /*PrintWriter out=response.getWriter();*/
            Map<String,Object>allCategory=new HashMap<>();
            if(lvl1!=null){
                int lv1=Integer.parseInt(lvl1);
                //查询该标题下是否存在子目录
                List<VCategory> lv2=vClassificationService.getVCategoryLevel2(lv1);
                if(lv2.size()>0){
                    List<VCategory> lv1s=vClassificationService.getVCategoryLevel1();
                    List<VCategory> lv2s=vClassificationService.getVCategoryLevel2(1);
                    List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
                    allCategory.put("lv1s",lv1s);
                    allCategory.put("lv1s",lv2s);
                    allCategory.put("lv3",lv3);
                    /*out.print("<script language=\"javascript\">alert('该目录下存在子目录！！');</script>");*/
                }else {
                    int lev1=Integer.parseInt(lvl1);
                    //如果不存在，调用删除方法删除当前ID目录
                    int result=vClassificationService.getDelCategoryLv1ById(lev1);
                    List<VCategory> lv1s=vClassificationService.getVCategoryLevel1();
                    List<VCategory> lv2s=vClassificationService.getVCategoryLevel2(1);
                    List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
                    allCategory.put("lv1s",lv1s);
                    allCategory.put("lv1s",lv2s);
                    allCategory.put("lv3",lv3);
                    /*out.print("<script language=\"javascript\">alert('删除成功！！');</script>");*/
                }
            }
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(allCategory));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param vtype 判断属于第几级分类
     * @param vpid 商品ID
     * @param vCategoryName
     * @param httpServletResponse
     */
    @PostMapping("/addFenleiLevel")
    public void addFenleiLevel(@RequestParam(value = "vtype")String vtype,@RequestParam(value = "vpid",required = false)String vpid,@RequestParam(value = "vCategoryName")String vCategoryName,HttpServletResponse httpServletResponse){
        int vtp=Integer.parseInt(vtype);
        int vpids=0;
        int result=0;
        Map<String,Object>map=new HashMap<>();
        if(vtp==1){
            result=vClassificationService.addClassification(vtp,0,vCategoryName);
        }else {
            vpids=Integer.parseInt(vpid);
            result=vClassificationService.addClassification(vtp,vpids,vCategoryName);
        }
        List<VCategory> lv1=vClassificationService.getVCategoryLevel1();
        List<VCategory> lv2=vClassificationService.getVCategoryLevel2(1);
        List<VCategory> lv3=vClassificationService.getVCategoryLevel2(18);
        map.put("lv1",lv1);
        map.put("lv2",lv2);
        map.put("lv3",lv3);
        try {
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);

    }

    /**
     * 查看不同等级的分类
     * @param vtype 传入等级信息，判断等级
     * @param httpServletResponse
     */
    @GetMapping("/inAddFenleiLevel")
    public void inAddFenleiLevel(@RequestParam(value = "vtype")String vtype,HttpServletResponse httpServletResponse){
        int vtp=Integer.parseInt(vtype);
        List<VCategory>allLevel=null;
        Map<String,Object>map=new HashMap<>();
        int parentId=0;
        map.put("vtype",vtp);
//传递等级下的分类信息
//        model.addAttribute("vtype",vtp);
        if(vtp==1){
//返回分类界面
//            return "addonefen.html";
        }
        if(vtp>1){
            parentId=vtp-1;
            allLevel=vClassificationService.getVCategoryLevelfen(parentId);
        }
        map.put("molevel",allLevel);
        try {
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //传递等级下的分类信息
//        model.addAttribute("molevel",allLevel);
        //返回分类界面
//        return "addtwoorthreefen.html";
    }
}
