package com.youke.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.common.result.MsgCode;
import com.youke.common.result.Result;
import com.youke.entity.Order;
import com.youke.service.OrderService;
import com.youke.utils.DateUtil;
import com.youke.vo.ReqOrderStatusVO;
import com.youke.vo.ReqOrderVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @PostMapping("createOrder")
    @ApiOperation("创建订单")
    public Result<Map> createOrder(@RequestBody Order order){
       Map map = orderService.createOrder(order);
       return new Result<Map>(map,MsgCode.CREATE_SUCCESS);
    }

    @GetMapping("getOrderById/{id}")
    @ApiOperation("查询订单详情")
    public Result<Order> getOrderById(@PathVariable("id")Integer id){
        Order info = orderService.getOrderById(id);
        return new Result<Order>(info,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getOrderByUserId")
    @ApiOperation("根据用户id查询订单")
    public Result<List<Order>> getOrderByUserId(Integer userId,Integer orderStatus){
        List<Order> info = orderService.getOrderByUserId(userId,orderStatus);
        return new Result<List<Order>>(info,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("listOrderPagingByShopsId")
    @ApiOperation("后台分页查询订单列表")
    public Result<IPage<Order>> listOrderPagingByShopsId(Integer shopsId,String orderNo,Integer page,Integer length){
       IPage<Order> iPage = orderService.listOrderPagingByShopsId(shopsId,orderNo,page,length);
       return new Result<IPage<Order>>(iPage,MsgCode.FIND_SUCCESS);
    }

    @GetMapping("getOrderDetailById")
    @ApiOperation("后台查订单详情")
    public Result<Order> getOrderDetailById(Integer orderId){
      Order order = orderService.getOrderDetailById(orderId);
      return new Result<Order>(order,MsgCode.FIND_SUCCESS);
    }

    @PutMapping("updateOrderStatus")
    @ApiOperation("修改订单状态")
    public Result<Void> updateOrderStatus(@RequestBody ReqOrderStatusVO orderStatusVO){
       int success = orderService.updateOrderStatus(orderStatusVO);
       if (success > 0){
           return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
       }
       return new Result<Void>(null,MsgCode.UPDATE_FAIL);
    }

    @PutMapping("updateUserOrderById")
    @ApiOperation("用户修改订单信息")//可以在创建订单时候修改地址和用于确认订单之类的
    public Result<Void> updateUserOrderById(@RequestBody Order order){
        order.setUpdateTime(DateUtil.nowDate());
        boolean update = orderService.updateById(order);
        if (update){
            return new Result<Void>(null,MsgCode.UPDATE_SUCCESS);
        }
        return new Result<Void>(null,MsgCode.UPDATE_FAIL);

    }

}
