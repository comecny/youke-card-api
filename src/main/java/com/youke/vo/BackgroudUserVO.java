package com.youke.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BackgroudUserVO implements Serializable {

    private static long serialVersionUID = 1231423132L;

    private String nickName;
    private String avatarUrl;
    private Integer backgroudUserId;
    private String backgroudUserName;
    private Date createTime;
    private Integer userId;
    private Integer roleId;
    private String role;
    private String roleDescribes;
    private String bcakgroudUserPassword;
}
