package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.Products;
import com.youke.vo.BackProductsVo;
import com.youke.vo.ResqProductsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductsMapper extends BaseMapper<Products> {

    ResqProductsVO getProductsById(@Param("productsId")Integer productsId);

    IPage<Products> listProductsPaging(Page<Products> productsPage, Object o, @Param("shopsId")Integer shopsId);

    IPage<BackProductsVo> listBackProductsPagingById(Page<Object> objectPage, Object o,@Param("shopsId")Integer shopsId);
}