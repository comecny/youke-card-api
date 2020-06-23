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

import java.util.List;

@ApiModel(value="com-youke-entity-ShopsFeeOrder")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops_fee_order")
public class ShopsFeeOrder implements Serializable {
    /**
     * 店铺会费订单
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="店铺会费订单")
    private Integer id;

    /**
     * 店铺id
     */
    @TableField(value = "shop_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopId;

    /**
     * 订单编号
     */
    @TableField(value = "shops_fee_no")
    @ApiModelProperty(value="订单编号")
    private String shopsFeeNo;

    /**
     * 总金额
     */
    @TableField(value = "total_price")
    @ApiModelProperty(value="总金额")
    private BigDecimal totalPrice;

    /**
     * 微信支付订单号
     */
    @TableField(value = "transaction_id")
    @ApiModelProperty(value="微信支付订单号")
    private String transactionId;

    /**
     * 微信支付随机数
     */
    @TableField(value = "nonce_str")
    @ApiModelProperty(value="微信支付随机数")
    private String nonceStr;

    /**
     * 微信支付商户号
     */
    @TableField(value = "out_trade_no")
    @ApiModelProperty(value="微信支付商户号")
    private String outTradeNo;

    /**
     * 支付状态  0是未支付，1是已支付
     */
    @TableField(value = "fee_pay_status")
    @ApiModelProperty(value="支付状态  0是未支付，1是已支付")
    private Integer feePayStatus;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后修改时间")
    private String updateTime;

    @TableField(exist = false)
    private List<ShopsRelIndustry> shopsRelIndustryList;

    /**
     * 行业名
     */
    @TableField(value = "industry_name_list")
    @ApiModelProperty(value="行业名")
    private String industryNameList;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String SHOPS_REL_INDUSTRY_LIST = "shopsRelIndustryList";

    public static final String COL_SHOP_ID = "shop_id";

    public static final String COL_SHOPS_FEE_NO = "shops_fee_no";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_TRANSACTION_ID = "transaction_id";

    public static final String COL_NONCE_STR = "nonce_str";

    public static final String COL_OUT_TRADE_NO = "out_trade_no";

    public static final String COL_FEE_PAY_STATUS = "fee_pay_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";
}