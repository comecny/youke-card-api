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

@ApiModel(value="com-youke-entity-Maintain")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "maintain")
public class Maintain implements Serializable {
    /**
     * 用户维权表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="用户维权表")
    private Integer id;

    /**
     * 商铺id
     */
    @TableField(value = "shorps_id")
    @ApiModelProperty(value="商铺id")
    private Integer shorpsId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 原因
     */
    @TableField(value = "reson")
    @ApiModelProperty(value="原因")
    private String reson;

    /**
     * 维权处理状态
     */
    @TableField(value = "maintain_status")
    @ApiModelProperty(value="维权处理状态")
    private Integer maintainStatus;

    /**
     * 维权处理结果
     */
    @TableField(value = "end")
    @ApiModelProperty(value="维权处理结果")
    private String end;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后更新时间")
    private String updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    @TableField(value = "order_id")
    @ApiModelProperty(value="订单id")
    private Integer orderId;

    @TableField(value = "remarks")
    @ApiModelProperty(value="备注")
    private String remarks;

    @TableField(exist = false)
    private String orderNO;

    @TableField(exist = false)
    private String phone;

    @TableField(exist = false)
    private String nickName;

    @TableField(exist = false)
    private String shopsName;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SHORPS_ID = "shorps_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_RESON = "reson";

    public static final String COL_MAINTAIN_STATUS = "maintain_status";

    public static final String COL_END = "end";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}