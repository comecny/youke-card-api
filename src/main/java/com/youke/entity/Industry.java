package com.youke.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-Industry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "industry")
public class Industry implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_INDUSTRY_NAME = "industry_name";

    public static final String COL_PRICE = "price";

    /**
     * 行业表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="行业表id")
    private Integer id;

    /**
     * 行业名称
     */
    @TableField(value = "industry_name")
    @ApiModelProperty(value="行业名称")
    private String industryName;

    /**
     * 收费价格
     */
    @TableField(value = "price")
    @ApiModelProperty(value="收费价格")
    private BigDecimal price;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private String createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private String updateTime;

    /**
     * 表状态
     */
    private String status;
}