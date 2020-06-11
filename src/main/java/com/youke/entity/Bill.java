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

@ApiModel(value="com-youke-entity-Bill")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "bill")
public class Bill implements Serializable {
    /**
     * 流水表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="流水表")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 0是进账 1是出账
     */
    @TableField(value = "bill_type")
    @ApiModelProperty(value="0是进账 1是出账")
    private Integer billType;

    /**
     * 支付类型
     */
    @TableField(value = "pay_type")
    @ApiModelProperty(value="支付类型")
    private String payType;

    /**
     * 金额
     */
    @TableField(value = "money")
    @ApiModelProperty(value="金额")
    private BigDecimal money;

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

    public static final String COL_BILL_TYPE = "bill_type";

    public static final String COL_PAY_TYPE = "pay_type";

    public static final String COL_MONEY = "money";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}