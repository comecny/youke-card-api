package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.ShopsFeeOrder;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ShopsFeeOrderMapper extends BaseMapper<ShopsFeeOrder> {
    IPage<ShopsFeeOrder> listShopsFeeOrderpangingByShopsId(Page<ShopsFeeOrder> shopsFeeOrderPage,
                                                                 @Param("shopsId")Integer shopsId);
}