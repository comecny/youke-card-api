package com.youke.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.ShoppingCats;
import com.youke.service.ShoppingCatsService;
import com.youke.utils.DateUtil;
import com.youke.vo.ReqCatsIdVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("shoppingCats")
public class ShoppingCatsController {

    @Autowired
    private ShoppingCatsService shoppingCatsService;

    @PostMapping("insertShoppingCats")
    @ApiOperation("新增购物车")
    public Result<Void> createShoppingCats(@RequestBody ShoppingCats shoppingCats){
        shoppingCats.setCreateTime(DateUtil.nowDate());
        boolean save = shoppingCatsService.save(shoppingCats);
        if (save){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listShoppingCats")
    @ApiOperation("分页查询购物车列表")
    public Result<IPage<ShoppingCats>> listShoppingCats(Integer page, Integer length){
        IPage<ShoppingCats> paging = shoppingCatsService.listShoppingCats(page,length);
        return new Result<IPage<ShoppingCats>>(paging,MsgCode.FIND_SUCCESS);
    }

    @DeleteMapping("deleteBatchShoppingCats")
    @ApiOperation("删除购物车商品(多选删除)")
    public Result<Void> deleteBatchShoppingCats(@RequestBody ReqCatsIdVO catsIdVO){
       int success = shoppingCatsService.deleteBatchShoppingCats(catsIdVO);
       if (success > 0) {
           return new Result<Void>(null,MsgCode.DELETE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.DELETE_FAIL);
    }

    @PutMapping("updateShoppingCats")
    @ApiOperation("修改购物车内容")
    public Result<Void> updateShoppingCats(@RequestBody ShoppingCats shoppingCats){
        shoppingCats.setUpdateTime(DateUtil.nowDate());
        boolean success = shoppingCatsService.updateById(shoppingCats);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

}
