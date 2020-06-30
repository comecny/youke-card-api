package com.youke.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.youke.common.exception.db.InsertException;
import com.youke.dao.*;
import com.youke.entity.*;
import com.youke.utils.DateUtil;
import com.youke.utils.OrderUUIDtil;
import com.youke.vo.ReqShopsScoreVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.service.ShopsService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShopsServiceImpl extends ServiceImpl<ShopsMapper, Shops> implements ShopsService{

    @Autowired
    private ShopsFeeOrderMapper shopsFeeOrderMapper;

    @Autowired
    private ShopsRelIndustryMapper shopsRelIndustryMapper;

    @Autowired
    private ShopsMapper shopsMapper;

    @Autowired
    private ShopsScoreMapper shopsScoreMapper;

    @Autowired
    private ShopsFansMapper shopsFansMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    @Transactional
    public synchronized int createShopsOrder(ShopsFeeOrder shopsFeeOrder) {

        List<ShopsRelIndustry> shopsRelIndustryList = shopsFeeOrder.getShopsRelIndustryList();
        shopsFeeOrder.setCreateTime(DateUtil.nowDate());
        shopsFeeOrder.setShopsFeeNo(OrderUUIDtil.getOrderIdByUUId());
        shopsFeeOrder.setIndustryNameList(JSON.toJSONString(shopsRelIndustryList));
        int success = shopsFeeOrderMapper.insert(shopsFeeOrder);
        if (success <= 0) throw new InsertException("新增商铺订单异常");

        return shopsRelIndustryMapper.insertBatchShopsRelIndustry(shopsRelIndustryList);

    }

    @Override
    public IPage<ShopsFeeOrder> listShopsFeeOrderpangingByShopsId(Integer page, Integer length, Integer shopsId) {

        IPage<ShopsFeeOrder> mapIPage =
                shopsFeeOrderMapper.listShopsFeeOrderpangingByShopsId(new Page<ShopsFeeOrder>(page, length),shopsId);
        List<ShopsFeeOrder> records = mapIPage.getRecords();

        for (ShopsFeeOrder record : records) {
            String industryNameList = record.getIndustryNameList();
            List<ShopsRelIndustry> shopsRelIndustries = JSON.parseArray(industryNameList, ShopsRelIndustry.class);
            record.setShopsRelIndustryList(shopsRelIndustries);
        }
        return mapIPage;
    }

    @Override
    public IPage<Shops> listShopsPaging(Integer page, Integer length, Integer industryId) {
       IPage<Shops> ipage = shopsMapper.listShopsPaging(new Page<Object>(page,length),industryId);
       return ipage;
    }

    @Override
    @Transactional
    public int createShopsScore(ReqShopsScoreVO shopsScoreVO) {

        //1.先判断判断当前用户是否已经评分过了.如果评分过，则不能进行评分
        QueryWrapper<ShopsFans> shopsFansQueryWrapper =
                new QueryWrapper<ShopsFans>()
                        .setEntity(ShopsFans.builder().shopsId(shopsScoreVO.getShopsId()).userId(shopsScoreVO.getUserId()).build());
        Integer count = shopsFansMapper.selectCount(shopsFansQueryWrapper);
        if (count > 0) return -10;

        //2.先将评分表数据取到
        List<ShopsScore> shopsScores = shopsScoreMapper.selectList(null);

        //3.取到当前店铺的信息，从里面取到分数
        Shops shops = shopsMapper.selectById(shopsScoreVO.getShopsId());
        Integer shopsScore = shops.getShopsScore();
        Integer score = shopsScoreVO.getScore();

        //4.得到一个新的分数
       Integer newScore = score+shopsScore;

       //5.将新的分数插入到店铺信息当中
        int shopsSuccess = shopsMapper.updateById(Shops
                        .builder()
                        .id(shopsScoreVO.getShopsId())
                        .shopsScore(newScore)
                        .updateTime(DateUtil.nowDate())
                        .build());

        //6.将此条信息加入粉丝表
        int success = shopsFansMapper.insert(ShopsFans
                .builder().userId(shopsScoreVO.getUserId())
                .shopsId(shopsScoreVO.getShopsId())
                .createTime(DateUtil.nowDate())
                .fansScore(shopsScoreVO.getScore())
                .build());

        return success;
    }

    @Override
    public IPage<Shops> listBackShops(Integer page, Integer length) {
        Page<Shops> shopsPage =
                shopsMapper.selectPage(new Page<Shops>(page, length), new QueryWrapper<Shops>().setEntity(Shops.builder().status("0").build()));
        List<Shops> records = shopsPage.getRecords();
        for (Shops record : records) {
            User user = userMapper.getUser(record.getUserId());
            String avatarUrl = user.getAvatarUrl();
            record.setLogo(avatarUrl);
           List<Industry> list = shopsMapper.listIndustry(record.getId());
            String s = JSON.toJSONString(list);
            record.setIndustrys(s);
            record.setIndustryList(list);
        }
        return shopsPage;
    }

    @Override
    public Shops getBackShopsInfo(Integer shopsId, Integer userId) {
        Shops shops = shopsMapper.selectById(shopsId);
        User user = userMapper.getUser(userId);
        shops.setUser(user);
        List<Industry> list = shopsMapper.listIndustry(shopsId);
        shops.setIndustryList(list);
        String s = JSON.toJSONString(list);
        shops.setIndustrys(s);
        return shops;
    }
}
