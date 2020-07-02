package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.BackgroundMenu;
import com.youke.entity.BackgroundMenuRelRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BackgroundMenuMapper extends BaseMapper<BackgroundMenuRelRole> {

    List<BackgroundMenu> listMneu(@Param("roleId")Integer roleId);

    int deleteBatch(List<BackgroundMenuRelRole> list);

}
