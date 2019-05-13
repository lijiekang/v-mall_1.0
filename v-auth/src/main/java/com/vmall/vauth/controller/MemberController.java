package com.vmall.vauth.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.vmall.mapper.collection.CollectionMapper;
import com.vmall.pojo.*;
import com.vmall.pojo.vo.Property;
import com.vmall.vauth.service.dev.*;
import com.vmall.vauth.service.tool.MailService;
import com.vmall.vauth.service.tool.TokenService;
import com.vmall.vutil.SMSCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@Api(tags = "会员中心接口")
public class MemberController {
    @Resource
    DevUserService devUserService;
    @Resource
    TokenService tokenService;
    @Resource
    DevAddressService devAddressService;
    @Resource
    ScoreService scoreService;
    @Resource
    CollectionService collectionService;
    @Resource
    CommonsService commonsService;
    @Resource
    ShoppingService shoppingService;
    @Resource
    MailService mailService;
    @Resource
    AfterSaleService afterSaleService;
    @Resource
    goodCouponService goodCouponService;

    @GetMapping("currentVuser")
    @ApiOperation("用户个人资料数据")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String Vuesr(HttpServletRequest request){
        String token=request.getHeader("token");
        VUser vUesr=devUserService.getVUesrById(token);//空为没有这些状态的订单数量
        return JSONArray.toJSONString(vUesr);
    }
    /*
    * 用户个人资料修改加文件上传
    * */
    @PostMapping("currentVuserUpd")
    @ApiOperation("用户个人资料修改")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String currentVuserUpd(MultipartFile file,VUser vUesr,HttpServletRequest request){
        String token=request.getHeader("token");
        VUser vUesr1=(VUser) tokenService.get(token);
        vUesr.setvUserId(vUesr1.getvUserId());
        String realpath="F:/Vmall/VmallImage/userportrait";
        File folder =new File(realpath);
        if(!folder.isDirectory()){
            folder.mkdirs();
        }
        String name= file.getOriginalFilename();
        try {
            file.transferTo(new File(folder,name));
            if (devUserService.updProfile(vUesr)>0){
                return JSONArray.toJSONString("修改成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONArray.toJSONString("修改失败");
    }
    @GetMapping("question")
    @ApiOperation("安全问题列表")
    public String questionlist(@RequestParam("pageNo")String pageNo){
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        page.setTotalCount(devUserService.count());
        List<VQuestion> list=devUserService.allQuestion(page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("addressList")
    @ApiOperation("收货地址列表")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addressList(HttpServletRequest request,@RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        page.setTotalCount(devAddressService.count(token));
        List<VUserAddress> list=devAddressService.allAddList(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @PostMapping("addAddress")
    @ApiOperation("新增收货地址")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addQuest(@RequestParam("vAddress")String vAddress,HttpServletRequest request){
        String token=request.getHeader("token");
        VUserAddress vUserAddress=new VUserAddress();
        vUserAddress.setvAddress(vAddress);
        vUserAddress.setvDistributionArea("3232");
        vUserAddress.setvIsDefault(0);
        vUserAddress.setvPostCode("000000");
        vUserAddress.setvReciveName("年度");
        vUserAddress.setvRecivePhone("18674900601");
        if(devAddressService.addAddress(vUserAddress,token)>0){
            return JSONArray.toJSONString("新增成功");
        }
        return JSONArray.toJSONString("新增失败");
    }
    @PostMapping("updAddress")
    @ApiOperation("修改收货地址")
    public String updAddress(VUserAddress vUserAddress){
        if(devAddressService.updAddress(vUserAddress)>0){
            return JSONArray.toJSONString("修改成功");
        }
        return JSONArray.toJSONString("修改失败");
    }
    @GetMapping("delAddress")
    @ApiOperation("删除收货地址")
    public String addQuest(@RequestParam("addressId")String addressId){
        if(devAddressService.delAddress(Integer.valueOf(addressId))>0){
            return JSONArray.toJSONString("删除成功");
        }
        return JSONArray.toJSONString("删除成功");
    }
    @GetMapping("addressById")
    @ApiOperation("收货地址数据")
    public String addressById(@RequestParam("addressId")String addressId){
        VUserAddress vUserAddress=devAddressService.getAddressById(Integer.valueOf(addressId));
        return JSONArray.toJSONString(vUserAddress);
    }
    @GetMapping("addQuest")
    @ApiOperation("安全问题设置")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addQuest(@RequestParam("questId")String questId,@RequestParam("answer")String answer,HttpServletRequest request){
        String token=request.getHeader("token");
        devUserService.addQuestion(Integer.valueOf(questId),token,answer);
        return JSONArray.toJSONString("设置成功");
    }
    /*
     * 置换token请求
     * */
    @GetMapping("tokenDisplace")
    @ApiOperation("token")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public void tokenDisplace(HttpServletRequest request, HttpServletResponse response){
        String token=request.getHeader("token");
        VUser vUesr=(VUser)tokenService.get(token);
        if(vUesr!=null) {
            tokenService.set(vUesr);
            Cookie cookie = new Cookie("token", token.toString());
            cookie.setMaxAge(15 * 60);
            response.addCookie(cookie);
            tokenService.del(token);
        }
    }
    @GetMapping("updPwd")
    @ApiOperation("修改密码")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String updPwd(@RequestParam("password")String password,HttpServletRequest request){
        String token=request.getHeader("token");
        if(devUserService.updPwd(token,password)){
            return JSONArray.toJSONString("修改成功");
        }
        return JSONArray.toJSONString("修改失败");
    }
    @GetMapping("addhistory")
    @ApiOperation("添加足迹")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addhistory(@RequestParam("productId")String productId,HttpServletRequest request){
        String token=request.getHeader("token");
        VBrowsinghistory history=new VBrowsinghistory();
        history.setvProductId(Integer.valueOf(productId));
        history.setvBrowsTime(new Timestamp(System.currentTimeMillis()));
        devUserService.addFootHistory(token,history);
        return JSONArray.toJSONString("添加成功");
    }
    @GetMapping("myhistory")
    @ApiOperation("我的足迹")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String myhistory(HttpServletRequest request,@RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        page.setTotalCount(devUserService.historycount(token));
        List<VBrowsinghistory> vBrowsinghistory=devUserService.myFootHistory(token,page);
        map.put("list",vBrowsinghistory);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("scoreList")
    @ApiOperation("我的积分记录")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String scoreList(HttpServletRequest request,@RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setTotalCount(scoreService.count(token));
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        List<VScore> list=scoreService.scoreList(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("addScore")
    @ApiOperation("积分记录添加")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addScore(@RequestParam("cScore")String cScore,@RequestParam("details")String details,HttpServletRequest request){
        String token=request.getHeader("token");
        VScore vScore=new VScore();
        vScore.setV_change_score(cScore);
        if(cScore.substring(0,1).equals("+")){
            vScore.setV_type(0);
        }else{
            vScore.setV_type(1);
        }
        vScore.setV_createDate(new Timestamp(System.currentTimeMillis()));
        vScore.setV_details(details);
        boolean flag=scoreService.addScore(vScore,token);
        return JSONArray.toJSONString(flag);
    }
    @GetMapping("colList")
    @ApiOperation("我的收藏")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String colList(HttpServletRequest request,@RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setTotalCount(collectionService.count(token));
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        List<VCollection> list=collectionService.colLsit(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("delCol")
    @ApiOperation("删除收藏记录")
    public String delCol(@RequestParam("collectionId")String collectionId){
        return JSONArray.toJSONString(collectionService.delCol(Integer.valueOf(collectionId)));
    }
    @GetMapping("comList")
    @ApiOperation("我的评价")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String comList(HttpServletRequest request,@RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setTotalCount(commonsService.count(token));
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        List<VCommons> list=commonsService.comList(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @PostMapping("addshoppingcart")
    @ApiOperation("添加购物车")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String addshoppingcart(HttpServletRequest request, @RequestBody Property[] properties, @RequestParam("num")String num){
        String token=request.getHeader("token");
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<properties.length;i++){
            Property property=properties[i];
            sb.append(property.getPropertiesId()+":"+property.getPropertyId());
            if(i!=properties.length-1)
                sb.append("-");
        }
        boolean flag=shoppingService.addShoppingCart(token,sb.toString(),Integer.valueOf(num));
        return JSONArray.toJSONString(flag);
    }
    @GetMapping("myshoppingcart")
    @ApiOperation("我的购物车")
    @ApiImplicitParam(value="token",name = "token",paramType = "header")
    public String myshoppingcart(HttpServletRequest request,@RequestParam(value = "pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setTotalCount(shoppingService.count(token));
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        List<VShoppingcart> list=shoppingService.shoppingcartList(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("delshoppingcart")
    @ApiOperation("删除购物车记录")
    public String delshoppingcart(@RequestParam("cartId")String cartId,
                                  @RequestParam("cartproductId")String cartproductId){
        boolean flag=shoppingService.delShoppingCart(Integer.valueOf(cartId),Integer.valueOf(cartproductId));
        return JSONArray.toJSONString(flag);
    }
    @GetMapping("updPhoneNumber")
    @ApiOperation("手机号换绑")
    @ApiImplicitParam(value = "token",name = "token",paramType = "header")
    public String updPhoneNumber(@RequestParam("phoneNumber")String phoneNumber,
                                 @RequestParam("phoneCode")String phoneCode,
                                 HttpServletRequest request){
        String token=request.getHeader("token");
        if(phoneNumber!=""&&phoneCode!=""){
            return JSONArray.toJSONString(devUserService.updPhoneNumber(token,phoneNumber,phoneCode));
        }
        return JSONArray.toJSONString("绑定失败");
    }
    @GetMapping("/PhoneCode")
    @ApiOperation("手机验证码")
    public String PhoneCode(@RequestParam("phone")String phone){
        Integer code1= new Random().nextInt(1000000);
        try {
            SMSCode.sendCode(phone,code1.toString());
            tokenService.setCode(phone,code1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONArray.toJSONString("验证码已发送");
    }
    @GetMapping("updEmail")
    @ApiOperation("邮箱换绑")
    @ApiImplicitParam(value = "token",name = "token",paramType = "header")
    public String updEmail(HttpServletRequest request,
                           @RequestParam("email")String email,
                           @RequestParam("emailCode")String emailCode){
        String token=request.getHeader("token");
        if(email!=""&&emailCode!=""){
            return JSONArray.toJSONString(devUserService.updEmail(token,email,emailCode));
        }
        return JSONArray.toJSONString("绑定失败");
    }
    @GetMapping("/getCode")//发送邮箱验证码
    @ApiOperation("邮箱验证码")
    public String getCode(@RequestParam("email")String email){
        Integer code1= new Random().nextInt(1000000);
        mailService.sendMail(email,"验证码","您收到的验证码为:",code1);
        return "验证码已发送";
    }
    @GetMapping("afterSaleList")
    @ApiOperation("售后列表")
    @ApiImplicitParam(value = "token",name = "token",paramType = "header")
    public String afterSaleList(HttpServletRequest request,
                                @RequestParam("pageNo")String pageNo){
        String token=request.getHeader("token");
        Map<String,Object> map=new HashMap<>();
        Page page=new Page();
        page.setTotalCount(afterSaleService.count(token));
        page.setCurrentPageNo(Integer.valueOf(pageNo));
        List<VAftersale> list=afterSaleService.aftersaleList(token,page);
        map.put("list",list);
        map.put("page",page);
        return JSONArray.toJSONString(map);
    }
    @GetMapping("afterSale")
    @ApiOperation("售后详情")
    @ApiImplicitParam(value = "token",name = "token",paramType = "header")
    public String afterSale(HttpServletRequest request,@RequestParam("aftersaleId")String aftersaleId){
        String token=request.getHeader("token");
        VAftersale vAftersale=afterSaleService.getAfterSale(token,Integer.valueOf(aftersaleId));
        return JSONArray.toJSONString(vAftersale);
    }
    @GetMapping("goodCoupon")
    @ApiOperation("精选好券")
    public String afterSale(){
        List<VConpon> conpons=goodCouponService.goodCoupon();
        return JSONArray.toJSONString(conpons);
    }
    @GetMapping("ReceiveCoupon")
    @ApiOperation("领取优惠券")
    @ApiImplicitParam(value = "token",name = "token",paramType = "header")
    public String ReceiveCoupon(HttpServletRequest request,@RequestParam("couponId")String couponId,@RequestParam(value = "num",defaultValue = "1")String num){
        String flag="";
        String token=request.getHeader("token");
        if(goodCouponService.ReceiveCoupon(token,Integer.valueOf(couponId),Integer.valueOf(num))>0){
            return "true";
        }
        return "false";
    }
}
