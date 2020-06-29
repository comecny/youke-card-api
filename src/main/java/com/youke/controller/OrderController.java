package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Order;
import com.youke.service.OrderService;
import com.youke.utils.DateUtil;
import com.youke.vo.ReqOptionsVO;
import com.youke.vo.ReqOrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("insertOrder")
    @ApiOperation("新增订单")
    public Result<ReqOrderVo> insertOrder(@RequestBody Order order){
        order.setCreateTime(DateUtil.nowDate());
        order.setUpdateTime(DateUtil.nowDate());
        ReqOrderVo success = orderService.insert(order);
        if (success != null){
            return new Result<ReqOrderVo>(success,MsgCode.IINSERT_SUCCESS);
        }else
        return new Result<ReqOrderVo>(null,MsgCode.INSERT_FAIL);
    }
}
