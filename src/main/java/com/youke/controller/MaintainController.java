package com.youke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Maintain;
import com.youke.service.impl.MaintainService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("maintain")
public class MaintainController {

    @Autowired
    private MaintainService maintainService;

    @PostMapping("createMaintain")
    @ApiOperation("创建维权")
    public Result<Void> createMaintain(@RequestBody Maintain maintain){
        maintain.setCreateTime(DateUtil.nowDate());
        boolean save = maintainService.save(maintain);
        if (save){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listMaintain")
    @ApiOperation("分页查询维权列表")
    public Result<IPage<Maintain>> listBackMaintain(Integer page,Integer length,Integer userId){
       IPage<Maintain> iPage =  maintainService.listBackMaintain(page,length,userId);
       return new Result<IPage<Maintain>>(iPage,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("maintainHandle")
    @ApiOperation("维权处理")
    public Result<Void> maintainHandle(@RequestBody Maintain maintain){
        maintain.setUpdateTime(DateUtil.nowDate());
        maintain.setMaintainStatus(1);
        boolean success = maintainService.updateById(maintain);
        if (success){
            return new Result<Void>(null,MsgCode.HANDLE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.HANDLE_FAIL);
    }

    @GetMapping("getMaintainByOderId/{userId}/{orderId}")
    @ApiOperation("获取当前订单的维权信息")
    public Result<Maintain> getMaintainByOderId(@PathVariable("userId") Integer userId,@PathVariable("orderId")Integer orderId){
        QueryWrapper<Maintain> maintainQueryWrapper =
                new QueryWrapper<Maintain>()
                        .setEntity(Maintain.builder().orderId(orderId).userId(userId).build());
        Maintain info = maintainService.getOne(maintainQueryWrapper);
        return new Result<Maintain>(info,MsgCode.FIND_SUCCESS);
    }
}
