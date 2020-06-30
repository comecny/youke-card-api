package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.Industry;
import com.youke.entity.Shops;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ShopsMapper extends BaseMapper<Shops> {

    IPage<Shops> listShopsPaging(Page<Object> objectPage, @Param("industryId")Integer industryId);

    List<Industry> listIndustry(@Param("id")Integer id);
}