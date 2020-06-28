package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {

    IPage<User> findAll(Page<User> userPage, Object o,@Param("phone")String phone,@Param("nickName")String nickName);

    int insertUser(User user);

    int updateUser(User user);

    User getUser(@Param("id")Integer id);

}
