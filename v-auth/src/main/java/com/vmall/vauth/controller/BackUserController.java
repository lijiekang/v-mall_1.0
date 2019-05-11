package com.vmall.vauth.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.Page;
import com.vmall.pojo.VUser;
import com.vmall.vauth.service.BackUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class BackUserController {

    @Autowired
    BackUserService backUserService;

    @ApiOperation(value = "用户列表",notes = "根据姓名模糊查询",
            protocols = "HTTP", produces = "application/json")
    @GetMapping(value = "/user",produces = {"application/json;charset=utf-8"})
    public Object getUserByAll(@RequestParam(value = "vUserCode",required = false) String vUserCode,@RequestParam(value = "currentPage",required = false) String currentPage){
        Page page=new Page();
        try {
            if(currentPage==null){
                page.setCurrentPageNo(1);
            }else{
                page.setCurrentPageNo(Integer.valueOf(currentPage));
            }
            int totalCount1=backUserService.getTotalPageCount(vUserCode);
            page.setTotalCount(totalCount1);
            List<VUser> userList=backUserService.getAllUser(vUserCode,(page.getCurrentPageNo()-1)*3,3);
            page.setDatas(userList);
        }catch (Exception e){
            e.printStackTrace();
        }
        String json= JSONArray.toJSONString(page.getDatas());
        return  json;
    }



    @ApiOperation(value = "根据Id查询用户",notes = "id")
    @GetMapping("/user/{id}")
    public Object getUserById(int id){
        VUser vUser=backUserService.getUserById(id);
        String json=JSONArray.toJSONString(vUser);
        return json;
    }



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
    @PutMapping(value = "/user/{id}",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public Object getUpdate(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile, HttpServletRequest request,VUser vUesr){
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
                return "文件格式错误";
            }
            int result=backUserService.getUpdate(vUesr);
            if (result>0){
                map.put("error","修改成功");
            }else {
                map.put("error","修改失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @ApiOperation(value="添加用户", notes="文件上传")
    @PostMapping(value = "/user",consumes = "multipart/*",headers = "content-type=multipart/form-date")
    public Object upload(@ApiParam(value = "上传的文件" ,required = true) MultipartFile multipartFile, HttpServletRequest request, VUser vUesr) {
        HashMap<String,String> map=new HashMap<String, String>();
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
            return "文件格式错误";
        }
        int result=backUserService.addUser(vUesr);
        if (result>0){
            map.put("error","新增成功");

        }else {
            map.put("error","新增失败");
        }
        return map;
    }
}
