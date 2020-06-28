package com.youke.service.impl;

import com.youke.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.CashOutMapper;
import com.youke.entity.CashOut;
import com.youke.service.CashOutService;
@Service
public class CashOutServiceImpl extends ServiceImpl<CashOutMapper, CashOut> implements CashOutService{

    @Autowired
    private CashOutMapper cashOutMapper;

    @Override
    public int insertCashOut(CashOut cashOut) {
        cashOut.setCreateTime(DateUtil.nowDate());
         return cashOutMapper.insert(cashOut);
    }
}
