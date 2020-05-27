package com.youke.controller.background;

import com.youke.entity.BackgroudUserRole;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.BackgroudUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("backgroud/role")
public class BackgroudUserRoleController {

    @Autowired
    private BackgroudUserRoleService roleService;

    @PostMapping("insertRole")
    @ApiOperation("新增后台角色")
    public Result<Void> insertRole(@RequestBody BackgroudUserRole userRole){
        int success = roleService.insert(userRole);
        if (success > 0){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listRole")
    @ApiOperation("查询角色列表")
    public Result<List<BackgroudUserRole>> listRole(){
        List list = roleService.listRole();
        return new Result<List<BackgroudUserRole>>(list,MsgCode.FIND_SUCCESS);
    }

    @PutMapping("updateRole")
    @ApiOperation("修改角色信息")
    public Result<Void> updateRole(@RequestBody BackgroudUserRole userRole){
        int success = roleService.updateByPrimaryKeySelective(userRole);
        if (success > 0){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    /**
     *
     * @param userRole
     * @return
     */
    @DeleteMapping("deleteRole")
    @ApiOperation("删除角色")
    public Result<Void> deleteRoleById(@RequestBody BackgroudUserRole userRole){
      int success = roleService.deleteRoleById(userRole);
      if (success > 0){
          return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
      }if (success == -10){
          return new Result<Void>(null,MsgCode.DELETE_USE);
      }
      return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

}
