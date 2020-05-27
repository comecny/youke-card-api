package com.youke.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.User;

import java.util.List;

public interface UserService  {

    List<User> findAll();

    int insertUser(User user);

    int updateUser(User user);

    User getUser(Integer id);

    int register(User user);

    User wxLogin(User user);
}
