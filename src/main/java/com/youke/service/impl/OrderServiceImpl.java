package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.dao.OrderDetailMapper;
import com.youke.dao.ProductsStocksMapper;
import com.youke.entity.Coupon;
import com.youke.entity.OrderDetail;
import com.youke.entity.ProductsStocks;
import com.youke.service.CouponService;
import com.youke.service.ProductsOptionsRelStocksService;
import com.youke.utils.DateUtil;
import com.youke.utils.OrderUUIDtil;
import com.youke.vo.ReqOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Order;
import com.youke.dao.OrderMapper;
import com.youke.service.OrderService;
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    @Autowired
    private CouponService couponService;

    @Autowired
    private ProductsStocksMapper productsStocksMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public ReqOrderVo insert(Order order) {
        //生成订单id
        order.setOrderNo(OrderUUIDtil.getOrderIdByUUId());
        //先查找有无合适的优惠券
        List<Coupon> list = couponService.list(new QueryWrapper<Coupon>()
                .setEntity(Coupon.builder().status("0").build()));
        for (Coupon coupon : list) {
            if (((coupon.getUseStart()).compareTo(order.getTotalPrice())) != 1) {
                order.setCouponprice(order.getTotalPrice().subtract(coupon.getUseEnd()));
                order.setCouponId(coupon.getId());
                order.setCouponFlag(0);
            } else {
                order.setCouponprice(order.getTotalPrice());
                order.setCouponFlag(1);
            }
        }
        //根据订单id去生成订单详情的数据
        for (OrderDetail orderDetail : order.getOrderDetails()
        ) {
            orderDetail.setOrderId(order.getId());
            orderDetail.setCreateTime(DateUtil.nowDate());
            orderDetail.setUpdateTime(DateUtil.nowDate());
            ProductsStocks productsStocks = productsStocksMapper.selectById(orderDetail.getStocksId());
            Integer num = Integer.valueOf(productsStocks.getStocks());
            productsStocks.setStocks((String.valueOf(num - orderDetail.getNumber())));
            if (Integer.valueOf(productsStocks.getStocks()) <= 0) {
                return null;
            }
            productsStocks.setCreateTime(DateUtil.nowDate());
            productsStocks.setUpdateTime(DateUtil.nowDate());
            productsStocksMapper.updateById(productsStocks);
            orderDetailMapper.insert(orderDetail);
        }
        orderMapper.insert(order);
        ReqOrderVo reqOrderVo = new ReqOrderVo();
        reqOrderVo.setOrderId(order.getId());
        return reqOrderVo;

    }
}
