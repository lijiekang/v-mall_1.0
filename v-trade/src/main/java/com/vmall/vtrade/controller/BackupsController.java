package com.vmall.vtrade.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.*;
import com.vmall.vtrade.config.AlipayConfig;
import com.vmall.vtrade.service.orderdetailsservice.VOrderDetailsService;
import com.vmall.vtrade.service.orderservice.VOrderService;
import com.vmall.vtrade.service.orderstatusservice.VOrderStatusService;
import com.vmall.vtrade.service.productservice.VProductService;
import com.vmall.vtrade.service.propertiesservice.VPropertiesService;
import com.vmall.vtrade.service.propertiesskuservice.VPropertiesSkuService;
import com.vmall.vtrade.service.propertyservice.VPropertyService;
import com.vmall.vtrade.service.skuservice.SkuService;
import com.vmall.vtrade.service.userservice.UserService;
import com.vmall.vutil.GenerateNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/backups")
public class BackupsController {
    @Autowired
    VOrderService vOrderService;
    @Autowired
    VOrderDetailsService vOrderDetailsService;
    @Autowired
    VOrderStatusService vOrderStatusService;
    @Autowired
    UserService userService;
    @Autowired
    VProductService vProductService;
    @Autowired
    AlipayConfig alipayConfig;
    @Autowired
    SkuService skuService;
    @Autowired
    VPropertiesSkuService vPropertiesSkuService;
    @Autowired
    VPropertiesService vPropertiesService;
    @Autowired
    VPropertyService vPropertyService;


    /**
     * 返回订单集合
     * @param no 当前页码
     * @param size 页面数量大小
     * @param usernames 购买人
     * @param response response对象
     */

    @RequestMapping(value = "/toIndex",produces = "application/json;charset=utf-8")
    public void toIndex(@RequestParam(value = "no",required = false)String no, @RequestParam(value = "size",required = false)String size,
                        @RequestParam(value = "usernames",required = false)String usernames, HttpServletResponse response) {
        Integer curr = 1;
        Integer pageSize = 10;
        Page page = new Page();
        Integer count = vOrderService.allCount();
        page.setTotalCount(count);
        if (no != null) {
            curr = Integer.parseInt(no);
            if (curr >= page.getTotalPageCount()) {
                curr = page.getTotalPageCount();
            }
            if (curr <= 1) {
                curr = 1;
            }
        }
        if (size != null) {
            pageSize = Integer.parseInt(size);
        }
        page.setCurrentPageNo(curr);
        page.setPageSize(pageSize);
        List<VOrder> vOrderList = vOrderService.getAllVOrderByUserName(curr, pageSize, usernames);
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(vOrderList));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 进入新增首页
     * 查询所有规格
     * @param response response对象
     */
    @RequestMapping(value = "/toAdd")
    public void toAdd(HttpServletResponse response){
        List<VSku> skuList=skuService.findAllSku();
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(skuList));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据商品名称查询规格
     * @param spmc 商品名称
     * @param response response对象
     */
    @RequestMapping(value = "/properties")
    public void toPro(@RequestParam("spmc")String spmc,HttpServletResponse response){
        VProduct vProduct=vProductService.getVProductIdByProductName(spmc);
        StringBuffer sb=new StringBuffer();
        VProperties vProperties=new VProperties();
        VProperty vProperty=new VProperty();
        sb.append("<label>属性</label><br>");
        String valueName="";
        List<Integer> skuidList = skuService.findskuId((int)vProduct.getvProductId());//根据商品id查询skuid
        for(int i=0;i<skuidList.size();i++){
            Integer skuid=skuidList.get(i);//获取skuid
            List<Integer> guiIdList=vPropertiesSkuService.vPropertList(skuid);//根据skuid查询propertiesid
            for(int j=0;j<guiIdList.size();j++){
                Integer skuids=guiIdList.get(j);
                if(j==0)
                    valueName="name1";
                else if(j==1)
                    valueName="name2";
                else
                    valueName="name3";
                List<VProperties> vPropertiesList=vPropertiesService.findAllVProperties(skuids);//根据skuid查询属性
                for(int k=0;k<vPropertiesList.size();k++){
                    vProperties=vPropertiesList.get(k);
                    sb.append("<span  value='"+vProperties.getvPropertiesId()+"'/>" +vProperties.getvPropertiesName()+":</span>");
                    List<VProperty> vPropertyList=vPropertyService.findAllVProperty((int)vProperties.getvPropertiesId());//根据属性id获得属性值集合
                    for(int l=0;l<vPropertyList.size();l++){
                        vProperty=vPropertyList.get(l);
                        sb.append("<input onchange='dj($(this))' id='dan' type='radio' value='"+vProperty.getvPropertiesId()+"' name='"+valueName+"'/>"
                                +"<span>"+vProperty.getvPropertiesValue()+"</span>");

                    }
                    sb.append("<br>");
                }
            }
        }
        System.out.println(sb.toString());
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(sb.toString());
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回受影响行数
     * @param username 购买人
     * @param vUsername 收件人
     * @param productName 商品名称
     * @param vCost 金额
     * @param num 数量
     * @param vUserAddress 收件地址
     * @return
     */
    @PostMapping(value = "/toAddOrder")
    public void toAddOrders(
            @RequestParam("username")String username,@RequestParam("vUsername")String vUsername,
            @RequestParam("productName")String productName, @RequestParam("vPhone")String vPhone,
            @RequestParam("vCost")String vCost, @RequestParam("num")String num,@RequestParam("wen")String wen,
            @RequestParam("vUserAddress")String vUserAddress,HttpServletResponse response
    ){
        VUser vUser=userService.getUserIdByUserName(username);//根据用户名查询用户id
        String str="";
        VProduct vProduct=vProductService.getVProductIdByProductName(productName);//根据商品名称查询商品id,商品积分
        VSku vSku=skuService.findskuIdBySkuName(wen);//根据skuname查询SKU对象
        VOrder vOrder=new VOrder();
        if(vProduct!=null && vProduct!=null){
            vOrder.setvUserId(vUser.getvUserId());
            vOrder.setvUsername(vUsername);
            vOrder.setvUserAddress(vUserAddress);
            vOrder.setvPhone(vPhone);
            vOrder.setvCreateTime(new Timestamp(System.currentTimeMillis()));
            vOrder.setvSerialNumber(GenerateNumUtil.generateOrderNumber((int)vUser.getvUserId(),(int)vProduct.getvProductId()));//生成订单号
            vOrder.setvCost(Double.parseDouble(vCost)*Integer.parseInt(num));
            vOrder.setvSkuId(vSku.getVSkuId());
            Integer result=vOrderService.addVOrder(vOrder);
            if(result>0){
                VOrderDetails vOrderDetails=new VOrderDetails();
                vOrderDetails.setvProductId(vProduct.getvProductId());
                vOrderDetails.setvQuantity(Long.parseLong(num));
                vOrderDetails.setvCost(Double.parseDouble(vCost)*Integer.parseInt(num));
                vOrderDetails.setvOrderId(vOrder.getvOrderId());
                Integer result2=vOrderDetailsService.addVOrderDetails(vOrderDetails);//新增订单详情
                if(result2>0) {
                    Integer result3 = vProductService.updateVProductStoreByProductId(vProduct.getvProductId(), vOrderDetails.getvQuantity());//修改商品库存
                    if (result3 > 0) {
                        Integer result4 = userService.updateUserByGrade(vUser.getvUserId(), vProduct.getvGrade()*Double.parseDouble(num));//修改用户积分
                        if (result4 > 0) {
                            System.out.println("成功");
                            str="success";
                        }else{
                            System.out.println("失败");
                            str="fail";
                        }
                    }
                }
            }
        }
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(str));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据订单id获取订单详细信息
     * @param vOrderId 订单id
     * @return
     */
    @RequestMapping(value = "/toUpdate")
    public void toupdate(String vOrderId,HttpServletResponse response){
        VOrder vOrder=vOrderService.getVOrderByorderId(Integer.parseInt(vOrderId));
        if(vOrder!=null){
            try {
                ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
                os.writeObject(JSONArray.toJSON(vOrder));
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 修改订单
     * @param vOrderId 订单id
     * @param vUsername 购买人
     * @param vPhone 电话号码
     * @param vUserAddress 地址
     * @return
     */
    @PutMapping(value = "/doUpdateOrder")
    public void doUpdate(String vOrderId,String vUsername,
                           String vPhone,String vUserAddress,HttpServletResponse response){
        VOrder vOrder=new VOrder();
        vOrder.setvUsername(vUsername);
        vOrder.setvPhone(vPhone);
        vOrder.setvUserAddress(vUserAddress);
        vOrder.setvOrderId(Integer.parseInt(vOrderId));
        Integer result=vOrderService.updateVOrderByorderId(vOrder);
        String str="";
        if(result>0){
            System.out.println("成功");
            str="success";
        }else{
            System.out.println("失败");
            str="fail";
        }
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(str));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除订单
     * @param id 订单id
     * @return
     */
    @PostMapping(value = "/delOrder")
    public void todelOrder(@RequestParam("id")String id,HttpServletResponse response){
        Map<String,String> map=new HashMap<String, String>();
        Integer result=vOrderService.deleteOrder(Integer.parseInt(id));//删除订单
        if(result>0){
            map.put("status","success");
        }else {
            map.put("status","fail");
        }
        try {
            ObjectOutputStream os=new ObjectOutputStream(response.getOutputStream());
            os.writeObject(JSONArray.toJSON(map));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
