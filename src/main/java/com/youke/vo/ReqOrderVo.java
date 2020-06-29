package com.youke.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.youke.entity.Order;
import com.youke.entity.OrderDetail;
import com.youke.entity.ProductsStocks;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ReqOrderVo {
    private static final long serialVersionUID = 1L;

    /**
     * 订单主体
     */
    @ApiModelProperty(value="订单编号")
    private Integer orderId;

}
