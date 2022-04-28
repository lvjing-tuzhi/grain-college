package com.tuzhi.order_service.service.impl;

import com.tuzhi.order_service.pojo.Order;
import com.tuzhi.order_service.mapper.OrderMapper;
import com.tuzhi.order_service.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Service
public class OrderServiceImp extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
