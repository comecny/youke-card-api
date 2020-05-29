package com.youke.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName(value = "backgroud_user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BackgroudUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 后台用户id
    */
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    /**
    * 关联移动端的用户id
    */
    private Integer userId;

    /**
    * 后台用户名
    */
    private String backgroudUserName;

    /**
    * 后台用户密码
    */
    private String bcakgroudUserPassword;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 最后修改时间
    */
    private Date updateTime;

    /**
    * 表状态
    */
    private String status;

    /**
     * 角色id
     */
    private Integer roleId;
}