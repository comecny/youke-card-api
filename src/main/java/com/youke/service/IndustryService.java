package com.youke.service;

import com.youke.entity.Industry;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IndustryService extends IService<Industry>{

    List<Industry> listIndusytyByShopsId(Integer id);
}
