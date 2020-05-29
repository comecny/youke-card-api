package com.youke.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com-youke-entity-ProductsOptionsRelStocks")
@Data
public class ProductsOptionsRelStocks implements Serializable {
    /**
     * 商品属性选项id
     */
    @ApiModelProperty(value = "商品属性选项id")
    private Integer optionId;

    /**
     * 库存表id
     */
    @ApiModelProperty(value = "库存表id")
    private Integer stockId;

    private static final long serialVersionUID = 1L;
}

