package com.vmall.vtrade.controller;

import com.alibaba.fastjson.JSON;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: 李秸康
 * @Description: 该类用于返回JSON格式数据
 * @Date created in 7:45 2019-05-12
 * @Modifyied By:
 */
@RequestMapping(value="/orderJSON")
@RestController
public class VOrderControllerJSON {

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
     * 返回订单列表信息（JSON）
     * @param no
     * @param size
     * @param usernames
     * @param session
     * @return
     */
    @RequestMapping(value="/toIndex")
    public String toIndex(@RequestParam(value = "no",required = false)String no, @RequestParam(value = "size",required = false)String size,
                          @RequestParam(value = "usernames",required = false)String usernames, HttpSession session){
        Integer curr=1;
        Integer pageSize=1;
        Page page=new Page();
        Integer count=vOrderService.allCount();
        page.setTotalCount(count);
        if(no!=null&&!no.equals("")) {
            curr=Integer.parseInt(no);
            if(curr>page.getTotalPageCount()){
                curr=page.getTotalPageCount();
            }
            if(curr<1){
                curr=1;
            }
        }
        if(size!=null&&!size.equals("")){
            pageSize=Integer.parseInt(size);
        }
        page.setCurrentPageNo(curr);
        page.setPageSize(pageSize);
        List<VOrder> vOrderList=vOrderService.getAllVOrderByUserName(curr,pageSize,usernames);
        page.setDatas(vOrderList);
        return JSONArray.toJSONString(page);
    }


    /**
     * 获取sku信息
     * @return
     */
    @RequestMapping(value="/skus")
    public String skuList(){
        List<VSku> skuList=skuService.findAllSku();
        return JSONArray.toJSONString(skuList);
    }



    /**
     * 根据spmc查询商品id
     * @param spmc 商品名称
     * @return
     */
    @RequestMapping(value = "/properties")
    @ResponseBody
    public Object toPro(@RequestParam("spmc")String spmc,HttpSession session){
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
//            sb=new StringBuffer();
//            sb.append("<label>属性</label><br>");

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
        System.out.println(vProperty.getvPropertiesValue());
        session.setAttribute("valueName",valueName);
        System.out.println(sb.toString());
        return sb.toString();
    }



    /**
     *
     * @param username 购买人
     * @param vUsername 收件人
     * @param productName 商品名称
     * @param vCost 金额
     * @param num 数量
     * @param vUserAddress 收件地址
     * @return
     */
    @RequestMapping(value = "/toAddOrder")
    public String toAddOrders(
            @RequestParam("username")String username,@RequestParam("vUsername")String vUsername,
            @RequestParam("productName")String productName, @RequestParam("vPhone")String vPhone,
            @RequestParam("vCost")String vCost, @RequestParam("num")String num,@RequestParam("wen")String wen,
            @RequestParam("vUserAddress")String vUserAddress
    ){
        VUser vUser=userService.getUserIdByUserName(username);//根据用户名查询用户id
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
                            return JSON.toJSONString("success");
                        }
                    }
                }
            }
        }
        return JSON.toJSONString("failure");
    }

}
