package com.tuzhi.edu_service.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli_parent
 * @description: 教师查询条件
 * @author: 兔子
 * @create: 2022-03-08 20:27
 **/

@Data
@ApiModel("教师查询条件")
public class TeacherQuery {
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty(value = "等级", example = "1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "开始时间", example = "2019-10-30 11:53:03")
    private String begin;
    @ApiModelProperty(value = "结束时间", example = "2019-10-30 11:53:03")
    private String end;
}
