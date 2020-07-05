package com.youke.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Images;
import com.youke.entity.Shops;
import com.youke.entity.ShopsFeeOrder;
import com.youke.entity.User;
import com.youke.service.ShopsService;
import com.youke.service.UserService;
import com.youke.utils.DateUtil;
import com.youke.vo.ReqShopsScoreVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("shops")
public class ShopsController {

    @Autowired
    private ShopsService shopsService;

    @Autowired
    private UserService userService;

    @PostMapping("insertShops")
    @ApiOperation("新增店铺")
    public Result<Void> insertShops(@RequestBody Shops shops){
        List<Images> imagesList = shops.getImagesList();
        String images = JSON.toJSONString(imagesList);
        shops.setCreateTime(DateUtil.nowDate());
        shops.setIamges(images);
        boolean success = shopsService.save(shops);
        if (success){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping(value = "listShopsPanging")
    @ApiOperation("小程序分页查询店铺")
    public Result<IPage<Shops>> listShopsPaging(Integer page,Integer length,Integer industryId){

       IPage<Shops> listShopsPaging = shopsService.listShopsPaging(page,length,industryId);
        return new Result<IPage<Shops>>(listShopsPaging,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getShopsById/{id}")
    @ApiOperation("通过商铺id查询")
    public Result<Shops> getShopsById(@PathVariable("id")Integer id){
        Shops info = shopsService.getById(id);
        String iamges = info.getIamges();
        List<Images> images = JSON.parseArray(iamges, Images.class);
        User user = userService.getUser(info.getUserId());
        info.setUser(user);
        info.setImagesList(images);
        return new Result<Shops>(info,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("shopsAuthAndUpdate")
    @ApiOperation("商铺认证/与修改")
    public Result<Void> shopsAuth(@RequestBody Shops shops){
        shops.setUpdateTime(DateUtil.nowDate());
        boolean success = shopsService.updateById(shops);
        if (success){
            return new Result<Void>(null,MsgCode.SUCCESS);
        }
        return new Result<Void>(null,MsgCode.FAIL);
    }

    @PostMapping("createShopsOrder")
    @ApiOperation("创建商铺会费订单")
    public Result<Void> createShopsOrder(@RequestBody ShopsFeeOrder shopsFeeOrder){
       int success = shopsService.createShopsOrder(shopsFeeOrder);
       if (success > 0){
           return new Result<Void>(null,MsgCode.CREATE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.CREATE_FAIL);
    }

    @GetMapping("listShopsFeeOrderpangingByShopsId")
    @ApiOperation("查询会费订单列表")
    public Result<IPage<ShopsFeeOrder>> listShopsFeeOrderpagingByShopsId(Integer page,
                                                                         Integer length,
                                                                         Integer shopsId){

        IPage<ShopsFeeOrder> paging = shopsService.listShopsFeeOrderpangingByShopsId(page,length,shopsId);
        return new Result<IPage<ShopsFeeOrder>>(paging,MsgCode.FIND_SUCCESS);
    }

    @PostMapping("createShopsScore")
    @ApiOperation("创建店铺评分")
    public Result<Void> createShopsScore(@RequestBody ReqShopsScoreVO shopsScoreVO){
       int success = shopsService.createShopsScore(shopsScoreVO);
       if (success > 0){
           return new Result<Void>(null,MsgCode.IINSERT_SUCCESS);
       }
       if (success == -10){
           return new Result<Void>(null,MsgCode.SCORE_FAIL);
       }
       return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("listBackShops")
    @ApiOperation("后台分页查询店铺列表")
    public Result<IPage<Shops>> listBackShops(Integer page,Integer length){

       IPage<Shops> paging = shopsService.listBackShops(page,length);
       return new Result<IPage<Shops>>(paging,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getBackShopsInfo")
    @ApiOperation("后台查询店铺详情")
    public Result<Shops> getBackShopsInfo(Integer shopsId, Integer userId){
        Shops shops = shopsService.getBackShopsInfo(shopsId,userId);
        return new Result<Shops>(shops,MsgCode.FIND_SUCCESS);
    }

    @PutMapping("updateBackShops")
    @ApiOperation("后台修改店铺信息")
    public Result<Void> updateBackShops(@RequestBody Shops shops){
        shops.setUpdateTime(DateUtil.nowDate());
        boolean success = shopsService.updateById(shops);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @GetMapping("getshopsIByUserId/{userId}")
    @ApiOperation("查询当前用户是否有店铺信息")
    public Result<Shops> getshopsIByUserId(@PathVariable("userId")Integer userId){
        Shops info = shopsService.getOne(new QueryWrapper<Shops>(Shops.builder().userId(userId).build()));
        return new Result<Shops>(info,MsgCode.FIND_SUCCESS);
    }
}
