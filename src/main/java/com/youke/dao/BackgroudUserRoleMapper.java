package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.BackgroudUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BackgroudUserRoleMapper extends BaseMapper<BackgroudUserRole> {

    int deleteByPrimaryKey(Integer id);

    int insert(BackgroudUserRole record);

    int insertSelective(BackgroudUserRole record);

    BackgroudUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackgroudUserRole record);

    int updateByPrimaryKey(BackgroudUserRole record);
}