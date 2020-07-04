package com.youke.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.dao.UserMapper;
import com.youke.entity.CashOut;
import com.youke.entity.User;
import com.youke.service.CashOutService;
import com.youke.service.UserService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("cashOut")
public class CashOutController {

    @Autowired
    private CashOutService cashOutService;

    @Autowired
    private UserMapper userMapper;

    @PostMapping("insertCashOut")
    @ApiOperation("新增提现申请")
    public Result<Void> insertCashOut(@RequestBody CashOut cashOut){
       int success = cashOutService.insertCashOut(cashOut);
       if (success > 0){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
      return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listCashOut")
    @ApiOperation("小程序提现申请列表")
    public Result<Map<String, Object>> listCashOutByUserId(Integer userId){
        List<CashOut> list = cashOutService.list(new QueryWrapper<CashOut>().setEntity(CashOut.builder().userId(userId).build()));
        User user = userMapper.getUser(userId);
        String balance = user.getBalance();
        Map<String, Object> map = new HashMap<>();
        map.put("CashOutList",list);
        map.put("balance",balance);
        return new Result<Map<String, Object>>(map,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("listBackCashOut")
    @ApiOperation("后台提现申请列表")
    public Result<IPage<CashOut>> listBackCashOutByUserId(Integer page, Integer length){
        IPage<CashOut> list = cashOutService.listBackCashOutByUserId(page,length);
        return new Result<IPage<CashOut>>(list,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("backExamineCashOut")
    @ApiOperation("后台提现审核")
    public Result<Void> backExamineCashOut(@RequestBody CashOut cashOut){
        cashOut.setUpdateTime(DateUtil.nowDate());
       int success = cashOutService.backExamineCashOut(cashOut);
       if (success > 0){
           return new Result<Void>(null,MsgCode.SUCCESS);
       }
       return new Result<Void>(null,MsgCode.FAIL);
    }


}
