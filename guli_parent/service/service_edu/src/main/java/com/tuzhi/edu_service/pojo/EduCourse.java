package com.tuzhi.edu_service.pojo;

import com.baomidou.mybatisplus.annotation.*;

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
 * 课程
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-08
 */
@Data
@Accessors(chain = true)
@TableName("edu_course")
@ApiModel(value = "EduCourse对象", description = "课程")
public class EduCourse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("课程讲师ID")
    private String teacherId;

    @ApiModelProperty("二级分类ID")
    private String subjectId;

    @ApiModelProperty("一级ID")
    private String subjectParentId;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    private Integer lessonNum;

    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @ApiModelProperty("销售数量")
    private Long buyCount;

    @ApiModelProperty("浏览数量")
    private Long viewCount;

    @ApiModelProperty("乐观锁")
    private Long version;

    @ApiModelProperty("课程状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    //    逻辑删除注解
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
