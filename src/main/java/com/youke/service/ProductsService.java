package com.youke.service;

import com.youke.entity.Products;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.vo.InsertProductsVO;

public interface ProductsService {

    boolean insertProducts(InsertProductsVO productsVO);
}
