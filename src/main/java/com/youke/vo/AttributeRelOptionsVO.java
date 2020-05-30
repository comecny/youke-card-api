package com.youke.vo;

import com.youke.entity.ProductsOptions;
import lombok.Data;

import java.io.Serializable;

@Data
public class AttributeRelOptionsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProductsOptions options;

    private Integer attributeId;
}
