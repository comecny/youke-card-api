package com.youke.vo;

import com.youke.entity.Products;
import com.youke.entity.ProductsResources;
import com.youke.entity.ProductsStocks;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ReqProductsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品主体
     */
    private Products products;

    /**
     *商品资源
     */
    private List<ProductsResources> productsResources;

    /**
     * 属性以及属性选项列表
     */
    private List<AttributeRelOptionsVO> attributeRelOptionsVOS;
}
