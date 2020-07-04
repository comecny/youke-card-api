package com.youke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.BackgroudLogsMapper;
import com.youke.entity.BackgroudLogs;
import com.youke.service.BackgroudLogsService;

@Service
public class BackgroudLogsServiceImpl extends ServiceImpl<BackgroudLogsMapper, BackgroudLogs> implements BackgroudLogsService{

    @Autowired
    private BackgroudLogsMapper logsMapper;

    @Override
    public IPage<BackgroudLogs> listLogsPaging(Integer page, Integer length) {
      return   logsMapper.selectPage(new Page<BackgroudLogs>(page,length),null);
    }
}
