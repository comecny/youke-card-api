package com.youke.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youke.entity.ShoppingCats;
import com.youke.vo.ResqOptionsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoppingCatsMapper extends BaseMapper<ShoppingCats> {

    List<ResqOptionsVO> listOption(@Param("productsStocksId")Integer productsStocksId);

    int deleteBatchShoppingCats(@Param("list")List<ShoppingCats> list);

}