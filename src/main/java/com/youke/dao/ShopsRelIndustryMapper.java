package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.ShopsRelIndustry;

import java.util.List;

public interface ShopsRelIndustryMapper extends BaseMapper<ShopsRelIndustry> {

    int insertBatchShopsRelIndustry(List<ShopsRelIndustry> shopsRelIndustryList);

}