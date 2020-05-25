package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-BackgroudUserRole")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "backgroud_user_role")
public class BackgroudUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 后台用户角色表id
    */
    @TableId(value = "id",type = IdType.AUTO)
    @ApiModelProperty(value="后台用户角色表id")
    private Integer id;

    /**
    * 角色
    */
    @ApiModelProperty(value="角色")
    private String role;

    /**
    * 角色描述
    */
    @ApiModelProperty(value="角色描述")
    @TableField(value = "describes",exist=true)
    private String describes;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 最后修改时间
    */
    @ApiModelProperty(value="最后修改时间")
    private Date updateTime;

    /**
    * 表状态
    */
    @ApiModelProperty(value="表状态")
    private String status;


}