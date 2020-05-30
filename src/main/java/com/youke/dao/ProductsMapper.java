package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.Products;
import com.youke.vo.ResqProductsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

@Mapper
@Repository
public interface ProductsMapper extends BaseMapper<Products> {

    ResqProductsVO getProductsById(@Param("productsId")Integer productsId);
}