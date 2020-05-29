package com.youke.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

@ApiModel(value = "com-youke-entity-ProductsAttributeRelOptions")
@Data
public class ProductsAttributeRelOptions implements Serializable {
    /**
     * 属性id
     */
    @ApiModelProperty(value = "属性id")
    private Integer attributeId;

    /**
     * 属性选项id
     */
    @ApiModelProperty(value = "属性选项id")
    private Integer optionsId;

    private static final long serialVersionUID = 1L;
}

