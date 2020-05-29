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

@ApiModel(value="com-youke-entity-Agreement")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "agreement")
public class Agreement implements Serializable {
    /**
     * 协议表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="协议表")
    private Integer id;

    /**
     * 0是用户协议，1是商铺协议
     */
    @TableField(value = "type")
    @ApiModelProperty(value="0是用户协议，1是商铺协议")
    private String type;

    /**
     * 协议内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="协议内容")
    private String content;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="修改时间")
    private String updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_TYPE = "type";

    public static final String COL_CONTENT = "content";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}