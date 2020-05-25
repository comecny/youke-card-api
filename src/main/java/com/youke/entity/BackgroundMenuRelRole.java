package com.youke.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "backgroud_menu_rel_role")
public class BackgroundMenuRelRole implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer bUserRoleId;
    private Integer bMenuId;
}
