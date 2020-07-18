package com.youke.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.User;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.UserService;
import io.swagger.annotations.ApiOperation;
import okhttp3.OkHttp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("listUser")
    @ApiOperation("查询用户列表")
    public Result<IPage<User>> listUser(Integer page, Integer length,String phone,String nickName){
        IPage<User> list = userService.findAll(page,length,phone,nickName);
        return new Result<IPage<User>>(list, MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getUser/{id}")
    @ApiOperation("查询用户信息")
    public Result<User> getUser(@PathVariable("id")Integer id){
       User user =userService.getUser(id);
       return new Result<User>(user,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getUserById/{userId}")
    @ApiOperation("后台使用的查询用户信息")
    public Result<User> getUserById(@PathVariable("userId")Integer userId){
      User user = userService.getUserById(userId);
      return new Result<User>(user,MsgCode.FIND_SUCCESS);
    }
    @PostMapping("insetUser")
    @ApiOperation("新增用户")
    public Result<Void> insertUser(@RequestBody User user){
      int success = userService.insertUser(user);
      if (success > 0){
          return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
      }
      return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PutMapping("updateUser")
    @ApiOperation("修改用户信息")
    public Result<Void> updateUser(@RequestBody User user){
       int success = userService.updateUser(user);
       if (success > 0){
           return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @PostMapping("wxRegister")
    @ApiOperation("小程序端注册")
    public Result<Void> wxRegister(@RequestBody User user){
       int success = userService.register(user);
       if (success > 0){
           return new Result<Void>(null,MsgCode.REGISTER_SUCCESS);
       }if (success == -10){
           return new Result<Void>(null,MsgCode.PRINT_COUNT_FIAL);
        }if (success == -11){
           return new Result<Void>(null,MsgCode.PASSWORD_NULL_FIAL);
        }if (success == -12){
           return new Result<Void>(null,MsgCode.USER_EXIST);
        }
       return new Result<Void>(null,MsgCode.REGISTER_FAIL);
    }

    @PostMapping("wxLogin")
    @ApiOperation("小程序端登录")
    public Result<User> wxLogin(@RequestBody User user){
      User info = userService.wxLogin(user);
      if (!ObjectUtils.isEmpty(info)){
          return new Result<User>(info,MsgCode.LOGIN_SUCCESS);
      }
      return new Result<User>(null,MsgCode.LOGIN_FAIL);
    }

}
