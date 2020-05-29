package com.youke.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Industry;
import com.youke.service.IndustryService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("industry")
public class IndustryController {

    @Autowired
    private IndustryService industryService;

    @PostMapping("insertIndustry")
    @ApiOperation("新增行业分类")
    public Result<Void> insertIndustry(@RequestBody Industry industry){
        industry.setCreateTime(DateUtil.nowDate());
        boolean success = industryService.save(industry);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PutMapping("updateIndustryById")
    @ApiOperation("修改行业分类")
    public Result<Void> updateIndustryById(@RequestBody Industry industry){
        industry.setUpdateTime(DateUtil.nowDate());
        boolean success = industryService.updateById(industry);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @DeleteMapping("deleteIndustryById")
    @ApiOperation("删除行业分类")
    public Result<Void> deleteIndustryById(@RequestBody Industry industry){
        industry.setStatus("1");
        industry.setUpdateTime(DateUtil.nowDate());
        boolean success = industryService.updateById(industry);
        if (success){
            return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @GetMapping("listIndustry")
    @ApiOperation("查询行业列表")
    public Result<List<Industry>> listIndustry(){
        List<Industry> list = industryService.list(new QueryWrapper<Industry>()
                .setEntity(Industry.builder().status("0").build()));
        return new Result<List<Industry>>(list,MsgCode.FIND_SUCCESS);
    }

}
