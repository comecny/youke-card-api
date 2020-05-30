package com.youke.service.impl;

import com.youke.vo.InsertProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Products;
import com.youke.dao.ProductsMapper;
import com.youke.service.ProductsService;

@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products> implements ProductsService{

    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public int insertProducts(InsertProductsVO productsVO) {

        return 0;
    }
}
