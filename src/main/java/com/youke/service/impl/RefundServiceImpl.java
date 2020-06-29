package com.youke.service.impl;

import com.youke.dao.OrderDetailMapper;
import com.youke.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Refund;
import com.youke.dao.RefundMapper;
import com.youke.service.RefundService;

@Service
public class RefundServiceImpl extends ServiceImpl<RefundMapper, Refund> implements RefundService{

    @Autowired
    private RefundMapper refundMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public int insertRefund(Refund refund) {

        //先判断订单是否发货


        return 0;
    }
}
