package com.youke.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.util.List;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.AUTO)
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
    private Integer backUserSign;
    @TableField(exist = false)
    private String token;
    @TableField(exist = false)
    private List<Address> addressList;
    @TableField(exist = false)
    private String createTime;
    @TableField(exist = false)
    private String status;


}
