package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.common.exception.db.UpdateException;
import com.youke.dao.AddressMapper;
import com.youke.dao.UserMapper;
import com.youke.entity.Address;
import com.youke.entity.User;
import com.youke.service.UserService;
import com.youke.utils.JWTUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public IPage<User> findAll(Integer page, Integer length, String phone, String nickName) {
       return userMapper.findAll(new Page<User>(page,length),null,phone,nickName);
    }

    @Override
    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userMapper.getUser(id);
    }

    @Override
    public int register(User user) {

        //校验参数
      if (user.getPhone() == null) return -10;
      if (user.getPassword() == null) return -11;

      //去数据库检查，电话是否存在
        QueryWrapper<User> userQueryWrapper =
                new QueryWrapper<User>().setEntity(User.builder().phone(user.getPhone()).build());
        Integer count = userMapper.selectCount(userQueryWrapper);
        if (count > 0) return -12;

        //将用户名和密码新增进数据库，秘密加密
        String md5pwd = new Md5Hash(user.getPassword(), "youke_20200525DIchK487WCXRAQ", 2).toString();
        user.setPassword(md5pwd);
        user.setBalance("0.00");
        user.setBackUserSign(0);
        user.setShopsSign("0");
        return userMapper.insertUser(user);
    }

    @Override
    public User wxLogin(User user) {
        //先检查数据库中是否有用户名和密码
        String md5pwd = new Md5Hash(user.getPassword(), "youke_20200525DIchK487WCXRAQ", 2).toString();
        QueryWrapper<User> userQueryWrapper =
                new QueryWrapper<User>().setEntity(User.builder().phone(user.getPhone()).password(md5pwd).build());
        User userInfo = userMapper.selectOne(userQueryWrapper);

        if (ObjectUtils.isEmpty(userInfo)){
            return null;
        }

        //在通过user中的id，去讲其他数据数据落数据库
        userInfo.setPassword(null);
        user.setId(userInfo.getId());
        user.setPassword(null);
        int isSuccess = userMapper.updateUser(user);
        if (isSuccess < 0) throw new UpdateException("登录时传入用户信息异常");

        String token = JWTUtils.jwtSign(String.valueOf(userInfo.getId()), userInfo.getPhone());
        user.setToken(token);
        return user;
    }

    @Override
    public User getUserById(Integer userId) {
        User user = userMapper.selectById(userId);
        List<Address> addressList = addressMapper.selectList(new QueryWrapper<Address>().setEntity(Address.builder().userId(userId).build()));
        user.setAddressList(addressList);
        return user;
    }
}
