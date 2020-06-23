package com.youke.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.core.JsonParser;
import com.youke.common.exception.db.InsertException;
import com.youke.dao.ShopsFeeOrderMapper;
import com.youke.dao.ShopsRelIndustryMapper;
import com.youke.entity.Images;
import com.youke.entity.ShopsFeeOrder;
import com.youke.entity.ShopsRelIndustry;
import com.youke.utils.DateUtil;
import com.youke.utils.OrderUUIDtil;
import com.youke.vo.BackgroudUserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Shops;
import com.youke.dao.ShopsMapper;
import com.youke.service.ShopsService;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.spring.web.json.Json;

@Service
public class ShopsServiceImpl extends ServiceImpl<ShopsMapper, Shops> implements ShopsService{

    @Autowired
    private ShopsFeeOrderMapper shopsFeeOrderMapper;

    @Autowired
    private ShopsRelIndustryMapper shopsRelIndustryMapper;

    @Autowired
    private ShopsMapper shopsMapper;

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
}
