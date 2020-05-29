package com.youke.vo;

import com.youke.entity.BackgroudPermissions;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class BackgroudUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private List<BackgroudPermissions> listPermissins;
}
