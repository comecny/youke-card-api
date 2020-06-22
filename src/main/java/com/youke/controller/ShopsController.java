package com.youke.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Images;
import com.youke.entity.Shops;
import com.youke.service.ShopsService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("shops")
public class ShopsController {

    @Autowired
    private ShopsService shopsService;

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

    @GetMapping("listShopsPanging/{page}/{length}/{industryId}")
    @ApiOperation("小程序分页查询店铺")
    public Result<IPage<Map<String, Object>>> listShopsPanging(@PathVariable("page")Integer page ,
                                                               @PathVariable("length") Integer length,
                                                               @PathVariable("industryId")Integer industryId){
        return null;
    }

    @GetMapping("getShopsById/{id}")
    @ApiOperation("通过商铺id查询")
    public Result<Shops> getShopsById(@PathVariable("id")Integer id){
        Shops info = shopsService.getById(id);
        String iamges = info.getIamges();
        List<Images> images = JSON.parseArray(iamges, Images.class);
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




}
