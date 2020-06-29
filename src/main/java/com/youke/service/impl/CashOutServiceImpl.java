package com.youke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.dao.UserMapper;
import com.youke.entity.User;
import com.youke.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.CashOutMapper;
import com.youke.entity.CashOut;
import com.youke.service.CashOutService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CashOutServiceImpl extends ServiceImpl<CashOutMapper, CashOut> implements CashOutService{

    @Autowired
    private CashOutMapper cashOutMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertCashOut(CashOut cashOut) {
        cashOut.setCreateTime(DateUtil.nowDate());
         return cashOutMapper.insert(cashOut);
    }

    @Override
    public IPage<CashOut> listBackCashOutByUserId(Integer page, Integer length) {
        return cashOutMapper.selectPage(new Page<CashOut>(page,length),null);
    }

    @Override
    @Transactional
    public int backExamineCashOut(CashOut cashOut) {

        if (cashOut.getType().equals("1")){
            cashOut.setCashOutStatus(2);
            return cashOutMapper.updateById(cashOut);
        }else {
            cashOut.setCashOutStatus(1);
            cashOutMapper.updateById(cashOut);

            BigDecimal pr = new BigDecimal(String.valueOf(cashOut.getPrice()));
            User user = userMapper.selectById(cashOut.getUserId());
            BigDecimal ba = new BigDecimal(user.getBalance());
            BigDecimal newBa = ba.subtract(pr);

           return userMapper.updateById(User.builder().id(cashOut.getUserId()).balance(newBa.toString()).build());
        }
    }
}
