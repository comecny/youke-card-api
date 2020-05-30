package com.youke.service;

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
}
