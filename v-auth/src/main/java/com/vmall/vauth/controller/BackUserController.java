package com.vmall.vauth.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Page;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.BackUserService;
import com.vmall.vauth.utils.HttpUtils;
import com.vmall.vauth.utils.PoiUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class BackUserController {

    @Autowired
    BackUserService backUserService;

    //根据Id查询用户
    @GetMapping("/user/{id}")
    public void getUserById(int id,HttpServletResponse httpServletResponse) throws IOException {
        ModelAndView modelAndView=new ModelAndView();
        VUser vUser=backUserService.getUserById(id);
        ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
        os.writeObject(JSONArray.toJSON(vUser));
        os.close();
    }

    //删除用户
    @DeleteMapping("/user/{id}")
    public void delUser(int id,HttpServletResponse httpServletResponse) throws IOException {
        HashMap<String,String> map=new HashMap<String, String>();
        int result=backUserService.delUser(id);
        ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
        os.writeObject(JSONArray.toJSON(result));
        os.close();
    }

    //修改用户
    @PostMapping(value = "/userxiu",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public void getUpdate(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile,VUser vUesr,HttpServletResponse httpServletResponse){
        ModelAndView modelAndView=new ModelAndView();
        HashMap<String,String> map=new HashMap<>();
        File file=new File("E:\\img");
        try {
            //生成随机filekey
            String fileKey = UUID.randomUUID().toString();
            //获取后缀
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file,fileKey+suffix));
            if (".png".equals(suffix) || ".jpg".equals(suffix)) {
                //上传文件
                vUesr.setvHeadPath(fileKey + suffix);
            } else {

            }
            int result=backUserService.getUpdate(vUesr);
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(result));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //用户列表
    @GetMapping(value = "/users",produces = {"application/json;charset=utf-8"})
    public void getUserByAll(HttpServletResponse httpServletResponse,@RequestParam(value = "vUsername",required = false) String vUsername,
                             @RequestParam(value = "currentPage",required = false) String currentPage){
        Page page=new Page();
        try {
            if(currentPage==null){
                page.setCurrentPageNo(1);
            }else{
                page.setCurrentPageNo(Integer.valueOf(currentPage));
            }
            int totalCount1=backUserService.getTotalPageCount(vUsername);
            page.setTotalCount(totalCount1);
            List<VUser> userList=backUserService.getAllUser(vUsername,(page.getCurrentPageNo()-1)*3,1000);
            page.setDatas(userList);
            ObjectOutputStream os=new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(page));
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        //String json= JSONArray.toJSONString(page.getvUserList());

    }

    //新增
    @PostMapping(value = "/user",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public void upload(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile, HttpServletRequest request, VUser vUesr,HttpServletResponse httpServletResponse) {
        ModelAndView modelAndView = new ModelAndView();
        //HashMap<String,String> map=new HashMap<String, String>();
        File file=new File("E:\\img");
        if(!file.isDirectory()){
            file.mkdirs();
        }
        //生成随机filekey
        String fileKey = UUID.randomUUID().toString();
        //获取后缀
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."));
        try {
            multipartFile.transferTo(new File(file,fileKey+suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (".png".equals(suffix) || ".jpg".equals(suffix)) {
            //上传文件
            vUesr.setvHeadPath(fileKey + suffix);
        } else {

        }
        int result=backUserService.addUser(vUesr);
        ObjectOutputStream os= null;
        try {
            os = new ObjectOutputStream(httpServletResponse.getOutputStream());
            os.writeObject(JSONArray.toJSON(result));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*@ApiOperation(value = "用户列表",notes = "根据姓名模糊查询",
            protocols = "HTTP", produces = "application/json")
    @GetMapping(value = "/user",produces = {"application/json;charset=utf-8"})
    public ModelAndView getUserByAll(@RequestParam(value = "vUsername",required = false) String vUsername,
                                     @RequestParam(value = "currentPage",required = false) String currentPage){
        ModelAndView modelAndView=new ModelAndView();
        Page page=new Page();
        try {
            if(currentPage==null){
                page.setCurrentPageNo(1);
            }else{
                page.setCurrentPageNo(Integer.valueOf(currentPage));
            }
            int totalCount1=backUserService.getTotalPageCount(vUsername);
            page.setTotalCount(totalCount1);
            List<VUser> userList=backUserService.getAllUser(vUsername,(page.getCurrentPageNo()-1)*3,1000);
            page.setDatas(userList);
        }catch (Exception e){
            e.printStackTrace();
        }
        //String json= JSONArray.toJSONString(page.getvUserList());
        modelAndView.setViewName("usertables");
        modelAndView.addObject("page",page);
        modelAndView.addObject("user",page.getDatas());
        return modelAndView;
    }

    @ApiOperation(value = "根据Id查询用户",notes = "id")
    @GetMapping("/user/{id}")
    public ModelAndView getUserById(int id){
        ModelAndView modelAndView=new ModelAndView();
        VUser vUser=backUserService.getUserById(id);
        modelAndView.addObject("vUser",vUser);
        modelAndView.setViewName("userupdate");
        return modelAndView;
    }*/

/*
    @ApiResponses({@ApiResponse(code = 200,message = "删除成功！"), @ApiResponse(code = 500,message = "删除失败！")})
    @ApiOperation(value = "删除用户",notes = "根据id删除用户")
    @DeleteMapping("/user/{id}")
    public Object delUser(int id){
        HashMap<String,String> map=new HashMap<String, String>();
        int result=backUserService.delUser(id);
        if (result>0){
            map.put("error","删除成功");
        }else {
            map.put("error","删除失败");
        }
        return map;
    }

    @ApiOperation(value = "修改用户")
    @PostMapping(value = "/userxiu",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public ModelAndView getUpdate(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile,VUser vUesr){
        ModelAndView modelAndView=new ModelAndView();
        HashMap<String,String> map=new HashMap<>();
        File file=new File("E:\\img");
        try {
            //生成随机filekey
            String fileKey = UUID.randomUUID().toString();
            //获取后缀
            String fileName = multipartFile.getOriginalFilename();
            String suffix = fileName.substring(fileName.indexOf("."));
            multipartFile.transferTo(new File(file,fileKey+suffix));
            if (".png".equals(suffix) || ".jpg".equals(suffix)) {
                //上传文件
                vUesr.setvHeadPath(fileKey + suffix);
            } else {
                return modelAndView.addObject("error","文件格式错误");
            }
            int result=backUserService.getUpdate(vUesr);
            if (result>0){
                //map.put("error","修改成功");
                modelAndView.setViewName("usertables");
            }else {
                //map.put("error","修改失败");
                modelAndView.setViewName("userupdate");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modelAndView;
    }

    @RequestMapping(value = "/useradd",method = RequestMethod.GET)
    public ModelAndView useradd(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("useradd");
        return modelAndView;
    }

    @ApiOperation(value="添加用户", notes="文件上传")
    @PostMapping(value = "/user",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public ModelAndView upload(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile, HttpServletRequest request, VUser vUesr) {
        ModelAndView modelAndView = new ModelAndView();
        //HashMap<String,String> map=new HashMap<String, String>();
        File file=new File("E:\\img");
        if(!file.isDirectory()){
            file.mkdirs();
        }
        //生成随机filekey
        String fileKey = UUID.randomUUID().toString();
        //获取后缀
        String fileName = multipartFile.getOriginalFilename();
        String suffix = fileName.substring(fileName.indexOf("."));
        try {
            multipartFile.transferTo(new File(file,fileKey+suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (".png".equals(suffix) || ".jpg".equals(suffix)) {
            //上传文件
            vUesr.setvHeadPath(fileKey + suffix);
        } else {
            return modelAndView.addObject("error","文件格式错误");
        }
        int result=backUserService.addUser(vUesr);
        if (result>0){
            //map.put("error","新增成功");
            modelAndView.setViewName("usertables");
        }else {
            //map.put("error","新增失败");
            modelAndView.setViewName("useradd");
        }
        return modelAndView;
    }*/

    @ApiOperation(value="身份证实名", notes="实名")
    @GetMapping("Verified")
    @ResponseBody
    public String Verified(@RequestParam("vUsercode") String vUsercode, @RequestParam("vIdentity") String vIdentity) {
        String host = "https://idenauthen.market.alicloudapi.com";
        String path = "/idenAuthentication";
        String method = "POST";
        String appcode = "81b8fec2b1514aa3a0a5877c1f686b65";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("idNo", vIdentity);
        bodys.put("name", vUsercode);
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value="手机实名", notes="实名")
    @GetMapping("phoneVerified")
    @ResponseBody
    public String phoneVerified(@RequestParam("vUsercode") String vUsercode, @RequestParam("vIdentity") String vIdentity,@RequestParam("vPhone") String vPhone){
        String host = "https://phone3.market.alicloudapi.com";
        String path = "/phonethree";
        String method = "GET";
        String appcode = "81b8fec2b1514aa3a0a5877c1f686b65";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("idcard", vIdentity);
        querys.put("phone", vPhone);
        querys.put("realname", vUsercode);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "wuliu")
    public String wuliu(@RequestParam("nu") String nu, @RequestParam("com") String com){
        String host = "https://allexp.market.alicloudapi.com";
        String path = "/expQuery";
        String method = "GET";
        String appcode = "81b8fec2b1514aa3a0a5877c1f686b65";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("com", com);
        querys.put("nu", nu);

        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/hssf",method = RequestMethod.GET)
    public ResponseEntity<byte[]> vUser(){
        return PoiUtils.exportEmp2Excel(backUserService.getAllVUser());
    }

}