package com.youke.service;

import com.youke.entity.Refund;
import com.baomidou.mybatisplus.extension.service.IService;
public interface RefundService extends IService<Refund>{

    int insertRefund(Refund refund);
}
