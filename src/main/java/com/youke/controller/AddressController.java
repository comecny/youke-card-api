package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Address;
import com.youke.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("insertAddress")
    @ApiOperation("新增用户地址")
    public Result<Void> insertAddress(@RequestBody Address address){
       int success = addressService.insertAddress(address);
       if (success >0){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }
}
