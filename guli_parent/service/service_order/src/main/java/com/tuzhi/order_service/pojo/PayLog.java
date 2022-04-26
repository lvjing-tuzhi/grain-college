package com.tuzhi.order_service.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付日志表
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Data
@Accessors(chain = true)
@TableName("t_pay_log")
@ApiModel(value = "PayLog对象", description = "支付日志表")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("支付完成时间")
    private Date payTime;

    @ApiModelProperty("支付金额（分）")
    private BigDecimal totalFee;

    @ApiModelProperty("交易流水号")
    private String transactionId;

    @ApiModelProperty("交易状态")
    private String tradeState;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty("其他属性")
    private String attr;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    private Date gmtModified;


}
