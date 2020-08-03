package com.youke.service;

import com.youke.entity.BackgroudPermissions;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.entity.BackgroundRoleRelPemissions;

import java.util.Map;

public interface BackgroudPermissionsService extends IService<BackgroudPermissions>{

    Map<Integer, Object> listPemission();

    int insertPemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions);

    boolean deletePemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions);
}
