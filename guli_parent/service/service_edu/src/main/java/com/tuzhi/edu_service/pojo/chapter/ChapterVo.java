package com.tuzhi.edu_service.pojo.chapter;

import com.tuzhi.edu_service.pojo.excel.TwoSubject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: guli_parent
 * @description: 章节Vo
 * @author: 兔子
 * @create: 2022-04-08 09:08
 **/

@Data
@ApiModel(value = "章节Vo")
public class ChapterVo {

    @ApiModelProperty("章节id")
    private String id;

    @ApiModelProperty("章节名称")
    private String title;

    @ApiModelProperty("小结")
    private List<VideoVo> children;
}
