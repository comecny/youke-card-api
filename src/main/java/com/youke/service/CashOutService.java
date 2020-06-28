package com.youke.service;

import com.youke.entity.CashOut;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CashOutService extends IService<CashOut>{

    int insertCashOut(CashOut cashOut);
}
