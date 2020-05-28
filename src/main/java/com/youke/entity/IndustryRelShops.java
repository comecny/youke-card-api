package com.youke.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class IndustryRelShops implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 行业id
     */
    private Integer industryId;

    /**
     * 店铺id
     */
    private Integer shopsid;
}
