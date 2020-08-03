package com.youke.service;

import java.util.List;

import com.youke.entity.BackgroudUserRole;



public interface BackgroudUserRoleService{

    int insert(BackgroudUserRole record);

    int updateByPrimaryKeySelective(BackgroudUserRole record);

    List<Object> listRole();

    int deleteRoleById(BackgroudUserRole userRole);
}
