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

@ApiModel(value="com-youke-entity-OrderDetail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "order_detail")
public class OrderDetail implements Serializable {
    /**
     * 订单详情表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="订单详情表")
    private Integer id;

    /**
     * 订单id
     */
    @TableField(value = "order_id")
    @ApiModelProperty(value="订单id")
    private Integer orderId;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value="商品id")
    private Integer productsId;

    /**
     * 库存表id
     */
    @TableField(value = "stocks_id")
    @ApiModelProperty(value="库存表id")
    private Integer stocksId;

    /**
     * 商品名
     */
    @TableField(value = "products_name")
    @ApiModelProperty(value="商品名")
    private String productsName;

    /**
     * 主图
     */
    @TableField(value = "major_picture")
    @ApiModelProperty(value="主图")
    private String majorPicture;

    /**
     * 数量
     */
    @TableField(value = "number")
    @ApiModelProperty(value="数量")
    private Integer number;

    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="价格")
    private BigDecimal price;

    /**
     * 属性，属性选项，关联出来的json字符串
     */
    @TableField(value = "attr_opt_content")
    @ApiModelProperty(value="属性，属性选项，关联出来的json字符串")
    private String attrOptContent;

    @TableField(value = "introduction")
    @ApiModelProperty(value="商品简介")
    private String introduction;

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

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ORDER_ID = "order_id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_STOCKS_ID = "stocks_id";

    public static final String COL_PRODUCTS_NAME = "products_name";

    public static final String COL_MAJOR_PICTURE = "major_picture";

    public static final String COL_NUMBER = "number";

    public static final String COL_PRICE = "price";

    public static final String COL_ATTR_OPT_CONTENT = "attr_opt_content";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}