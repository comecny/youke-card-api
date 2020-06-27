package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.dao.CouponMapper;
import com.youke.entity.Coupon;
import com.youke.service.CouponService;
@Service
public class CouponServiceImpl extends ServiceImpl<CouponMapper, Coupon> implements CouponService{

}
