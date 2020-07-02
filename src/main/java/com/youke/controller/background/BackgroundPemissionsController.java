package com.youke.controller.background;

import com.youke.entity.BackgroundRoleRelPemissions;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.BackgroudPermissionsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("background/pemission")
public class BackgroundPemissionsController {

    @Autowired
    private BackgroudPermissionsService permissionsService;

    @GetMapping("listPemission")
    @ApiOperation("查询权限列表")
    public Result<Map<Integer, Object>> listPemission(){
        Map<Integer, Object> map = permissionsService.listPemission();
        return new Result<Map<Integer, Object>>(map, MsgCode.FIND_SUCCESS);
    }

    @PostMapping("insertPemissionsRelRole")
    @ApiOperation("的关系")
    public Result<Void> insertPemissionsRelRole(@RequestBody BackgroundRoleRelPemissions roleRelPemissions){
       int success = permissionsService.insertPemissionsRelRole(roleRelPemissions);
       if (success > 0){
           return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @DeleteMapping("deletePemissionsRelRole")
    @ApiOperation("删除权限和角色的关系")
    public Result<Void> deletePemissionsRelRole(@RequestBody BackgroundRoleRelPemissions roleRelPemissions){
      boolean success = permissionsService.deletePemissionsRelRole(roleRelPemissions);
      if (success){
          return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
      }
      return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }
}
