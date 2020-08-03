package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youke.vo.AddressinfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-Order")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "`order`")
public class Order implements Serializable {
    /**
     * 订单表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="订单表id")
    private Integer id;

    /**
     * 订单号
     */
    @TableField(value = "order_no")
    @ApiModelProperty(value="订单号")
    private String orderNo;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 订单总金额
     */
    @TableField(value = "total_price")
    @ApiModelProperty(value="订单总金额")
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
     * 微信支付随机数
     */
    @TableField(value = "out_trade_no")
    @ApiModelProperty(value="微信支付随机数")
    private String outTradeNo;

    /**
     * 支付状态 0未支付 1已支付
     */
    @TableField(value = "pay_status")
    @ApiModelProperty(value="支付状态 0未支付 1已支付")
    private Integer payStatus;

    /**
     * 订单状态 0是未付款 1是待发货 2运送中 3是待收货 4是已完成 5是退款审核 6是退款中 7是退款完成
     */
    @TableField(value = "order_status")
    @ApiModelProperty(value="订单状态 0是未付款 1是待发货 2运送中 3是待收货 4是已完成 5是退款审核 6是退款中 7是退款完成")
    private Integer orderStatus;

    /**
     * 优惠卷id
     */
    @TableField(value = "coupon_id")
    @ApiModelProperty(value="优惠卷id")
    private Integer couponId;

    /**
     * 使用优惠卷标识，0是使用，1是未使用
     */
    @TableField(value = "coupon_flag")
    @ApiModelProperty(value="使用优惠卷标识，0是使用，1是未使用")
    private Integer couponFlag;

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

    /**
     * 支付时间
     */
    @TableField(value = "pay_time")
    @ApiModelProperty(value="支付时间")
    private String payTime;

    /**
     * 支付时间
     */
    @TableField(value = "send_time")
    @ApiModelProperty(value="发货时间")
    private String sendTime;



    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    /**
     * 使用优惠券后总金额
     */
    @TableField(value = "coupon_price")
    @ApiModelProperty(value="订单总金额")
    private BigDecimal couponprice;

    @TableField(value = "address_info")
    @ApiModelProperty(value="地址详情")
    private String addressInfo;

    /**
     * 订单详情
     */
    @TableField(exist = false)
    @ApiModelProperty(value="订单详情列表")
    private List<OrderDetail> orderDetails;

    /**
     * 订单详情
     */
    @TableField(exist = false)
    @ApiModelProperty(value="订单详情")
    private OrderDetail orderDetail;

    /**
     * 商品信息
     */
    @TableField(exist = false)
    @ApiModelProperty(value="商铺信息")
    private Shops shops;

    @TableField(value = "shops_id")
    @ApiModelProperty(value="商铺id")
    private Integer shopsId;

    @TableField(exist = false)
    private AddressinfoVO maps;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_NO = "order_no";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TOTAL_PRICE = "total_price";

    public static final String COL_TRANSACTION_ID = "transaction_id";

    public static final String COL_NONCE_STR = "nonce_str";

    public static final String COL_OUT_TRADE_NO = "out_trade_no";

    public static final String COL_PAY_STATUS = "pay_status";

    public static final String COL_ORDER_STATUS = "order_status";

    public static final String COL_COUPON_ID = "coupon_id";

    public static final String COL_COUPON_FLAG = "coupon_flag";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}