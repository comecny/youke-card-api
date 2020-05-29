package com.youke.service;

import com.youke.entity.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface AddressService extends IService<Address>{

    int insertAddress(Address address);

    List<Address> listAddressByUserId(Integer userId);

    Address getAddressById(Integer id);
}
