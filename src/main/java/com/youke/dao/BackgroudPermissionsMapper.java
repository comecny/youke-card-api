package com.youke.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.BackgroudPermissions;
import com.youke.entity.BackgroundRoleRelPemissions;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BackgroudPermissionsMapper extends BaseMapper<BackgroudPermissions> {

    int insertPemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions);

    boolean deletePemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions);

    List<BackgroudPermissions> listPermissionsByRoleId(Integer roleId);
}