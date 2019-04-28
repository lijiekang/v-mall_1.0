package com.vmall.vtrade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.vmall.pojo.*;
import com.vmall.vtrade.config.AlipayConfig;
import com.vmall.vtrade.service.orderdetailsservice.VOrderDetailsService;
import com.vmall.vtrade.service.orderservice.VOrderService;
import com.vmall.vtrade.service.orderstatusservice.VOrderStatusService;
import com.vmall.vtrade.service.productservice.VProductService;
import com.vmall.vtrade.service.userservice.UserService;
import com.vmall.vutil.GenerateNumUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

@Controller
@Api
@RequestMapping("/vorder")
public class VOrderController {

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

    /**
     * 根据订单号查询订单
     * @param vSerialNumber 订单号
     */
    @RequestMapping(value = "/vorders/{vSerialNumber}",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    @ApiOperation(value = "根据订单号查询订单",notes = "根据订单号查询")
    @ResponseBody
    public Object VOrderBySerialNumber(@PathVariable String vSerialNumber){
        VOrder vOrder=vOrderService.getVOrderByvSerialNumber(vSerialNumber);
        return JSONArray.toJSONString(vOrder);
    }

    /**
     * 进入订单信息页面
     * @param no 当前页码
     * @param size 页面显示数量
     * @param usernames 购买人
     * @param session session对象
     * @return 订单页面
     */
    @RequestMapping(value = "/toIndex")
    @ApiOperation(value = "根据名字查询订单信息",notes = "查询订单")
    public String toIndex(@RequestParam(value = "no",required = false)String no, @RequestParam(value = "size",required = false)String size,
                          @RequestParam(value = "usernames",required = false)String usernames, HttpSession session){
        Integer curr=1;
        Integer pageSize=1;
        Page page=new Page();
        Integer count=vOrderService.allCount();
        page.setTotalCount(count);
        if(no!=null) {
            curr=Integer.parseInt(no);
            if(curr>page.getTotalPageCount()){
                curr=page.getTotalPageCount();
            }
            if(curr<1){
                curr=1;
            }
        }
        if(size!=null){
            pageSize=Integer.parseInt(size);
        }
        page.setCurrentPageNo(curr);
        page.setPageSize(pageSize);
        List<VOrder> vOrderList=vOrderService.getAllVOrderByUserName(curr,pageSize,usernames);
        session.setAttribute("vOrderList",vOrderList);
        session.setAttribute("page",page);
        return "ordermanage";
    }

    /**
     * 进入新增订单详情页面
     * @return
     */
    @RequestMapping(value = "/toAdd")
    public String toAdd(){
        return "addorder";
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
            @RequestParam("vCost")String vCost, @RequestParam("num")String num,
            @RequestParam("vUserAddress")String vUserAddress
    ){
        VUesr vUesr=userService.getUserIdByUserName(username);//根据用户名查询用户id
        VProduct vProduct=vProductService.getVProductIdByProductName(productName);//根据商品名称查询商品id,商品积分
        VOrder vOrder=new VOrder();
        if(vProduct!=null && vProduct!=null){
                vOrder.setvUserId(vUesr.getvUserId());
                vOrder.setvUsername(vUsername);
                vOrder.setvUserAddress(vUserAddress);
                vOrder.setvPhone(vPhone);
                vOrder.setvCreateTime(new Timestamp(System.currentTimeMillis()));
                vOrder.setvSerialNumber(GenerateNumUtil.generateOrderNumber((int)vUesr.getvUserId(),(int)vProduct.getvProductId()));//生成订单号
                vOrder.setvCost(Double.parseDouble(vCost)*Integer.parseInt(num));
                vOrder.setvProductId(vProduct.getvProductId());//新增订单
                Integer result=vOrderService.addVOrder(vOrder);
                if(result>0){
                    VOrderDetails vOrderDetails=new VOrderDetails();
                    vOrderDetails.setvProductId(vProduct.getvProductId());
                    vOrderDetails.setvQuantity(Long.parseLong(num));
                    vOrderDetails.setvCost(Double.parseDouble(vCost)*Integer.parseInt(num));
                    vOrderDetails.setvOrderId(vOrder.getvOrderId());
                    Integer result2=vOrderDetailsService.addVOrderDetails(vOrderDetails);//新增订单详情
                    if(result2>0) {
                        Integer result3 = vProductService.updateVProductStoreByProductId(vOrder.getvProductId(), vOrderDetails.getvQuantity());//修改商品库存
                        if (result3 > 0) {
                            Integer result4 = userService.updateUserByGrade(vUesr.getvUserId(), vProduct.getvGrade()*Double.parseDouble(num));//修改用户积分
                            if (result4 > 0) {
                                return "redirect:toIndex";
                            }
                        }
                    }
            }
        }
        return "redirect:toAdd";
    }

    /**
     * 根据订单id获取订单详细信息
     * @param vOrderId 订单id
     * @param session session对象
     * @return
     */
    @RequestMapping(value = "/toUpdate")
    public String toupdate(String vOrderId,HttpSession session){
        VOrder vOrder=vOrderService.getVOrderByorderId(Integer.parseInt(vOrderId));
        if(vOrder!=null){
            session.setAttribute("vOrder",vOrder);
            return "updateorder";
        }
        return "redirect:toIndex";
    }

    /**
     * 修改订单
     * @param vOrderId 订单id
     * @param vUsername 购买人
     * @param vPhone 电话号码
     * @param vUserAddress 地址
     * @return
     */
    @RequestMapping(value = "/doUpdateOrder")
    public String doUpdate(String vOrderId,String vUsername,
                           String vPhone,String vUserAddress){
        VOrder vOrder=new VOrder();
        vOrder.setvUsername(vUsername);
        vOrder.setvPhone(vPhone);
        vOrder.setvUserAddress(vUserAddress);
        vOrder.setvOrderId(Integer.parseInt(vOrderId));
        Integer result=vOrderService.updateVOrderByorderId(vOrder);
        if(result>0){
            return "redirect:toIndex";
        }
        return "redirect:toUpdate";
    }

    /**
     * 生成支付页面
     * @param dingdanhao 订单号
     * @param request 对象
     * @return 支付页面
     */
    @RequestMapping(value = "/topay")
    public String dopays(String dingdanhao,HttpServletRequest request){
        VOrder vOrder=vOrderService.getVOrderByorderId(Integer.parseInt(dingdanhao));
        Long productId=vOrder.getvProductId();//获得商品id
        VProduct vProduct=vProductService.getVProductNameByProductId(String.valueOf(productId));//获得商品对象
        String result = null;
        try {
            //获得初始化的AlipayClient
            AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                    alipayConfig.getAppid(),
                    alipayConfig.getAppPrivateKey(),
                    alipayConfig.getFORMAT(),
                    alipayConfig.getCharset(),
                    alipayConfig.getAlipayPublicKey(),
                    alipayConfig.getSign_type());

            //设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(alipayConfig.getReturnUrl());
            alipayRequest.setNotifyUrl(alipayConfig.getNotifyUrl());

            //商户订单号，商户网站订单系统中唯一订单号，必填
            String out_trade_no = vOrder.getvSerialNumber();
            //付款金额，必填
            double total_amount = vOrder.getvCost();
            //订单名称，必填
            String subject = vProduct.getvProductName();
            //商品描述，可空
            String body = "";
            Integer result2 = vOrderStatusService.updateVorderStatus(vOrder.getvOrderId());//修改订单状态
            alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                    + "\"total_amount\":\""+ total_amount +"\","
                    + "\"subject\":\""+ subject +"\","
                    + "\"body\":\""+ body +"\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
            //请求
            result = alipayClient.pageExecute(alipayRequest).getBody();
        } catch (Exception e) {
            e.printStackTrace();
            result="系统错误，请稍后再试";
        }
        request.setAttribute("result",result);
        return "pay";
    }

}
