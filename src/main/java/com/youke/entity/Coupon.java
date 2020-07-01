package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-Coupon")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "coupon")
public class Coupon implements Serializable {
    /**
     * 优惠卷
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="优惠卷")
    private Integer id;

    /**
     * 开始时间
     */
    @TableField(value = "start_time")
    @ApiModelProperty(value="开始时间")
    private String startTime;

    /**
     * 结束时间
     */
    @TableField(value = "end_time")
    @ApiModelProperty(value="结束时间")
    private String endTime;

    /**
     * 数量
     */
    @TableField(value = "number")
    @ApiModelProperty(value="数量")
    private Integer number;

    @TableField(value = "coupon_describe")
    @ApiModelProperty(value="描述")
    private String couponDescribe;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 开始金额区间
     */
    @TableField(value = "use_start")
    @ApiModelProperty(value="开始金额区间")
    private BigDecimal useStart;

    /**
     * 结束金额区间
     */
    @TableField(value = "use_end")
    @ApiModelProperty(value="结束金额区间")
    private BigDecimal useEnd;

    /**
     * 删除标志
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "删除状态")
    private String status;

    @TableField(value = "update_time")
    @ApiModelProperty("更新时间")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_START_TIME = "start_time";

    public static final String COL_END_TIME = "end_time";

    public static final String COL_NUMBER = "number";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_USE_START = "use_start";

    public static final String COL_USE_END = "use_end";
}