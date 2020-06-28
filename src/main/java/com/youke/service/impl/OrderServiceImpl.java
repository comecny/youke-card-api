package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.dao.ProductsStocksMapper;
import com.youke.entity.Coupon;
import com.youke.entity.OrderDetail;
import com.youke.entity.ProductsStocks;
import com.youke.service.CouponService;
import com.youke.service.ProductsOptionsRelStocksService;
import com.youke.utils.OrderUUIDtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Order;
import com.youke.dao.OrderMapper;
import com.youke.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService{
    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductsStocksMapper productsStocksMapper;

    @Override
    @Transactional
    public synchronized boolean insert(Order order) {
        //生成订单id
        order.setOrderNo(OrderUUIDtil.getOrderIdByUUId());
        System.out.println(order.getOrderNo());
        //先查找有无合适的优惠券
        List<Coupon> list = couponService.list(new QueryWrapper<Coupon>()
                .setEntity(Coupon.builder().status("0").build()));
        for (Coupon coupon:list) {
            if (((coupon.getUseStart()).compareTo(order.getTotalPrice()))!= -1){
                    order.setCouponprice(order.getTotalPrice().subtract(coupon.getUseEnd()));
                    order.setCouponId(coupon.getId());
                    order.setCouponFlag(0);
            }else {
                order.setCouponprice(order.getTotalPrice());
                order.setCouponFlag(1);
            }
        }
        //根据订单id去生成订单详情的数据
        for (OrderDetail orderDetail:order.getOrderDetails()
             ) {
            orderDetail.setOrderId(order.getOrderNo());
            ProductsStocks productsStocks = productsStocksMapper.selectById(orderDetail.getStocksId());
            Integer num = Integer.valueOf(productsStocks.getStocks());
            productsStocks.setStocks((String.valueOf(num- orderDetail.getNumber())));
            productsStocksMapper.updateById(productsStocks);
        }
        return true;
    }
}
