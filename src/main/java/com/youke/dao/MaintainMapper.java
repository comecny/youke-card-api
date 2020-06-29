package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.Maintain;
import org.apache.ibatis.annotations.Param;

public interface MaintainMapper extends BaseMapper<Maintain> {

    IPage<Maintain> listBackMaintain(Page<Maintain> maintainPage, Object o,@Param("userId")Integer userId);
}