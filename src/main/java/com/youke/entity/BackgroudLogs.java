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

@ApiModel(value="com-youke-entity-BackgroudLogs")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "backgroud_logs")
public class BackgroudLogs implements Serializable {
    /**
     * 后台操作日志表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="后台操作日志表")
    private Integer id;

    /**
     * 后台用户id
     */
    @TableField(value = "backgroud_user_id")
    @ApiModelProperty(value="后台用户id")
    private Integer backgroudUserId;

    /**
     * 操作
     */
    @TableField(value = "operation")
    @ApiModelProperty(value="操作")
    private String operation;

    /**
     * 操作人ip
     */
    @TableField(value = "ip")
    @ApiModelProperty(value="操作人ip")
    private String ip;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private Object status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_BACKGROUD_USER_ID = "backgroud_user_id";

    public static final String COL_OPERATION = "operation";

    public static final String COL_IP = "ip";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_STATUS = "status";
}