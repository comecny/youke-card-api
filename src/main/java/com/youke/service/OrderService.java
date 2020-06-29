package com.youke.service;

import com.youke.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.vo.ReqOrderVo;

import java.util.List;

public interface OrderService extends IService<Order>{
        public ReqOrderVo insert(Order order);

        public Order getOrderById(Integer id);

        public List<Order> getOrderByUserId(Integer userId);

}
