package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.vo.ReqOrderStatusVO;
import com.youke.vo.ReqOrderVo;

import java.util.List;
import java.util.Map;

public interface OrderService extends IService<Order>{
        public ReqOrderVo insert(Order order);

        public Order getOrderById(Integer id);

        public List<Order> getOrderByUserId(Integer userId,Integer orderStatus );

    IPage<Order> listOrderPagingByShopsId(Integer shopsId, String orderNo, Integer page, Integer length);

    Order getOrderDetailById(Integer orderId);

    int updateOrderStatus(ReqOrderStatusVO orderStatusVO);

    Map createOrder(Order order);
}
