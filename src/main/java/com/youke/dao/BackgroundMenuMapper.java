package com.youke.dao;

import com.youke.entity.BackgroundMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BackgroundMenuMapper {

    List<BackgroundMenu> listMneu(@Param("roleId")Integer roleId);

}
