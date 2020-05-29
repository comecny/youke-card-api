package com.youke.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youke.entity.ProductsAttribute;
import com.youke.dao.ProductsAttributeMapper;
import com.youke.service.ProductsAttributeService;
@Service
public class ProductsAttributeServiceImpl extends ServiceImpl<ProductsAttributeMapper, ProductsAttribute> implements ProductsAttributeService{

}
