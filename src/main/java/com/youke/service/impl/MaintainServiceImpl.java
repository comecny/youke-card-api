package com.youke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Maintain;
import com.youke.dao.MaintainMapper;
import com.youke.service.impl.MaintainService;

@Service
public class MaintainServiceImpl extends ServiceImpl<MaintainMapper, Maintain> implements MaintainService{

    @Autowired
    private MaintainMapper maintainMapper;

    @Override
    public IPage<Maintain> listBackMaintain(Integer page, Integer length,Integer userId) {
        return maintainMapper.listBackMaintain(new Page<Maintain>(page,length),null,userId);
    }
}
