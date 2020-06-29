package com.youke.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.ProductsAttribute;
import com.youke.dao.ProductsAttributeMapper;
import com.youke.service.ProductsAttributeService;
@Service
public class ProductsAttributeServiceImpl extends ServiceImpl<ProductsAttributeMapper, ProductsAttribute> implements ProductsAttributeService{

    @Autowired
    private ProductsAttributeMapper attributeMapper;

    @Override
    public IPage<ProductsAttribute> getAttribute(Integer page, Integer length) {
       return attributeMapper.selectPage(new Page<ProductsAttribute>(page,length),null);
    }
}
