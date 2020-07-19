package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.Shops;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.entity.ShopsFeeOrder;
import com.youke.vo.ReqShopsScoreVO;

import java.util.Map;

public interface ShopsService extends IService<Shops>{

    Map createShopsOrder(ShopsFeeOrder shopsFeeOrder);

    IPage<ShopsFeeOrder> listShopsFeeOrderpangingByShopsId(Integer page, Integer length, Integer shopsId);

    IPage<Shops> listShopsPaging(Integer page, Integer length, Integer industryId);

    int createShopsScore(ReqShopsScoreVO shopsScoreVO);

    IPage<Shops> listBackShops(Integer page, Integer length);

    Shops getBackShopsInfo(Integer shopsId, Integer userId);
}
