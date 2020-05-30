package com.youke.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.ProductsResourcesMapper;
import com.youke.entity.ProductsResources;
import com.youke.service.ProductsResourcesService;
import org.springframework.stereotype.Service;

@Service
public class ProductsResourcesServiceImpl extends ServiceImpl<ProductsResourcesMapper,
        ProductsResources> implements ProductsResourcesService {
}
