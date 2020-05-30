package com.youke.vo;

import com.youke.entity.ProductsAttribute;
import com.youke.entity.ProductsOptions;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AttributeListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProductsAttribute attributes;

    private List<ProductsOptions> productsOptions;
}
