package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.CashOut;
import com.youke.service.CashOutService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cashOut")
public class CashOutController {

    @Autowired
    private CashOutService cashOutService;

    @PostMapping("insertCashOut")
    @ApiOperation("新增提现申请")
    public Result<Void> insertCashOut(@RequestBody CashOut cashOut){
       int success = cashOutService.insertCashOut(cashOut);
       if (success > 0){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
      return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

}
