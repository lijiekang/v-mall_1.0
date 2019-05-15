package com.vmall.vproducts.controller.vproduct;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.*;
import com.vmall.vproducts.config.SolrUtil;
import com.vmall.vproducts.service.vbrand.VBrandService;
import com.vmall.vproducts.service.vcategory.VCategoryService;
import com.vmall.vproducts.service.vproduct.VProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

@RestController
@Api(tags = "商品")
public class VProductControllerJie {
    @Autowired
    VProductService vProductService;
    @Autowired
    VCategoryService vCategoryService;
    @Autowired
    VBrandService vBrandService;


    @GetMapping(value = "/tables")
    public String totables(){
        return "redirect:/getproduct";
    }
    /*@ApiOperation(value = "搜索商品")
    @ApiImplicitParam(paramType = "path",required = true)*/

    /**
     * 查询商品
     * @param vProductName
     * @param pageNo
     * @return
     */
    @GetMapping("/getproduct")//查询所有的商品
    @ResponseBody
    public void getproduct(@RequestParam(value = "vProductName",required = false) String vProductName, @RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo, HttpServletResponse httpServletResponse){
        int count=vProductService.count(vProductName);
        int pageno=0;
        if(pageNo!=""){
            pageno=Integer.valueOf(pageNo);
        }
        Pages page=new Pages();
        page.setPageNo(pageno);
        page.setPagetiao(count);
        List<VProduct> listproduct=vProductService.listProduct(vProductName,page);
        List<VCategory>vclevel=vCategoryService.getcategorylist();
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vProductName));
            os.writeObject(JSONArray.toJSON(listproduct));
            os.writeObject(JSONArray.toJSON(vclevel));
            os.writeObject(JSONArray.toJSON(page.getPageNo()));
            os.writeObject(JSONArray.toJSON(page.getPageye()));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*@ApiOperation(value = "查看商品",notes = "通过id查看商品")
    @GetMapping("/chakan/{id}")*/
    @GetMapping(value = "/tochakan")//查看商品
    public void chakan(String vProductId, HttpServletResponse httpServletResponse){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vproduct));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*@ApiResponses({
            @ApiResponse(code=200,message = "删除成功！"),
            @ApiResponse(code = 500,message = "删除失败!")
    })
    @ApiOperation(value = "删除商品",notes = "通过id删除商品")
    @DeleteMapping("/productid/{id}")*/
    @GetMapping("/delete")//删除商品
    @ResponseBody
    public void productid(String vProductId, HttpServletResponse httpServletResponse){
        String flag="";
        int productiddel=vProductService.del(Integer.valueOf(vProductId));
        if(productiddel>0){
            flag="true";
        }else{
            flag="false";
        }
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(false));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping(value="categorylevellist",produces="application/json;charset=UTF-8")//查询二级分类
    @ResponseBody
    public void categorylevellist(HttpServletRequest request, HttpServletResponse httpServletResponse){
        int id=Integer.parseInt(request.getParameter("queryCategoryLevel1"));
        List<VCategory>categoryList=vCategoryService.getcategoryName(id);
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(categoryList));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping(value = "toadd")//去商品添加
    public void toadd(HttpServletResponse httpServletResponse){
      /*  List<VCategory>categoryList=vCategoryService.getcategoryName(Integer.valueOf(vProductId));
        model.addAttribute("categoryList",categoryList);*/
        List<VCategory>vclevel=vCategoryService.getcategorylist();
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vclevel));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 新增商品
     * @param multipartFile
     * @param vProduct
     * @return
     */
    @GetMapping("/add")
    public void add(MultipartFile multipartFile, VProduct vProduct, HttpServletResponse httpServletResponse){
        File file1=new File("E:\\tu");
        try {
            String filekey= UUID.randomUUID().toString();
            String fileName=multipartFile.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file1,filekey+suffix));
            if(".png".equals(suffix)||".jpg".equals(suffix)){
                //上传文件
                vProduct.setvImgUrl(filekey+suffix);
            }
            int vp=vProductService.add(vProduct);
            String flag="";
            if(vp>0){
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
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    @GetMapping("/toupd")//去修改商品
    public void toupd(String vProductId,HttpServletResponse httpServletResponse){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        //查询所有的品牌
        List<VBrand>early=vBrandService.chabrand();
        List<VCategory> vclevel=vCategoryService.getcategorylist();
        List<VCategory> vcleve2 = vCategoryService.getcategoryName((int) vproduct.getvCategoryLevel1());
        List<VCategory> vcleve3=vCategoryService.getcategoryName((int) vproduct.getvCategoryLevel2());

        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(early));
            os.writeObject(JSONArray.toJSON(vclevel));
            os.writeObject(JSONArray.toJSON(vcleve2));
            os.writeObject(JSONArray.toJSON(vcleve3));
            os.writeObject(JSONArray.toJSON(vProductId));
            os.writeObject(JSONArray.toJSON(vproduct));
            os.writeObject(JSONArray.toJSON(vproduct.getvCategoryLevel1()));
            os.writeObject(JSONArray.toJSON(vproduct.getvCategoryLevel2()));
            os.writeObject(JSONArray.toJSON(vproduct.getvCategoryLevel3()));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改商品
     * @param multipartFile
     * @param vProduct
     * @return
     */
    @GetMapping("upd")
    public void upd(MultipartFile multipartFile,VProduct vProduct,HttpServletResponse httpServletResponse){
        File file1=new File("E:\\tu");
        try {
            String filekey=UUID.randomUUID().toString();
            String fileName=multipartFile.getOriginalFilename();
            String suffix=fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file1,filekey+suffix));
            if(".png".equals(suffix)||".jpg".equals(suffix)){
                //上传文件
                vProduct.setvImgUrl(filekey+suffix);
            }
            int vp=vProductService.upd(vProduct);
            String flag="";
            if(vp>0){
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
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * 查询预警货品
     * @param vProductName
     * @param pageNo
     * @return
     */
    @GetMapping("/early")
    public void early(String vProductName, @RequestParam(value = "pageNo",defaultValue = "1",required = false) String pageNo,HttpServletResponse httpServletResponse){
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
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vProductName));
            os.writeObject(JSONArray.toJSON(listproduct));
            os.writeObject(JSONArray.toJSON(page.getPageNo()));
            os.writeObject(JSONArray.toJSON(page.getPageye()));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @GetMapping("/toearlyupd")//去修改预警货品
    public void toupdearly(String vProductId,HttpServletResponse httpServletResponse){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vproduct));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @GetMapping("/earlyupd")//修改预警货品
    public void earlyupd(VProduct vProduct,HttpServletResponse httpServletResponse){
        int updearly=vProductService.updearly(vProduct);
        String flag="";
        if(updearly>0){
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
    @GetMapping("toinventory")//查询商品库存
    public void toinventory(String vProductId,HttpServletResponse httpServletResponse){
        VProduct vproduct=vProductService.chakanvproduct(Integer.valueOf(vProductId));
        try{
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(vproduct));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 修改商品库存

     * @return
     */
    @GetMapping("inventory")
    public void inventory(VProduct vProduct,HttpServletResponse httpServletResponse){
        int kucuen=vProductService.inventory(vProduct);
        String flag="";
        if(kucuen>0){
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
    @GetMapping("productupdshang")//商品库存上架
    @ResponseBody
    public Object putawayupdshang(VProduct vProduct,HttpServletResponse httpServletResponse){
        String flag="";
        int upd=vProductService.putawayproduct(vProduct);
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
        return JSONArray.toJSONString(flag);
    }
    @GetMapping("productupdxia")
    @ResponseBody
    public void putawayupdxia(VProduct vProduct,HttpServletResponse httpServletResponse){
        String flag="";
        vProduct.setvIsDelete(1);
        int upd=vProductService.putawayproduct(vProduct);
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

    /**
     * 用Solr查询商品
     * @param vProductName
     * @param pages
     * @return
     * @throws Exception
     */
    @GetMapping("SolrName")
    public void SolrName(String vProductName,Pages pages,HttpServletResponse httpServletResponse){
        try{
            Map<String,Object> map= SolrUtil.SolrName(vProductName,pages.getPageNo(),pages.getPageSize());
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
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

    @GetMapping("/tuijian")
    public String getTuiJianProduct(@RequestParam(value = "vSalesVolume",required = false) String vSalesVolume,@RequestParam(value = "vCreateDate",required = false)String vCreateDate,@RequestParam(value = "vCommonsCount",required = false)String vCommonsCount){
        Map<String,Object>map=new HashMap<>();
        List<VOrder>allOrder=vProductService.allOrder();
        List<VProduct>tuiProduct=new ArrayList<>();
        for (int i=0;i<allOrder.size();i++){
            List<VOrderDetails>allDetails=vProductService.allOrderDetails((int)allOrder.get(i).getvOrderId());
            for(int z=0;z<allDetails.size();z++){
                VProduct vProduct=new VProduct();
                vProduct=vProductService.getSelectProductById((int)allDetails.get(z).getvProductId());
                vProductService.updateProductVolume(0,(int)vProduct.getvProductId());
            }
        }

        for (int i=0;i<allOrder.size();i++){
            int vsum=0;
            int vcount=0;
            List<VOrderDetails>allDetails=vProductService.allOrderDetails((int)allOrder.get(i).getvOrderId());
            for (int j=0;j<allDetails.size();j++){
                VProduct vProduct=new VProduct();
                vProduct=vProductService.getSelectProductById((int)allDetails.get(j).getvProductId());
                vsum+=allDetails.get(j).getvQuantity()+vProduct.getvSalesVolume();
                int result=vProductService.updateProductVolume(vsum,(int)vProduct.getvProductId());
            }
        }
        tuiProduct=vProductService.getSelectProductTop(vSalesVolume,vCreateDate,vCommonsCount);
        map.put("tuiProduct",tuiProduct);
        String str= JSON.toJSONString(map);
        return str;
    }

    @GetMapping("/inTop")
    public String inTuiTop(Model model, @RequestParam(value = "tiao",required = false) String tiao){
        int tiaopan=1;
        if(tiao!=null&&tiao!=""){
            tiaopan=Integer.parseInt(tiao);
        }

        Map<String,Object>map=new HashMap<>();
        List<VProduct>topProduct=new ArrayList<>();
        if(tiaopan==1){
            topProduct=vProductService.getSelectProductTop(tiao,null,null);
        }
        if(tiaopan==2){
            topProduct=vProductService.getSelectProductTop(tiao,null,null);
        }
        if(tiaopan==3){
            topProduct=vProductService.getSelectProductTop(null,tiao,null);
        }
        if(tiaopan==4){
            topProduct=vProductService.getSelectProductTop(null,null,tiao);
        }
        model.addAttribute("top",topProduct);
        return JSON.toJSONString(topProduct);
        /**
         * 返回页面
         */
//        return "tuijianTop.html";
    }

    @RequestMapping("/toppai")
    public String toppai(String ti){
        Map<String,Object>map=new HashMap<>();
        int tis=Integer.parseInt(ti);
        List<VProduct>topProduct=new ArrayList<>();
        if(tis==1){
            topProduct=vProductService.getSelectProductTop(ti,null,null);
        }
        if(tis==2){
            topProduct=vProductService.getSelectProductTop(ti,null,null);
        }
        if(tis==3){
            topProduct=vProductService.getSelectProductTop(null,ti,null);
        }
        if(tis==4){
            topProduct=vProductService.getSelectProductTop(null,null,ti);
        }
        map.put("topp",topProduct);
        return JSON.toJSONString(topProduct);
    }
}
