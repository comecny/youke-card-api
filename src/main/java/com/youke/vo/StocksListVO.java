package com.youke.vo;

import com.youke.entity.ProductsOptionsRelStocks;
import com.youke.entity.ProductsStocks;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class StocksListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProductsStocks productsStocks;

    private List<Integer> optionsId;
}
