package com.youke.controller.background;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.BackgroudUser;
import com.youke.result.MsgCode;
import com.youke.result.Result;
import com.youke.service.BackgroudUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("backgroud/user")
public class BackgroudUserController {

    @Autowired
    private BackgroudUserService backgroudUserService;

    /**
     * 返回为-10用户已为后台用户
     * @param backgroudUser
     * @return
     */
    @PostMapping("insertBackgroudUser")
    @ApiOperation(value = "新增后台用户（将前台用户设为后台用户）")
    public Result<Void> insertBackgroudUser(@RequestBody BackgroudUser backgroudUser){
       int  success = backgroudUserService.insertBackgroudUser(backgroudUser);
       if (success > 0){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
       if (success == -10){
           return new Result<Void>(null,MsgCode.USER_EXIST);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listBackGroudUserPaging/{page}/{length}")
    @ApiOperation("分页查询后台用户列表")
    public Result< IPage<Map<String, Object>>> listBackGroudUserPaging(@PathVariable("page")Integer page,
                                                                       @PathVariable("length")Integer length){
        IPage<Map<String, Object>> paging = backgroudUserService.listBackGroudUserPaging(page,length);
        return new Result<IPage<Map<String, Object>>>(paging,MsgCode.FIND_SUCCESS);
    }
}
