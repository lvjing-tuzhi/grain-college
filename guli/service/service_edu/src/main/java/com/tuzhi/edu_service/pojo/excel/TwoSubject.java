package com.tuzhi.edu_service.pojo.excel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: guli_parent
 * @description: 一级分类
 * @author: 兔子
 * @create: 2022-04-08 09:08
 **/

@Data
@ApiModel(value = "树形分类二级实体类", description = "")
public class TwoSubject {

    @ApiModelProperty("一级分类id")
    private String id;

    @ApiModelProperty("二级分类名称")
    private String title;
}
