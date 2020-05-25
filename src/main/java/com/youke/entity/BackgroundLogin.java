package com.youke.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class BackgroundLogin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
