package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.ProductsService;
import com.youke.vo.InsertProductsVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("insertProducts")
    @ApiOperation("新增商品信息")
    public Result<Void> insertProducts(@RequestBody InsertProductsVO productsVO){
       boolean success = productsService.insertProducts(productsVO);
       if (success){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }
}
