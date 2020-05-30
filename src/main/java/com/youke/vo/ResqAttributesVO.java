package com.youke.vo;

import lombok.Data;

import java.io.Serializable;

import java.util.ArrayList;

@Data
public class ResqAttributesVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String attributeName;

    private ArrayList<ResqOptionsVO> optionsVOS;

}
