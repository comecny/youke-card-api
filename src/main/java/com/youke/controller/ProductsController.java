package com.youke.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Products;
import com.youke.entity.ProductsEvaluate;
import com.youke.entity.ProductsStocks;
import com.youke.service.ProductsService;
import com.youke.vo.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;
import java.util.*;


@RestController
@RequestMapping("products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @PostMapping("insertProducts")
    @ApiOperation("新增商品信息")
    public Result<Map<String,Object>> insertProducts(@RequestBody ReqProductsVO productsVO){
        Map<String,Object> map = productsService.insertProducts(productsVO);
       if (!map.isEmpty()){
           return new Result<Map<String,Object>>(map, MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Map<String,Object>>(null,MsgCode.INSERT_FAIL);
    }

    @PostMapping("insertProductsStocks")
    @ApiOperation("新增商品库存")
    public Result<Void> insertProductsStocks(@RequestBody ReqStocksVO reqStocksVO){
       boolean success = productsService.insertProductsStocks(reqStocksVO);
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

    @PostMapping("getProductsStocks")
    @ApiOperation("通过选项id获取库存价格")
    public Result<ProductsStocks> getProductsStocks(@RequestBody ReqOptionsVO optionsVO){
        ProductsStocks info = productsService.getProductsStocks(optionsVO);
        return new Result<ProductsStocks>(info,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("listProductsPaging")
    @ApiOperation("分页查询商品列表")
    public Result<IPage<Products>> listProductsPaging(Integer page,Integer length,Integer shopsId){
       IPage<Products> productsIPage = productsService.listProductsPaging(page,length,shopsId);
        return new Result<IPage<Products>>(productsIPage,MsgCode.FIND_SUCCESS);
    }

    @DeleteMapping("deleteProducts")
    @ApiOperation("删除商品")
    public Result<Void> deleteProducts(@RequestBody Products products){
      int success = productsService.deleteProducts(products);
      if (success > 0){
          return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
      }
      return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @PostMapping("insertProductsEvaluate")
    @ApiOperation("新增商品评价")
    public Result<Void> insertProductsEvaluate(@RequestBody ProductsEvaluate productsEvaluate){
       int success = productsService.insertProductsEvaluate(productsEvaluate);
       if (success > 0){
           return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("getProductsEcaluetre")
    @ApiOperation("查询商品评论详情")
    public Result<ProductsEvaluate> getProductsEcaluetre(Integer shopsId,Integer ecaluetreId,
                                                         Integer productsId,Integer userId) throws ExecutionException,
            InterruptedException {
       ProductsEvaluate info = productsService.getProductsEcaluetre(shopsId,ecaluetreId,productsId,userId);
       return new Result<ProductsEvaluate>(info,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("examineEcaluetre")
    @ApiOperation("审核评论")
    public Result<Void> examineEcaluetre(@RequestBody ProductsEvaluate productsEvaluate){
       int success = productsService.examineEcaluetre(productsEvaluate);
       if (success > 0){
           return new Result<Void>(null,MsgCode.SUCCESS);
       }
       return new Result<Void>(null,MsgCode.FAIL);
    }

    @GetMapping("listBackProductsPagingById")
    @ApiOperation("分页查询后台商品列表")
    public Result<IPage<BackProductsVo>> listBackProductsPagingById(Integer page,Integer length,Integer shopsId){
       IPage<BackProductsVo> iPage = productsService.listBackProductsPagingById(page,length,shopsId);
       return new Result<IPage<BackProductsVo>>(iPage,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getBackProductsById")
    @ApiOperation("后台查询商品详情")
    public Result<ResqProductsVO> getBackProductsById(Integer id){
        ResqProductsVO info = productsService.getBackProductsById(id);
        return new Result<ResqProductsVO>(info,MsgCode.FIND_SUCCESS);
    }

    @PutMapping("updateProductsStock")
    @ApiOperation("修改商品库存")
    public Result<Void> updateProductsStock(@RequestBody ReqStocksVO reqStocksVO){
       int success = productsService.updateProductsStock(reqStocksVO);
       if (success > 0){
           return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @PutMapping("updateProiducts")
    @ApiOperation("后台修改商品信息")
    public Result<Void> updateProiducts(@RequestBody Products products){
       int success = productsService.updateProiducts(products);
       if (success > 0){
           return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @GetMapping("listProductsEcaluetre/{productsId}")
    @ApiOperation("查询商品评论列表")
    public Result<Map<String,Object>> listProductsEcaluetre(@PathVariable("productsId")Integer productsId){
        Map<String,Object> map = productsService.listProductsEcaluetre(productsId);
      return new Result<Map<String,Object>>(map,MsgCode.FIND_SUCCESS);
    }


}
