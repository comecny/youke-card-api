package com.youke.vo;

import com.youke.entity.ProductsStocks;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public class ResqProductsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String productsNo;

    private Integer shopsId;

    private String productsName;

    private String introduction;

    private String minPicture;

    private String majorPicture;

    private String maxPicture;

    private String scribePicture;

    private String delivePlace;

    private Integer productsStatus;

    private String content;

    private ArrayList<ResqResourcesVO> resourcesVOS;

    private ArrayList<ResqAttributesVO> attributesVOS;

    private List<ProductsStocks> ProductsStocks;


}
