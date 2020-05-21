package com.youke.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private static long serialVersionUID = 12314225123132L;

    private Integer id;
    private String name;
    private String sex;
    private Integer age;
}
