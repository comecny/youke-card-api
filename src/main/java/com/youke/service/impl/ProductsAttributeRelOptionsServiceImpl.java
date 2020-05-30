package com.youke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.ProductsAttributeRelOptionsMapper;
import com.youke.entity.ProductsAttributeRelOptions;
import com.youke.service.ProductsAttributeRelOptionsService;
import org.springframework.stereotype.Service;

@Service
public class ProductsAttributeRelOptionsServiceImpl extends ServiceImpl<ProductsAttributeRelOptionsMapper,
        ProductsAttributeRelOptions> implements ProductsAttributeRelOptionsService {
}
