package com.youke.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static long serialVersionUID = 12314225123132L;

    private Integer id;
    private String nickName;
    private String avatarUrl;
    private String gender;
    private String country;
    private String province;
    private String city;
    private String phone;
    private String password;
    private String shopsSign;
    private String openid;
    private String unionid;
    private String balance;

}
