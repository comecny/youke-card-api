package com.youke.controller;

import com.youke.entity.User;
import com.youke.result.MsgCode;
import com.youke.result.Result;
import com.youke.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("listUser")
    @ApiOperation("查询用户列表")
    public Result<List<User>> listUser(){
       List<User> list = userService.findAll();
        return new Result<List<User>>(list, MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getUser/{id}")
    @ApiOperation("查询用户信息")
    public Result<User> getUser(@PathVariable("id")Integer id){
       User user =userService.getUser(id);
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

}
