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

@ApiModel(value="com-youke-entity-ProductsStocks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_stocks")
public class ProductsStocks implements Serializable {
    /**
     * 商品库存表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="商品库存表id")
    private Integer id;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value="商品id")
    private Integer productsId;

    /**
     * sku图片
     */
    @TableField(value = "picture")
    @ApiModelProperty(value="sku图片")
    private String picture;

    /**
     * 价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="价格")
    private BigDecimal price;

    /**
     * 库存
     */
    @TableField(value = "stocks")
    @ApiModelProperty(value="库存")
    private String stocks;

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
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_PICTURE = "picture";

    public static final String COL_PRICE = "price";

    public static final String COL_STOCKS = "stocks";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}