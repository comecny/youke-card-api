package com.youke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.entity.BackgroudPermissions;
import com.youke.entity.BackgroundMenuRelRole;
import com.youke.vo.BackgroundMenuRelRoleVO;

import java.util.List;
import java.util.Map;

public interface BackgroundMenuService extends IService<BackgroundMenuRelRole> {

    Map listMenu(Integer roleId);

    int insertRoleRelMenu(BackgroundMenuRelRole menuRelRole);

    int deleteRoleRelMenu(BackgroundMenuRelRoleVO backgroundMenuRelRole);

    List<BackgroundMenuRelRole> listUserMenuByRoleId(Integer roleId);
}
