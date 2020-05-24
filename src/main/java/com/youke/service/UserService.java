package com.youke.service;

import com.youke.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    int insertUser(User user);

    int updateUser(User user);

    User getUser(Integer id);
}
