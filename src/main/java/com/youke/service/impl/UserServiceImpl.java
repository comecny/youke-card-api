package com.youke.service.impl;

import com.youke.dao.UserDao;
import com.youke.entity.User;
import com.youke.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAll() {
       return userDao.findAll();
    }
}
