package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.CashOutMapper;
import com.youke.entity.CashOut;
import com.youke.service.CashOutService;
@Service
public class CashOutServiceImpl extends ServiceImpl<CashOutMapper, CashOut> implements CashOutService{

    @Override
    public int insertCashOut(CashOut cashOut) {

        return 0;
    }
}
