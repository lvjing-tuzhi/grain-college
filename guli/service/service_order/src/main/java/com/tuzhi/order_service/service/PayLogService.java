package com.tuzhi.order_service.service;

import com.tuzhi.order_service.pojo.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
public interface PayLogService extends IService<PayLog> {

    //根据订单号创建微信支付二维码
    Map createWxNativePay(String orderNo);

    //根据订单号查询支付状态
    Map<String, String> queryPayStatus(String orderNo);

    //更新订单表的订单支付状态
    void updateOrdersStatus(Map<String, String> map);
}
