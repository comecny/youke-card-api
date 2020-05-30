package com.youke.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.ProductsAttributeRelOptionsMapper;
import com.youke.entity.ProductsAttributeRelOptions;
import org.springframework.stereotype.Service;

@Service
public class ProductsAttributeRelOptionsServiceImpl extends ServiceImpl<ProductsAttributeRelOptionsMapper,
        ProductsAttributeRelOptions> implements ProductsAttributeRelOptionsService {
}
