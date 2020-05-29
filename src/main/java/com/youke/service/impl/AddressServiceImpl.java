package com.youke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.youke.common.exception.db.UpdateException;
import com.youke.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.Address;
import com.youke.dao.AddressMapper;
import com.youke.service.AddressService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    @Transactional
    public int insertAddress(Address address) {
        //先判断地址是否存在地址
        Integer type = address.getType();
        Integer userId = address.getUserId();
        address.setCreateTime(DateUtil.nowDate());
        List<Address> addresses = addressMapper.selectList(new QueryWrapper<Address>()
                .setEntity(Address.builder().userId(userId).build()));
        if (addresses.isEmpty()) {
            //如果不存在地址，当前输入过来的又是非默认地址，则
            address.setType(0);
            return addressMapper.insert(address);
        } else {

            //如果存在地址，检测当前传传过来的地址类型
            if (type == 0) {
                Integer s;
                Address info = new Address();
                addresses.stream()
                        .filter(item -> item
                                .getType()
                                .compareTo(new Integer("0")) <= 0)
                        .forEach(str -> {
                            info.setId(str.getId());
                        });

                Address updateAddress = Address
                        .builder()
                        .id(info.getId())
                        .type(1)
                        .updateTime(DateUtil.nowDate())
                        .build();
                int isSuccess = addressMapper.updateById(updateAddress);
                if (isSuccess <= 0) throw new UpdateException("更改地址状态发生未知异常");

                return addressMapper.insert(address);
            } else {
                return addressMapper.insert(address);
            }
        }
    }


}
