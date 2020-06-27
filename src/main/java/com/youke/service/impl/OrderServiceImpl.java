package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Order;
import com.youke.dao.OrderMapper;
import com.youke.service.OrderService;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{

}
