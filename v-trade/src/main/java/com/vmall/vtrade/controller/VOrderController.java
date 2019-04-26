package com.vmall.vtrade.controller;

import com.alibaba.fastjson.JSONArray;
import com.vmall.pojo.*;
import com.vmall.vtrade.service.orderdetailsservice.VOrderDetailsService;
import com.vmall.vtrade.service.orderservice.VOrderService;
import com.vmall.vtrade.service.orderstatusservice.VOrderStatusService;
import com.vmall.vtrade.service.productservice.VProductService;
import com.vmall.vtrade.service.userservice.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
     * @param vSerialNumber 订单号
     * @param vStatusId 订单状态
     * @param vPayNum 交易号
     * @param productName 商品名称
     * @param vCost 金额
     * @param num 数量
     * @param vCreateTime 创建时间
     * @param vUserAddress 收件地址
     * @return
     */
    @RequestMapping(value = "/toAddOrder")
    public String toAddOrders(
            @RequestParam("username")String username,@RequestParam("vUsername")String vUsername,
            @RequestParam("vSerialNumber")String vSerialNumber,@RequestParam("vStatusId")String vStatusId,
            @RequestParam("vPayNum")String vPayNum,@RequestParam("productName")String productName,
            @RequestParam("vCost")String vCost,@RequestParam("num")String num,
            @RequestParam("vCreateTime")String vCreateTime,@RequestParam("vUserAddress")String vUserAddress
    ){
        VUesr vUesr=userService.getUserIdByUserName(username);//根据用户名查询用户id
        VProduct vProduct=vProductService.getVProductIdByProductName(productName);//根据商品名称查询商品id,商品积分
        VOrder vOrder=new VOrder();
        if(vProduct!=null){
            VOrderDetails vOrderDetails=new VOrderDetails();
            vOrderDetails.setvProductId(vProduct.getvProductId());
            vOrderDetails.setvQuantity(Long.parseLong(num));
            vOrderDetails.setvCost(Double.parseDouble(vCost));
            Integer result=vOrderDetailsService.addVOrderDetails(vOrderDetails);//新增订单详情
            if(result>0){
                vOrder.setvUserId(vUesr.getvUserId());
                vOrder.setvUsername(vUsername);
                vOrder.setvUserAddress(vUserAddress);
                vOrder.setvCreateTime(Timestamp.valueOf(vCreateTime));
                vOrder.setvSerialNumber(vSerialNumber);
                vOrder.setvStatusId(Long.parseLong(vStatusId));
                vOrder.setvCost(Double.parseDouble(vCost));
                vOrder.setvPayNum(vPayNum);
                vOrder.setvOrderDetailId(vOrderDetails.getvOrderDetailId());
                vOrder.setvProductId(vProduct.getvProductId());
                Integer result2=vOrderService.addVOrder(vOrder);
                if(result2>0){
                    Integer result3=vProductService.updateVProductStoreByProductId(vOrder.getvProductId(),vOrderDetails.getvQuantity());
                    if(result3>0){
                        Integer result4=userService.updateUserByGrade(vUesr.getvUserId(),vProduct.getvGrade());
                        if(result4>0){
                            Integer result5=vOrderStatusService.updateVorderStatus(vOrder.getvOrderId());
                            if(result5>0){
                                return "redirect:toIndex";
                            }
                        }
                    }
                }
            }
        }
        return "/toAdd";
    }
}
