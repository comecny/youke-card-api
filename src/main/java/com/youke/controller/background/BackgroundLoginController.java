package com.youke.controller.background;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.entity.BackgroundLogin;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Shops;
import com.youke.service.ShopsService;
import com.youke.vo.BackgroudUserVO;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("background")
public class BackgroundLoginController {

    @Autowired
    private ShopsService shopsService;

    @PostMapping("login")
    @ApiOperation("后台管理系统登录")
    public Result<Void> login(@RequestBody BackgroundLogin login){

        try {
            //创建shiro的jubject
            String username = login.getUsername();
            String password = login.getPassword();
            Subject subject = SecurityUtils.getSubject();

            UsernamePasswordToken token = new UsernamePasswordToken(username,password);
            subject.login(token);
            BackgroudUserVO userInfo =(BackgroudUserVO) SecurityUtils.getSubject().getPrincipal();
            QueryWrapper<Shops> shopsQueryWrapper = new QueryWrapper<>(Shops.builder().userId(userInfo.getUserId()).build());
            Shops shops = shopsService.getOne(shopsQueryWrapper);
            userInfo.setShops(shops);
            userInfo.setBcakgroudUserPassword(null);
            return new Result(userInfo, MsgCode.LOGIN_SUCCESS);
        }catch (IncorrectCredentialsException e){
            //错误返回消息
            return new Result(null, MsgCode.PASSWORD_FAIL);
        }catch (LockedAccountException | UnknownAccountException e){
            return new Result(null,MsgCode.COUNT_FIAL);
        } catch (AuthenticationException e){
            return new Result(null,MsgCode.PASSWORD_NULL_FIAL);
        }
    }

    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    @ResponseBody
    public Result logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(null,MsgCode.LAYOUT_SUCEESS);
    }


}
