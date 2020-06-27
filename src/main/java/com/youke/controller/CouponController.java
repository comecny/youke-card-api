package com.youke.controller;


import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Coupon;
import com.youke.service.CouponService;
import com.youke.utils.DateUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
