package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.dao.ProductsMapper;
import com.youke.dao.ProductsStocksMapper;
import com.youke.dao.ShopsMapper;
import com.youke.vo.ReqCatsIdVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.ShoppingCatsMapper;
import com.youke.entity.ShoppingCats;
import com.youke.service.ShoppingCatsService;

@Service
public class ShoppingCatsServiceImpl extends ServiceImpl<ShoppingCatsMapper, ShoppingCats> implements ShoppingCatsService{

    @Autowired
    private ShoppingCatsMapper shoppingCatsMapper;

    @Autowired
    private ProductsStocksMapper productsStocksMapper;

    @Autowired
    private ProductsMapper productsMapper;

    @Autowired
    private ShopsMapper shopsMapper;

    @Override
    public IPage<ShoppingCats> listShoppingCats(Integer page, Integer length) {

        Page<ShoppingCats> shoppingCatsPage = shoppingCatsMapper.selectPage(new Page<ShoppingCats>(page, length),
                new QueryWrapper<ShoppingCats>().setEntity(ShoppingCats.builder().status("0").build()));

        List<ShoppingCats> records = shoppingCatsPage.getRecords();
        for (ShoppingCats record : records) {
            Integer productsStocksId = record.getProductsStocksId();
            //通过库存id找到库存对应的选项数组名
          record.setResqOptionsVO(shoppingCatsMapper.listOption(productsStocksId));
          record.setProductsStocks(productsStocksMapper.selectById(record.getProductsStocksId()));
          record.setProducts(productsMapper.selectById(record.getProductsId()));
          record.setShops(shopsMapper.selectById(record.getShopsId()));
        }

        return shoppingCatsPage;
    }

    @Override
    public int deleteBatchShoppingCats(ReqCatsIdVO catsIdVO) {
        return shoppingCatsMapper.deleteBatchShoppingCats(catsIdVO.getReqShoppingCatsIdList());
    }
}
