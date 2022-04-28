package com.tuzhi.edu_service.pojo.chapter;

import com.tuzhi.edu_service.pojo.excel.TwoSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: guli_parent
 * @description: 小结Vo
 * @author: 兔子
 * @create: 2022-04-08 09:08
 **/

@Data
@ApiModel(value = "小结Vo")
public class VideoVo {

    @ApiModelProperty("小结id")
    private String id;

    @ApiModelProperty("小结名称")
    private String title;
}
