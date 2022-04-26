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
 * 订单
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-26
 */
@Data
@Accessors(chain = true)
@TableName("t_order")
@ApiModel(value = "Order对象", description = "订单")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("订单号")
    private String orderNo;

    @ApiModelProperty("课程id")
    private String courseId;

    @ApiModelProperty("课程名称")
    private String courseTitle;

    @ApiModelProperty("课程封面")
    private String courseCover;

    @ApiModelProperty("讲师名称")
    private String teacherName;

    @ApiModelProperty("会员id")
    private String memberId;

    @ApiModelProperty("会员昵称")
    private String nickname;

    @ApiModelProperty("会员手机")
    private String mobile;

    @ApiModelProperty("订单金额（分）")
    private BigDecimal totalFee;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    private Integer payType;

    @ApiModelProperty("订单状态（0：未支付 1：已支付）")
    private Integer status;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    private Date gmtModified;


}
