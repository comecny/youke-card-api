package com.youke.vo;

import com.youke.entity.BackgroudPermissions;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class BackgroudUserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 微信头像
     */
    private String avatarUrl;

    /**
     * 后台用户id
     */
    private Integer backgroudUserId;

    /**
     * 后台用户名
     */
    private String backgroudUserName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 小程序userid
     */
    private Integer userId;

    /**
     *角色id
     */
    private Integer roleId;

    /**
     * role
     */
    private String role;

    /**
     * role描述
     */
    private String roleDescribes;

    /**
     * 后台用户密码
     */
    private String bcakgroudUserPassword;

    /**
     * 权限列表
     */
    private List<BackgroudPermissions> listPermissins;
}
