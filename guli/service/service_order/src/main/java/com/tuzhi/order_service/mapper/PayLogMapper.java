package com.tuzhi.order_service.mapper;

import com.tuzhi.order_service.pojo.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
