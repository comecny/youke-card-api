package com.youke.controller;

import com.youke.entity.User;
import com.youke.result.MsgCode;
import com.youke.result.Result;
import com.youke.service.UserService;
import com.youke.utils.CosUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    @ApiOperation("查询用户")
    public Result<List<User>> getUser(MultipartFile file){
       List<User> user = userService.findAll();
        String upload = CosUtil.upload(file);
        return new Result<List<User>>(user, MsgCode.FIND_SUCCESS);
    }


}
