package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.User;

public interface UserService  {

    IPage<User> findAll(Integer page, Integer length, String phone,String nickName);

    int insertUser(User user);

    int updateUser(User user);

    User getUser(Integer id);

    int register(User user);

    User wxLogin(User user);

    User getUserById(Integer userId);
}
