package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Products;
import com.youke.entity.ProductsEvaluate;
import com.youke.entity.ProductsStocks;
import com.youke.vo.ReqOptionsVO;
import com.youke.vo.ReqProductsVO;
import com.youke.vo.ReqStocksVO;
import com.youke.vo.ResqProductsVO;

public interface ProductsService {

    boolean insertProducts(ReqProductsVO productsVO);

    boolean insertProductsStocks(ReqStocksVO stocksVO);

    ResqProductsVO getProductsById(Integer productsId);

    ProductsStocks getProductsStocks(ReqOptionsVO optionsVO);

    IPage<Products> listProductsPaging(Integer page, Integer length, Integer shopsId);

    int deleteProducts(Products products);

    int insertProductsEvaluate(ProductsEvaluate productsEvaluate);

}
