package com.youke.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Industry;
import com.youke.dao.IndustryMapper;
import com.youke.service.IndustryService;
@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry> implements IndustryService{

    @Autowired
    private IndustryMapper industryMapper;

    @Override
    public List<Industry> listIndusytyByShopsId(Integer id) {

       return industryMapper.listIndustryByShopsId(id);
    }
}
