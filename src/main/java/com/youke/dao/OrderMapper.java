package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.entity.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper extends BaseMapper<Order> {
    IPage<Order> listOrderPagingByShopsId(Page<Order> orderPage, @Param("shopsId")Integer shopsId,@Param("orderNo")String orderNo);
}