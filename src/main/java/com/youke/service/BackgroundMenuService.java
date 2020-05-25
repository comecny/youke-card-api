package com.youke.service;

import com.youke.entity.BackgroundMenuRelRole;

import java.util.List;
import java.util.Map;

public interface BackgroundMenuService {

    Map listMenu(Integer roleId);

    int insertRoleRelMenu(BackgroundMenuRelRole menuRelRole);

    int deleteRoleRelMenu(BackgroundMenuRelRole backgroundMenuRelRole);

    List<BackgroundMenuRelRole> listUserMenuByRoleId(Integer roleId);
}
