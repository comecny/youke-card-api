package com.youke.service;

import com.youke.entity.BackgroudUserRole;

import java.util.List;

public interface BackgroudUserRoleService{

    int insert(BackgroudUserRole record);

    int updateByPrimaryKeySelective(BackgroudUserRole record);

    List listRole();

    int deleteRoleById(BackgroudUserRole userRole);
}
