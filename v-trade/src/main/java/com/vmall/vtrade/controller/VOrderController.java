package com.vmall.vtrade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.vmall.pojo.*;
import com.vmall.vtrade.config.AlipayConfig;
import com.vmall.vtrade.config.UnionPayClient;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
    @Autowired
    SkuService skuService;
    @Autowired
    VPropertiesSkuService vPropertiesSkuService;
    @Autowired
    VPropertiesService vPropertiesService;
    @Autowired
    VPropertyService vPropertyService;

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
        Integer pageSize=10;
        Page page=new Page();
        Integer count=vOrderService.allCount();
        page.setTotalCount(count);
        if(no!=null) {
            curr=Integer.parseInt(no);
            if(curr>=page.getTotalPageCount()){
                curr=page.getTotalPageCount();
            }
            if(curr<=1){
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
    public String toAdd(HttpSession session){
        List<VSku> skuList=skuService.findAllSku();
        session.setAttribute("skuList",skuList);
        return "addorder";
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
     * 删除订单
     * @param id 订单id
     * @return
     */
    @RequestMapping(value = "/delOrder")
    @ResponseBody
    public Object todelOrder(@RequestParam("id")String id){
        Map<String,String> map=new HashMap<String, String>();
        Integer result=vOrderService.deleteOrder(Integer.parseInt(id));//删除订单
        if(result>0){
            map.put("status","success");
        }else {
            map.put("status","fail");
        }
        return JSONArray.toJSONString(map);
    }

    /**
     * 生成支付页面
     * @param dingdanhao 订单号
     * @param request 对象
     * @return 支付页面
     */
    @RequestMapping(value = "/topay")
    public String dopays(String dingdanhao,HttpServletRequest request){
        VOrder vOrder=vOrderService.getVOrderByorderId(Integer.parseInt(dingdanhao));//根据订单号获得订单对象
        VSku vSku=skuService.findProductIdBySkuId((int)vOrder.getvSkuId());//获得sku对象
        VProduct vProduct=vProductService.getVProductNameByProductId(String.valueOf(vSku.getVProductId()));//获得商品名称
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

    /**
     *
     * @param request 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表
     * @param out_trade_no 商户订单号
     * @param trade_no 交易号
     * @return
     */
    @RequestMapping(value = "/complete")
    public String upStu(HttpServletRequest request,String out_trade_no,String trade_no){
        System.out.println(trade_no);
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = false;
        try {
            verify_result = AlipaySignature.rsaCheckV1(params, alipayConfig.getZhifuPublicKey(), alipayConfig.getCharset(), "RSA2");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        if(verify_result){//验证成功
            //请在这里加上商户的业务逻辑程序代码
            VOrder vOrder=new VOrder();
            vOrder=vOrderService.getVOrderByvSerialNumber(out_trade_no);
            vOrder.setvPayNum(trade_no);
            Integer result = vOrderStatusService.updateVorderStatus(vOrder.getvOrderId(),vOrder.getvPayNum());//修改订单状态
            if(result>0){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        }else{//验证失败
            System.out.println("验证失败");
        }
        return "redirect:toIndex";
    }

    /**
     * 支付宝退款
     * @param out_trade_no 订单号
     * @return
     */
    @RequestMapping(value = "/tui")
    public String tuikuan(String out_trade_no){
        VOrder vOrder=vOrderService.getVOrderByvSerialNumber(out_trade_no);
        String returnStr = null;
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(),
                alipayConfig.getAppid(),
                alipayConfig.getAppPrivateKey(),
                alipayConfig.getFORMAT(),
                alipayConfig.getCharset(),
                alipayConfig.getZhifuPublicKey(),
                alipayConfig.getSign_type());
        AlipayTradeRefundRequest request=new AlipayTradeRefundRequest();
        request.setBizContent("{" +
                "\"out_trade_no\":\""+out_trade_no+"\","+
                "\"trade_no\":\"" + vOrder.getvPayNum() + "\"," +
                "\"refund_amount\":\"" + vOrder.getvCost() + "\"," +
                "\"refund_reason\":\"正常退款\"" +
                " }");
        AlipayTradeRefundResponse response;
        try {
            response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("支付宝退款成功");
            }else{
                returnStr = response.getSubMsg();//失败会返回错误信息
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "redirect:toIndex";
    }

    /**
     * 进入银联支付帮助页面
     * @return
     */
    @RequestMapping(value = "/toUnionHelp")
    public String tounionIndex(){
        return "index";
    }

    /**
     * 银联支付
     * @param orderNumber 商户订单号
     * @param txnAmt 金额（分）
     * @return
     * @throws Exception
     */
    @RequestMapping("/unionpay")
    @ResponseBody
    public String pay(@RequestParam String orderNumber,
                      @RequestParam Integer txnAmt,
                      @RequestParam String goodsName) throws Exception{

        String payhtml = UnionPayClient.pay(orderNumber, txnAmt.toString(),goodsName);
        //生成自动跳转的form表单，直接返给前端，让前端做页面的跳转
        return payhtml;
    }

    /**
     * 订单状态查询
     * @param orderNumber
     * @param txnTime
     * @return
     * @throws Exception
     */
//    @GetMapping("/orderquery")
//    public ResultBO<Object> query(@RequestParam String orderNumber,
//                                  @RequestParam String txnTime)throws Exception{
//
//        //参数限制逻辑
//
//        Map<String, String> rspData = UnionPayClient.query(orderNumber, txnTime);
//
//        //返回参数处理
//        if(!rspData.isEmpty()){
//            //验证签名
//            if(AcpService.validate(rspData, DemoBase.encoding)){
//                LogUtil.writeLog("验证签名成功");
//                if("00".equals(rspData.get("respCode"))){//如果查询交易成功
//                    //处理被查询交易的应答码逻辑
//                    String origRespCode = rspData.get("origRespCode");
//                    if("00".equals(origRespCode)){
//                        System.out.println("交易成功了！！！！！！！！");
//
//                        //交易成功，更新商户订单状态
//                        //数据库修改成功后告诉前端，用户支付成功
//                        return ResultTool.success();
//
//                    }else if("03".equals(origRespCode) ||
//                            "04".equals(origRespCode) ||
//                            "05".equals(origRespCode)){
//                        //需再次发起交易状态查询交易
//                    }else{
//                        //其他应答码为失败请排查原因
//                    }
//                }else{
//                    //查询交易本身失败，或者未查到原交易，检查查询交易报文要素
//                }
//            }else{
//                LogUtil.writeErrorLog("验证签名失败");
//                //检查验证签名失败的原因
//            }
//        }else{
//            //未返回正确的http状态
//            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
//        }
//        return ResultTool.success();
//    }

//    @PostMapping("/orderrefund")
//    public ResultBO<Object> refund(@RequestParam String refundOrderId,
//                                   @RequestParam String txnAmt,
//                                   @RequestParam String queryId){
//
//        Map<String, String> rspData = UnionPayClient.refund(refundOrderId, txnAmt, queryId);
//
//        /**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
//        //应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
//        if(!rspData.isEmpty()){
//            if(AcpService.validate(rspData, DemoBase.encoding)){
//                LogUtil.writeLog("验证签名成功");
//                String respCode = rspData.get("respCode");
//                if("00".equals(respCode)){
//
//                    //交易已受理，等待接收后台通知更新订单状态,也可以主动发起 查询交易确定交易状态。
//                    return ResultTool.success();
//
//                }else if("03".equals(respCode)||
//                        "04".equals(respCode)||
//                        "05".equals(respCode)){
//                    //后续需发起交易状态查询交易确定交易状态
//                }else{
//                    //其他应答码为失败请排查原因
//                }
//            }else{
//                LogUtil.writeErrorLog("验证签名失败");
//            }
//        }else{
//            //未返回正确的http状态
//            LogUtil.writeErrorLog("未获取到返回报文或返回http状态码非200");
//        }
//
//        return ResultTool.success();
//    }

}
