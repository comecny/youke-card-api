package com.youke.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResqResourcesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer productsId;

    private String url;

    private Integer type;

    private Integer majorType;
}
