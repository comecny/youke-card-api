package com.youke.service.impl;

import com.youke.entity.BackgroundRoleRelPemissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.BackgroudPermissionsMapper;
import com.youke.entity.BackgroudPermissions;
import com.youke.service.BackgroudPermissionsService;

@Service
public class BackgroudPermissionsServiceImpl extends ServiceImpl<BackgroudPermissionsMapper,
        BackgroudPermissions> implements BackgroudPermissionsService{

    @Autowired
    private BackgroudPermissionsMapper permissionsMapper;

    @Override
    public Map<Integer, Object> listPemission() {
        List<BackgroudPermissions> backgroudPermissions = permissionsMapper.selectList(null);
        Map<Integer, Object> totalMap = new HashMap<>();
        for (BackgroudPermissions backgroudPermission : backgroudPermissions) {
            List<BackgroudPermissions> collect = backgroudPermissions
                    .stream()
                    .filter(str -> str.getMenuId().equals(backgroudPermission.getMenuId()))
                    .collect(Collectors.toList());

            totalMap.put(backgroudPermission.getMenuId(),collect);
        }

        return totalMap;
    }

    @Override
    public int insertPemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions) {
        return permissionsMapper.insertPemissionsRelRole(roleRelPemissions);
    }

    @Override
    public boolean deletePemissionsRelRole(BackgroundRoleRelPemissions roleRelPemissions) {
        return permissionsMapper.deletePemissionsRelRole(roleRelPemissions);
    }
}
