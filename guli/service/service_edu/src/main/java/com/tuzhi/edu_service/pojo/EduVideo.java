package com.tuzhi.edu_service.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
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
 * 课程视频
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-08
 */
@Data
@Accessors(chain = true)
@TableName("edu_video")
@ApiModel(value = "EduVideo对象", description = "课程视频")
public class EduVideo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("视频ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("课程ID")
    private String courseId;

    @ApiModelProperty("章节ID")
    private String chapterId;

    @ApiModelProperty("节点名称")
    private String title;

    @ApiModelProperty("云端视频资源")
    private String videoSourceId;

    @ApiModelProperty("原始文件名称")
    private String videoOriginalName;

    @ApiModelProperty("排序字段")
    private Integer sort;

    @ApiModelProperty("播放次数")
    private Long playCount;

    @ApiModelProperty("是否可以试听：0收费 1免费")
    private Boolean isFree;

    @ApiModelProperty("视频时长（秒）")
    private Float duration;

    @ApiModelProperty("Empty未上传 Transcoding转码中  Normal正常")
    private String status;

    @ApiModelProperty("视频源文件大小（字节）")
    private Long size;

    @ApiModelProperty("乐观锁")
    @Version
    private Long version;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
