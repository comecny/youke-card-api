package com.youke.service.impl;

import com.youke.common.exception.db.InsertException;
import com.youke.dao.ProductsOptionsMapper;
import com.youke.dao.ProductsRelAttributeMapper;
import com.youke.entity.*;

import com.youke.service.ProductsAttributeRelOptionsService;
import com.youke.service.ProductsResourcesService;
import com.youke.utils.DateUtil;
import com.youke.vo.AttributeRelOptionsVO;
import com.youke.vo.InsertProductsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.ProductsMapper;
import com.youke.service.ProductsService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductsServiceImpl  implements ProductsService {

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ProductsResourcesService resourcesService;

    @Autowired
    private ProductsOptionsMapper optionsMapper;

    @Autowired
    private ProductsAttributeRelOptionsService relOptionsService;

    @Autowired
    private ProductsRelAttributeMapper productsRelAttributeMapper;

    @Override
    @Transactional
    public boolean insertProducts(InsertProductsVO productsVO) {
        Products products = productsVO.getProducts();
        List<AttributeRelOptionsVO> attributeRelOptionsVOS = productsVO.getAttributeRelOptionsVOS();
        List<ProductsResources> productsResources = productsVO.getProductsResources();

        String url = null;
        for (ProductsResources productsResource : productsResources) {
            if (productsResource.getType() == 0 && productsResource.getMajorType() == 1){
                 url = productsResource.getUrl();break;
            }
        }

        //先增加商品主体
        products.setCreateTime(DateUtil.nowDate());
        products.setMajorPicture(url);
        int isProducts = productsMapper.insert(products);
        if (isProducts <= 0) throw new InsertException("新增商品表异常");
        Integer productsId = products.getId();

        //增资源表
        for (ProductsResources productsResource : productsResources) {
            productsResource.setProductsId(productsId);
            productsResource.setCreateTime(DateUtil.nowDate());
        }
        resourcesService.saveBatch(productsResources);

        //插入关系表
        List<ProductsAttributeRelOptions> productsAttributeRelOptions = new ArrayList<>();
        for (AttributeRelOptionsVO attributeRelOptionsVO : attributeRelOptionsVOS) {
            ProductsOptions options = attributeRelOptionsVO.getOptions();
            options.setCreateTime(DateUtil.nowDate());
            options.setProductsId(productsId);
            optionsMapper.insert(options);
            Integer optionsId = options.getId();
            Integer attributeId = attributeRelOptionsVO.getAttributeId();
            productsRelAttribute productsRelAttribute = new productsRelAttribute();
            productsRelAttribute.setAttributeId(attributeId);
            productsRelAttribute.setProductsId(productsId);
            productsRelAttributeMapper.insert(productsRelAttribute);
            ProductsAttributeRelOptions info = new ProductsAttributeRelOptions();
            info.setAttributeId(attributeId);
            info.setOptionsId(optionsId);
            info.setProductsId(productsId);
            productsAttributeRelOptions.add(info);
        }
        boolean success = relOptionsService.saveBatch(productsAttributeRelOptions);
        return success;
    }
}
