package com.youke.dao;


import com.youke.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    List<User> findAll();

    int insertUser(User user);

    int updateUser(User user);

    User getUser(@Param("id")Integer id);
}
