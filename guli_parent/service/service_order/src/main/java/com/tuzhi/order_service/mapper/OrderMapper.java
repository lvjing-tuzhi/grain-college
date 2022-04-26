package com.tuzhi.order_service.mapper;

import com.tuzhi.order_service.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

}
