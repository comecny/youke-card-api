package com.youke.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel(value="com-youke-entity-productsRelAttribute")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_rel_attribute")
public class productsRelAttribute implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    @TableField(value = "products_id")
    private Integer productsId;

    /**
     * 属性id
     */
    @ApiModelProperty(value="属性id")
    @TableField(value = "attribute_id")
    private Integer attributeId;
}
