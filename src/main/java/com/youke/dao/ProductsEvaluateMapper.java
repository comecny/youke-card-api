package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.ProductsEvaluate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductsEvaluateMapper extends BaseMapper<ProductsEvaluate> {
}