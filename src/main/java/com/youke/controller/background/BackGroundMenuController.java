package com.youke.controller.background;

import com.youke.entity.BackgroundMenuRelRole;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.BackgroundMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("background/menu")
public class BackGroundMenuController {

    @Autowired
    private BackgroundMenuService menuService;

    @GetMapping("listMenuByRoleId/{roleId}")
    @ApiOperation("菜单列表查询")
    public Result<Map> listMenuByRoleId(@PathVariable("roleId")Integer roleId){
      Map map = menuService.listMenu(roleId);
        return new Result<Map>(map, MsgCode.FIND_SUCCESS);
    }

    @PostMapping("insertRoleRelMenu")
    @ApiOperation("新增角色喝菜单列表关联")
    public Result<Void> insertRoleRelMenu(@RequestBody BackgroundMenuRelRole menuRelRole){
       int success = menuService.insertRoleRelMenu(menuRelRole);
       if (success > 0){
           return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @DeleteMapping("deleteRoleRelMenu")
    @ApiOperation("删除角色和菜单的关联")
    public Result<Void> deleteRoleRelMenu(@RequestBody BackgroundMenuRelRole backgroundMenuRelRole){
       int success = menuService.deleteRoleRelMenu(backgroundMenuRelRole);
       if (success > 0){
           return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @GetMapping("listUserMenuByRoleId/{roleId}")
    @ApiOperation("通过角色id查询当角色有那些菜单权限")
    public Result<List<BackgroundMenuRelRole>> listUserMenuByRoleId(@PathVariable("roleId")Integer roleId){
        List<BackgroundMenuRelRole> list = menuService.listUserMenuByRoleId(roleId);
        return new Result<List<BackgroundMenuRelRole>>(list,MsgCode.FIND_SUCCESS);
    }

}
