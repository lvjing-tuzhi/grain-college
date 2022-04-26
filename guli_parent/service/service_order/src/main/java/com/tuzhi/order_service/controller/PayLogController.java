package com.tuzhi.order_service.controller;

import com.tuzhi.order_service.service.PayLogService;
import com.tuzhi.utilcommon.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Api(tags = "支付接口")
@RestController
@RequestMapping("/orderservice/payLog")
@CrossOrigin
public class PayLogController {

    @Autowired
    PayLogService payLogService;

    @ApiOperation("根据订单号创建微信支付二维码")
    @GetMapping("/createNative/{orderNo}")
    public Result createWxNativePay(@PathVariable String orderNo){
        //返回的信息包括二维码地址，还需要其他的信息
        Map map = payLogService.createWxNativePay(orderNo);
        return Result.ok().data(map);
    }

    //根据订单号查询订单状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return Result.error().message("支付出错了");
        }
        //如果返回map里面不为空，通过map获取订单状态
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.ok().message("支付成功");
        }
        return Result.ok().code(25000).message("支付中");

    }

}
