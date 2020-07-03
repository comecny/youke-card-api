package com.youke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.BackgroudLogs;
import com.baomidou.mybatisplus.extension.service.IService;
public interface BackgroudLogsService extends IService<BackgroudLogs>{

    IPage<BackgroudLogs> listLogsPaging(Integer page, Integer length);
}
