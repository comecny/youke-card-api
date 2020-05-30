package com.youke.vo;

import lombok.Data;
import java.util.List;

import java.io.Serializable;

@Data
public class ReqStocksVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<StocksListVO> listVos;

}
