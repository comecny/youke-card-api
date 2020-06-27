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

@ApiModel(value="com-youke-entity-Refund")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "refund")
public class Refund implements Serializable {
    /**
     * 退款表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="退款表id")
    private Integer id;

    /**
     * 发起退款人的用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="发起退款人的用户id")
    private Integer userId;

    /**
     * 发起人手机号
     */
    @TableField(value = "user_phone")
    @ApiModelProperty(value="发起人手机号")
    private String userPhone;

    /**
     * 店铺id
     */
    @TableField(value = "shops_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopsId;

    /**
     * 金额
     */
    @TableField(value = "moeny")
    @ApiModelProperty(value="金额")
    private BigDecimal moeny;

    /**
     * 原因
     */
    @TableField(value = "reason")
    @ApiModelProperty(value="原因")
    private String reason;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 创建时间（退款发起时间）
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间（退款发起时间）")
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_USER_PHONE = "user_phone";

    public static final String COL_SHOPS_ID = "shops_id";

    public static final String COL_MOENY = "moeny";

    public static final String COL_REASON = "reason";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_CREATE_TIME = "create_time";
}