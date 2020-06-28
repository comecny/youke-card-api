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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-CashOut")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "cash_out")
public class CashOut implements Serializable {
    /**
     * 提现表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="提现表")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 提现金额
     */
    @TableField(value = "price")
    @ApiModelProperty(value="提现金额")
    private BigDecimal price;

    /**
     * 提现状态，0是提现中 1是完成
     */
    @TableField(value = "cash_out_status")
    @ApiModelProperty(value="提现状态，0是提现中 1是完成")
    private Integer cashOutStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后更新时间")
    private Date updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private Object status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_PRICE = "price";

    public static final String COL_CASH_OUT_STATUS = "cash_out_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}