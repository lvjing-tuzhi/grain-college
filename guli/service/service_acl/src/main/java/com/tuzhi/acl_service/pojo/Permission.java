package com.tuzhi.acl_service.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author tuzhi
 * @since 2022-04-27
 */
@Data
@Accessors(chain = true)
@TableName("acl_permission")
@ApiModel(value = "Permission对象", description = "权限")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("编号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty("所属上级")
    private String pid;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("类型(1:菜单,2:按钮)")
    private Integer type;

    @ApiModelProperty("权限值")
    private String permissionValue;

    @ApiModelProperty("访问路径")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("状态(0:禁止,1:正常)")
    private Integer status;

    @ApiModelProperty(value = "层级")
    @TableField(exist = false)
    private Integer level;

    @ApiModelProperty(value = "下级")
    @TableField(exist = false)
    private List<Permission> children;

    @ApiModelProperty(value = "是否选中")
    @TableField(exist = false)
    private boolean isSelect;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    private Date gmtCreate;

    @ApiModelProperty("更新时间")
    private Date gmtModified;


}
