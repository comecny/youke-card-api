package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.service.ProductsService;
import com.youke.vo.ReqProductsVO;
import com.youke.vo.ReqStocksVO;
import com.youke.vo.ResqProductsVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("insertProducts")
    @ApiOperation("新增商品信息")
    public Result<Void> insertProducts(@RequestBody ReqProductsVO productsVO){
       boolean success = productsService.insertProducts(productsVO);
       if (success){
           return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @PostMapping("insertProductsStocks")
    @ApiOperation("新增商品库存")
    public Result<Void> insertProductsStocks(@RequestBody ReqStocksVO stocksVO){
       boolean success = productsService.insertProductsStocks(stocksVO);
       if (success){
           return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("getProductsById/{productsId}")
    @ApiOperation("通过商品id查询商品详细信息")
    public Result<ResqProductsVO> getProductsById(@PathVariable("productsId")Integer productsId){
        ResqProductsVO map = productsService.getProductsById(productsId);
      return new Result<ResqProductsVO>(map,MsgCode.FIND_SUCCESS);
    }
}
