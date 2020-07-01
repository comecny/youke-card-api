package com.youke.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Coupon;
import com.youke.service.CouponService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.soap.Detail;
import java.util.List;

@RestController("coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("insertCoupon")
    @ApiOperation("新增优惠卷")
    public Result<Void> insertCoupon(@RequestBody Coupon coupon){
        coupon.setCreateTime(DateUtil.nowDate());
        boolean save = couponService.save(coupon);
        if (save){
            return new Result<Void>(null, MsgCode.IINSERT_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.INSERT_FAIL);
    }

    @GetMapping("selectCoupon")
    @ApiOperation("查询优惠券列表")
    public Result<List<Coupon>> selectCoupon(){
        List<Coupon> list = couponService.list(new QueryWrapper<Coupon>()
                .setEntity(Coupon.builder().status("0").build()));
        return new Result<List<Coupon>>(list,MsgCode.SUCCESS);
    }

    @GetMapping("selectCouponById/{id}")
    @ApiOperation("查询优惠券信息")
    public Result<Coupon> selectCouponById(@PathVariable("id") Integer id){
        Coupon info = couponService.getById(id);
        return new Result<Coupon>(info,MsgCode.FIND_SUCCESS);
    }

    @PutMapping("updateConponById")
    @ApiOperation("修改优惠券信息")
    public Result<Void> updateConponById(@RequestBody Coupon coupon){
        boolean success = couponService.updateById(coupon);
        if (success){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @DeleteMapping("deleteConpon")
    @ApiOperation("删除优惠卷")
    public Result<Void> deleteConpon(@RequestBody Coupon coupon){
        return null;
    }


}
