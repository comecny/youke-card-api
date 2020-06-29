package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.ProductsAttribute;
import com.baomidou.mybatisplus.extension.service.IService;
public interface ProductsAttributeService extends IService<ProductsAttribute>{

    IPage<ProductsAttribute> getAttribute(Integer page, Integer length);
}
