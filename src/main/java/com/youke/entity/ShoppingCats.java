package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.youke.vo.ResqOptionsVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-ShoppingCats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shopping_cats")
public class ShoppingCats implements Serializable {
    /**
     * 购物车表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="购物车表id")
    private Integer id;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value="商品id")
    private Integer productsId;

    /**
     * 库存价格表id
     */
    @TableField(value = "products_stocks_id")
    @ApiModelProperty(value="库存价格表id")
    private Integer productsStocksId;

    /**
     * 数量
     */
    @TableField(value = "number")
    @ApiModelProperty(value="数量")
    private Integer number;

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

    @TableField(exist = false)
    private List<ResqOptionsVO> resqOptionsVO;

    @TableField(exist = false)
    private ProductsStocks productsStocks;

    @TableField(exist = false)
    private Products products;

    @TableField(exist = false)
    private Shops shops;

    @TableField(value = "shops_id")
    @ApiModelProperty(value="商铺id")
    private Integer shopsId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_PRODUCTS_STOCKS_ID = "products_stocks_id";

    public static final String COL_NUMBER = "number";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}