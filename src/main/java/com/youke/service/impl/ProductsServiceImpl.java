package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.common.exception.db.InsertException;
import com.youke.common.exception.db.UpdateException;
import com.youke.dao.*;
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

import java.util.*;
import java.util.concurrent.*;


import com.youke.service.ProductsService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductsServiceImpl  implements ProductsService {

    final static Logger logger = LogManager.getLogger(ProductsServiceImpl.class.getName());

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

    @Autowired
    private ProductsEvaluateMapper evaluateMapper;

    @Autowired
    private ShopsMapper shopsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public synchronized Map<String,Object> insertProducts(ReqProductsVO productsVO) {
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
        products.setProductsStatus(0);
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

            Integer attributeId = attributeRelOptionsVO.getAttributeId();
            List<ProductsOptions> optionsList = attributeRelOptionsVO.getOptionsList();
            for (ProductsOptions productsOptions : optionsList) {
                productsOptions.setCreateTime(DateUtil.nowDate());
                productsOptions.setProductsId(productsId);
                optionsMapper.insert(productsOptions);
                Integer optionsId = productsOptions.getId();
                logger.info("获取选项id：{"+optionsId+"} 已完成");

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
        }
        boolean success = relOptionsService.saveBatch(productsAttributeRelOptions);
        if (success) logger.info("新增关系表已完成");
        Map<String, Object> map = new HashMap<>();
        map.put("productsId",productsId);
        return map;
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

    @Override
    public IPage<Products> listProductsPaging(Integer page, Integer length, Integer shopsId) {
      return   productsMapper.listProductsPaging(new Page<Products>(page,length),
                null,shopsId);
    }

    @Override
    public int deleteProducts(Products products) {
        Products info = Products.builder().status("1").id(products.getId()).build();
       return productsMapper.updateById(info);
    }

    @Override
    public int insertProductsEvaluate(ProductsEvaluate productsEvaluate) {
        productsEvaluate.setCreateTime(DateUtil.nowDate());
       return evaluateMapper.insert(productsEvaluate);
    }

    @Override
    public ProductsEvaluate getProductsEcaluetre(Integer shopsId, Integer ecaluetreId,Integer productsId,Integer userId)
            throws InterruptedException,
            ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        CountDownLatch countDownLatch = new CountDownLatch(3);

        Future<Shops> shopsFuture = executorService.submit(() -> {
            try {
                Shops shops = shopsMapper.selectById(shopsId);
                logger.info("shops:" + shops);
                return shops;
            } finally {
                countDownLatch.countDown();
            }

        });

        Future<ProductsEvaluate> ProductsEvaluateFuture = executorService.submit(() -> {
            try {
                ProductsEvaluate productsEvaluate = evaluateMapper.selectById(ecaluetreId);
                logger.info("productsEvaluate:" + productsEvaluate);
                return productsEvaluate;
            } finally {
                countDownLatch.countDown();
            }

        });

        Future<Products> ProductsFuture  = executorService.submit(() -> {
            try {

                Products products = productsMapper.selectById(productsId);
                logger.info("products:" + products);
                return products;
            } finally {
                countDownLatch.countDown();
            }

        });

        Future<User> userFuture  = executorService.submit(() -> {
            try {

                User user = userMapper.selectById(userId);
                logger.info("user:" + user);
                return user;
            } finally {
                countDownLatch.countDown();
            }

        });

        countDownLatch.await();

        ProductsEvaluate productsEvaluate = ProductsEvaluateFuture.get();
        productsEvaluate.setShops(shopsFuture.get());
        productsEvaluate.setProducts(ProductsFuture.get());
        productsEvaluate.setUser(userFuture.get());
        return productsEvaluate;
    }

    @Override
    public int examineEcaluetre(ProductsEvaluate productsEvaluate) {
        productsEvaluate.setUpdateTime(DateUtil.nowDate());
       return evaluateMapper.updateById(productsEvaluate);
    }

    @Override
    public IPage<BackProductsVo> listBackProductsPagingById(Integer page, Integer length, Integer shopsId) {

        List<ReqStocksIntegerVO> listStocks = new ArrayList<ReqStocksIntegerVO>();
        IPage<BackProductsVo> iPage = productsMapper.listBackProductsPagingById(new Page<Object>(page,length),null,shopsId);
        List<BackProductsVo> records = iPage.getRecords();
        for (BackProductsVo record : records) {
            ArrayList<ProductsStocks> productsStocks = record.getProductsStocks();
            for (ProductsStocks productsStock : productsStocks) {
                Integer stocks = Integer.valueOf(productsStock.getStocks());
                ReqStocksIntegerVO reqStocksIntegerVO = new ReqStocksIntegerVO();
                reqStocksIntegerVO.setStocks(stocks);
                listStocks.add(reqStocksIntegerVO);

            }

            int sum = listStocks.stream().mapToInt(ReqStocksIntegerVO::getStocks).sum();
            record.setTotalStocks(sum);
        }
        return iPage;
    }

    @Override
    public ResqProductsVO getBackProductsById(Integer id) {
        ResqProductsVO  map = productsMapper.getProductsById(id);
        //查一个stocK列表
        List<ProductsStocks> productsStocks = stocksMapper.selectList(new QueryWrapper<ProductsStocks>()
                .setEntity(ProductsStocks.builder().productsId(id).build()));
        for (ProductsStocks productsStock : productsStocks) {
            Integer stocksId = productsStock.getId();
          List<ProductsOptions>  OPtions = stocksMapper.getOptionsListBystockId(stocksId);
          productsStock.setOptionsList(OPtions);
        }

        map.setProductsStocks(productsStocks);
        return map;
    }

    @Override
    public int updateProductsStock(ReqStocksVO reqStocksVO) {
        List<StocksListVO> listVos = reqStocksVO.getListVos();
        for (StocksListVO listVo : listVos) {
            ProductsStocks productsStocks = listVo.getProductsStocks();
            productsStocks.setUpdateTime(DateUtil.nowDate());
            stocksMapper.updateById(productsStocks);
        }

        return 1;
    }

    @Override
    public int updateProiducts(Products products) {

        return  0;
    }

    @Override
    public Map<String,Object>  listProductsEcaluetre(Integer productsId) {
        QueryWrapper<ProductsEvaluate> productsEvaluateQueryWrapper =
                new QueryWrapper<>(ProductsEvaluate.builder().productsId(productsId).pass(2).build());
        List<ProductsEvaluate> productsEvaluates = evaluateMapper.selectList(productsEvaluateQueryWrapper);
        for (ProductsEvaluate productsEvaluate : productsEvaluates) {
            Integer userId = productsEvaluate.getUserId();
            User user = userMapper.getUser(userId);
            productsEvaluate.setUser(user);
        }
        Integer count = evaluateMapper.selectCount(productsEvaluateQueryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("evaluateList",productsEvaluates);
        map.put("count",count);

        return map;
    }

    @Override
    @Transactional
    public int updateProducts(ReqProductsVO productsVO) {
        Products products = productsVO.getProducts();
        List<AttributeRelOptionsVO> attributeRelOptionsVOS = productsVO.getAttributeRelOptionsVOS();
        List<ProductsResources> productsResources = productsVO.getProductsResources();

        //获得主图
        String url = null;
        for (ProductsResources productsResource : productsResources) {
            if (productsResource.getType() == 0 && productsResource.getMajorType() == 1){
                url = productsResource.getUrl();break;
            }
        }

        //修改商品主体
        products.setUpdateTime(DateUtil.nowDate());
        products.setProductsStatus(0);
        products.setMajorPicture(url);
        int isProducts = productsMapper.updateById(products);
        if (isProducts <= 0) throw new UpdateException("修改商品异常");
        Integer productsId = products.getId();
        logger.info("获取商品id：{"+productsId+"} 已经完成");
        //增资源表
        for (ProductsResources productsResource : productsResources) {
            productsResource.setProductsId(productsId);
            productsResource.setCreateTime(DateUtil.nowDate());
        }
        resourcesService.updateBatchById(productsResources);
        logger.info("更新商品表完成");

        //只能改
        List<ProductsAttributeRelOptions> productsAttributeRelOptions = new ArrayList<>();
        for (AttributeRelOptionsVO attributeRelOptionsVO : attributeRelOptionsVOS) {

            Integer attributeId = attributeRelOptionsVO.getAttributeId();
            List<ProductsOptions> optionsList = attributeRelOptionsVO.getOptionsList();
            for (ProductsOptions productsOptions : optionsList) {
                productsOptions.setCreateTime(DateUtil.nowDate());
                productsOptions.setProductsId(productsId);
                optionsMapper.insert(productsOptions);
                Integer optionsId = productsOptions.getId();
                logger.info("获取选项id：{"+optionsId+"} 已完成");

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
        }
        boolean success = relOptionsService.saveBatch(productsAttributeRelOptions);
        if (success) logger.info("新增关系表已完成");

        return 0;
    }
}
