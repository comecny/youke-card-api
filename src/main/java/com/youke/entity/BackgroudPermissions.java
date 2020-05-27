package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-BackgroudPermissions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "backgroud_permissions")
public class BackgroudPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 后台权限表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="后台权限表")
    private Integer id;

    /**
     * 权限
     */
    @TableField(value = "permission")
    @ApiModelProperty(value="权限")
    private String permission;

    /**
     * 权限描述
     */
    @TableField(value = "describes")
    @ApiModelProperty(value="权限描述")
    private String describes;

    /**
     * 菜单id
     */
    @TableField(value = "menu_id")
    @ApiModelProperty(value="菜单id")
    private Integer menuId;

}