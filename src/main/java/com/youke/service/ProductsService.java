package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Products;
import com.youke.entity.ProductsEvaluate;
import com.youke.entity.ProductsStocks;
import com.youke.vo.*;

import java.util.concurrent.ExecutionException;

public interface ProductsService {

    boolean insertProducts(ReqProductsVO productsVO);

    boolean insertProductsStocks(ReqStocksVO stocksVO);

    ResqProductsVO getProductsById(Integer productsId);

    ProductsStocks getProductsStocks(ReqOptionsVO optionsVO);

    IPage<Products> listProductsPaging(Integer page, Integer length, Integer shopsId);

    int deleteProducts(Products products);

    int insertProductsEvaluate(ProductsEvaluate productsEvaluate);

    ProductsEvaluate getProductsEcaluetre(Integer shopsId, Integer ecaluetreId,Integer productsId,Integer userId)
            throws InterruptedException, ExecutionException;

    int examineEcaluetre(ProductsEvaluate productsEvaluate);

    IPage<BackProductsVo> listBackProductsPagingById(Integer page, Integer length, Integer shopsId);

    ResqProductsVO getBackProductsById(Integer id);
}
