package com.youke.service;

import com.youke.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.vo.ReqOrderVo;

public interface OrderService extends IService<Order>{
        public ReqOrderVo insert(Order order);

}
