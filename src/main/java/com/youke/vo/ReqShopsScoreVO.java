package com.youke.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ReqShopsScoreVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer score;

    private Integer shopsId;

    private Integer userId;

}
