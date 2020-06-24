package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.ShopsScore;
import com.youke.service.ShopsScoreService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("score")
public class ScoreController {

    @Autowired
    private ShopsScoreService shopsScoreService;

    @PostMapping("insertScore")
    @ApiOperation("新增评分")
    public Result<Void> insertScore(@RequestBody ShopsScore shopsScore){
        shopsScore.setCreateTime(DateUtil.nowDate());
        boolean success = shopsScoreService.save(shopsScore);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PutMapping("updateScoreById")
    @ApiOperation("修改评分信息")
    public Result<Void> updateScoreById(@RequestBody ShopsScore shopsScore){
        shopsScore.setUpdateTime(DateUtil.nowDate());
        boolean success = shopsScoreService.updateById(shopsScore);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @DeleteMapping("deleteScoreById")
    @ApiOperation("删除评分信息")
    public Result<Void> deleteScoreBYId(Integer id){
        boolean success = shopsScoreService.removeById(id);
        if (success){
            return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @GetMapping("listScore")
    @ApiOperation("查询评分信息")
    public Result<List<ShopsScore>> listScore(){
        List<ShopsScore> list = shopsScoreService.list();
        return new Result<List<ShopsScore>>(list,MsgCode.FIND_SUCCESS);
    }
}
