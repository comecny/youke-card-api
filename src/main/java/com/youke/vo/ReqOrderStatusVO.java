package com.youke.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class ReqOrderStatusVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer orderId;

    private String operate;

    private String sendTime;
}
