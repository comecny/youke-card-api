package com.youke.service.impl;

import com.youke.common.exception.db.InsertException;
import com.youke.dao.ProductsOptionsMapper;
import com.youke.dao.ProductsRelAttributeMapper;
import com.youke.dao.ProductsStocksMapper;
import com.youke.entity.*;

import com.youke.service.ProductsAttributeRelOptionsService;
import com.youke.service.ProductsOptionsRelStocksService;
import com.youke.service.ProductsResourcesService;
import com.youke.utils.DateUtil;
import com.youke.vo.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.youke.dao.ProductsMapper;
import com.youke.service.ProductsService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductsServiceImpl  implements ProductsService {

    static Logger logger = LogManager.getLogger(ProductsServiceImpl.class.getName());

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

    @Autowired
    private ProductsStocksMapper stocksMapper;

    @Autowired
    private ProductsOptionsRelStocksService optionsRelStocksService;

    @Override
    @Transactional
    public synchronized boolean insertProducts(ReqProductsVO productsVO) {
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
        logger.info("获取商品id：{"+productsId+"} 已经完成");
        //增资源表
        for (ProductsResources productsResource : productsResources) {
            productsResource.setProductsId(productsId);
            productsResource.setCreateTime(DateUtil.nowDate());
        }
        resourcesService.saveBatch(productsResources);
        logger.info("新增商品资源表已完成");
        //插入关系表
        List<ProductsAttributeRelOptions> productsAttributeRelOptions = new ArrayList<>();
        for (AttributeRelOptionsVO attributeRelOptionsVO : attributeRelOptionsVOS) {
            ProductsOptions options = attributeRelOptionsVO.getOptions();
            options.setCreateTime(DateUtil.nowDate());
            options.setProductsId(productsId);
            optionsMapper.insert(options);
            Integer optionsId = options.getId();
            logger.info("获取选项id：{"+optionsId+"} 已完成");
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
        if (success) logger.info("新增关系表已完成");
        return success;
    }

    @Override
    @Transactional
    public synchronized boolean insertProductsStocks(ReqStocksVO stocksVO) {
        List<StocksListVO> listVos = stocksVO.getListVos();
        for (StocksListVO listVo : listVos) {
            ProductsStocks productsStocks = listVo.getProductsStocks();
            productsStocks.setCreateTime(DateUtil.nowDate());
            stocksMapper.insert(productsStocks);
            Integer stocksId = productsStocks.getId();
            List<ProductsOptionsRelStocks> optionsRelStocksList = new ArrayList<>();
            List<Integer> optionsId = listVo.getOptionsId();
            for (Integer id : optionsId) {
                ProductsOptionsRelStocks optionsRelStocks = new ProductsOptionsRelStocks();
                optionsRelStocks.setOptionId(id);
                optionsRelStocks.setStockId(stocksId);
                optionsRelStocksList.add(optionsRelStocks);
            }
            //插入多条关系
            optionsRelStocksService.saveBatch(optionsRelStocksList);
        }
        return true;
    }

    @Override
    public ResqProductsVO getProductsById(Integer productsId) {
        ResqProductsVO  map = productsMapper.getProductsById(productsId);
        return map;
    }

    @Override
    public ProductsStocks getProductsStocks(ReqOptionsVO optionsVO) {
        List<ProductsOptions> optionsList = optionsVO.getOptionsList();
        List<ProductsOptionsRelStocks> productsOptionsRelStocks = new ArrayList<>();
        for (ProductsOptions productsOptions : optionsList) {
            ProductsOptionsRelStocks optionsRelStocks = new ProductsOptionsRelStocks();
            optionsRelStocks.setOptionId(productsOptions.getId());
            productsOptionsRelStocks.add(optionsRelStocks);
        }

       return stocksMapper.getProductsStocks(productsOptionsRelStocks);
    }
}
