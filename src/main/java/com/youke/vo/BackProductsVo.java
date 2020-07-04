package com.youke.vo;

import com.youke.entity.ProductsStocks;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
public class BackProductsVo {

    private Integer ProductsId;

    private String productsNo;

    private Integer shopsId;

    private String productsName;

    private String majorPicture;

    private BigDecimal minPicture;

    private BigDecimal maxPicture;

    private Integer productsStatus;

    private ArrayList<ProductsStocks> productsStocks;

    private Integer totalStocks;
}
