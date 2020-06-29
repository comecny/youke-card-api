package com.youke.controller;

import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Address;
import com.youke.entity.Order;
import com.youke.service.OrderService;
import com.youke.utils.DateUtil;
import com.youke.vo.ReqOptionsVO;
import com.youke.vo.ReqOrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("getOrderById/{id}")
    @ApiOperation("查询订单详情")
    public Result<Order> getOrderById(@PathVariable("id")Integer id){
        Order info = orderService.getOrderById(id);
        return new Result<Order>(info,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getOrderByUserId/{userId}")
    @ApiOperation("根据用户id查询订单")
    public Result<List<Order>> getOrderByUserId(@PathVariable("userId")Integer userId){
        List<Order> info = orderService.getOrderByUserId(userId);
        return new Result<List<Order>>(info,MsgCode.FIND_SUCCESS);
    }
}
