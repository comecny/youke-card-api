package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.CashOut;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CashOutService extends IService<CashOut>{

    int insertCashOut(CashOut cashOut);

    IPage<CashOut> listBackCashOutByUserId(Integer page, Integer length);

    int backExamineCashOut(CashOut cashOut);
}
