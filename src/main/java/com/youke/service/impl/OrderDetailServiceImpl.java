package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.OrderDetailMapper;
import com.youke.entity.OrderDetail;
import com.youke.service.OrderDetailService;
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService{

}
