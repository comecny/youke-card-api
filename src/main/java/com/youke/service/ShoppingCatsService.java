package com.youke.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.youke.entity.ShoppingCats;
import com.baomidou.mybatisplus.extension.service.IService;
import com.youke.vo.ReqCatsIdVO;

public interface ShoppingCatsService extends IService<ShoppingCats>{

    IPage<ShoppingCats> listShoppingCats(Integer page, Integer length);

    int deleteBatchShoppingCats(ReqCatsIdVO catsIdVO);
}
