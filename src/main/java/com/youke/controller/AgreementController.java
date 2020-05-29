package com.youke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Agreement;
import com.youke.service.AgreementService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("agreement")
public class AgreementController {

    @Autowired
    private AgreementService agreementService;

    @PostMapping("insertAgreement")
    @ApiOperation("新增协议表内容")
    public Result<Void> insertAgreement(@RequestBody Agreement agreement){
        agreement.setCreateTime(DateUtil.nowDate());
        boolean success = agreementService.save(agreement);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listAgreement")
    @ApiOperation("查询协议列表")
    public Result<List<Agreement> > listAgreement(){
        List<Agreement> list = agreementService.list(new QueryWrapper<Agreement>()
                .setEntity(Agreement.builder().status("0").build())
                .select(Agreement.class,info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("update_time") && !info.getColumn().equals("status")));
        return new Result<List<Agreement>>(list,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getAgreementById/{id}")
    @ApiOperation("查询协议详情")
    public Result<Agreement> getAgreementById(@PathVariable("id")Integer id){
        Agreement info = agreementService.getById(id);
        return new Result<Agreement>(info,MsgCode.FIND_SUCCESS);
    }

    @DeleteMapping("deleteAgreementById")
    @ApiOperation("删除协议")
    public Result<Void> deleteAgreementById(@RequestBody Agreement agreement){
        agreement.setStatus("1");
        agreement.setUpdateTime(DateUtil.nowDate());
        boolean success = agreementService.updateById(agreement);
        if (success){
            return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @PutMapping("updateAgreementById")
    @ApiOperation("修改协议")
    public Result<Void> updateAgreementById(@RequestBody Agreement agreement){
        agreement.setUpdateTime(DateUtil.nowDate());
        boolean success = agreementService.updateById(agreement);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }
}
