package com.youke.entity;


import lombok.Data;

@Data
public class PayInfo {

    private String totalPrice;
    private Integer orderId;
    private Integer userId;
    private String openId;

}
