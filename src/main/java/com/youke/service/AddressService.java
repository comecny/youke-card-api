package com.youke.service;

import com.youke.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;
public interface AddressService extends IService<Address>{

    int insertAddress(Address address);
}
