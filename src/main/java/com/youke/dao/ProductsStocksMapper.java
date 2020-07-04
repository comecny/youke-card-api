package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.ProductsOptions;
import com.youke.entity.ProductsOptionsRelStocks;
import com.youke.entity.ProductsStocks;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProductsStocksMapper extends BaseMapper<ProductsStocks> {

    ProductsStocks getProductsStocks(List<ProductsOptionsRelStocks> productsOptionsRelStocks);

    List<ProductsOptions> getOptionsListBystockId(@Param("stockId")Integer stocksId);
}