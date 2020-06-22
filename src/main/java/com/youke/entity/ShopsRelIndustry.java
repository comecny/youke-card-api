package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-ShopsRelIndustry")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops_rel_industry")
public class ShopsRelIndustry implements Serializable {
    /**
     * 行业id
     */
    @TableField(value = "industry_id")
    @ApiModelProperty(value="行业id")
    private Integer industryId;

    /**
     * 店铺id
     */
    @TableField(value = "shops_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopsId;

    private static final long serialVersionUID = 1L;

    public static final String COL_INDUSTRY_ID = "industry_id";

    public static final String COL_SHOPS_ID = "shops_id";
}