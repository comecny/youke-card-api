package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Maintain;
import com.baomidou.mybatisplus.extension.service.IService;
public interface MaintainService extends IService<Maintain>{

    IPage<Maintain> listBackMaintain(Integer page, Integer length,Integer userId);
}
