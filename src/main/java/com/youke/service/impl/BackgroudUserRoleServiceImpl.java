package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.dao.BackgroudUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.youke.dao.BackgroudUserRoleMapper;
import com.youke.entity.BackgroudUserRole;
import com.youke.service.BackgroudUserRoleService;

import java.util.List;

@Service
public class BackgroudUserRoleServiceImpl implements BackgroudUserRoleService{

    @Autowired
    private BackgroudUserRoleMapper backgroudUserRoleMapper;

    @Autowired
    private BackgroudUserMapper backgroudUserMapper;

    @Override
    public int insert(BackgroudUserRole record) {
        return backgroudUserRoleMapper.insert(record);
    }

    @Override
    public int updateByPrimaryKeySelective(BackgroudUserRole record) {
        return backgroudUserRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List listRole() {
        QueryWrapper<BackgroudUserRole> backgroudUserRoleQueryWrapper = new QueryWrapper<BackgroudUserRole>();
        List<BackgroudUserRole> backgroudUserRoles = backgroudUserRoleMapper.selectList(backgroudUserRoleQueryWrapper);
        return backgroudUserRoles;
    }

    @Override
    public int deleteRoleById(BackgroudUserRole userRole) {
        //先判断当前角色是否有用用户在用
       int count = backgroudUserMapper.checkRole(userRole.getId());
       if (count > 0) return -10;
       return backgroudUserRoleMapper.deleteByPrimaryKey(userRole.getId());
    }

}
