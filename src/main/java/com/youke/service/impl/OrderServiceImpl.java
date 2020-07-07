package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.dao.OrderDetailMapper;
import com.youke.dao.ProductsStocksMapper;
import com.youke.dao.ShopsMapper;
import com.youke.entity.*;
import com.youke.service.CouponService;
import com.youke.service.ProductsOptionsRelStocksService;
import com.youke.utils.DateUtil;
import com.youke.utils.OrderUUIDtil;
import com.youke.vo.ReqOrderStatusVO;
import com.youke.vo.ReqOrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.OrderMapper;
import com.youke.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ShopsMapper shopsMapper;

    @Override
    @Transactional
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
        orderMapper.insert(order);
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
        ReqOrderVo reqOrderVo = new ReqOrderVo();
        reqOrderVo.setOrderId(order.getId());
        return reqOrderVo;

    }

    @Override
    public Order getOrderById(Integer id) {
        Order order = orderMapper.selectById(id);
        List<OrderDetail> list = orderDetailMapper.selectList(new QueryWrapper<OrderDetail>()
                .setEntity(OrderDetail.builder().orderId(id).build()));
        order.setOrderDetails(list);
        return order;
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        List<Order> list =orderMapper.selectList(new QueryWrapper<Order>()
                .setEntity(Order.builder().userId(userId).build()));
        for (Order listOrder:list) {
            List<OrderDetail> listOrderDetail = orderDetailMapper.selectList(new QueryWrapper<OrderDetail>()
                    .setEntity(OrderDetail.builder().orderId(listOrder.getId()).build()));
            listOrder.setOrderDetails(listOrderDetail);
        }
        return list;
    }

    @Override
    public IPage<Order> listOrderPagingByShopsId(Integer shopsId, String orderNo, Integer page, Integer length) {
        Shops shops = shopsMapper.selectById(shopsId);
        Page<Order> orderPage = new Page<>(page, length);
        IPage<Order> iPage = orderMapper.listOrderPagingByShopsId(orderPage, shopsId, orderNo);
        List<Order> records = iPage.getRecords();
        for (Order record : records) {
            record.setShops(shops);
        }
        return iPage;
    }

    @Override
    public Order getOrderDetailById(Integer orderId) {
        Order order = orderMapper.selectById(orderId);
        List<OrderDetail> orderDetails = orderDetailMapper.selectList(new QueryWrapper<OrderDetail>(OrderDetail.builder().orderId(orderId).build()));
        order.setOrderDetails(orderDetails);
        return order;
    }

    @Override
    public int updateOrderStatus(ReqOrderStatusVO orderStatusVO) {
        Order order = new Order();
        order.setId(orderStatusVO.getOrderId());
        order.setOrderStatus(Integer.valueOf(orderStatusVO.getOperate()));
        order.setSendTime(orderStatusVO.getSendTime());
        order.setUpdateTime(DateUtil.nowDate());
        return orderMapper.updateById(order);
    }
}
