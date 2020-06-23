package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Shops;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.entity.ShopsFeeOrder;

import java.util.Map;

public interface ShopsService extends IService<Shops>{

    int createShopsOrder(ShopsFeeOrder shopsFeeOrder);

    IPage<ShopsFeeOrder> listShopsFeeOrderpangingByShopsId(Integer page, Integer length, Integer shopsId);

    IPage<Shops> listShopsPaging(Integer page, Integer length, Integer industryId);
}
