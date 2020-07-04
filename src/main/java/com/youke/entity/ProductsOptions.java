package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-ProductsOptions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_options")
public class ProductsOptions implements Serializable {
    /**
     * 商品属性选项表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="商品属性选项表")
    private Integer id;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value="商品id")
    private Integer productsId;

    /**
     * 选项名
     */
    @TableField(value = "option_name")
    @ApiModelProperty(value="选项名")
    private String optionName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后修改时间")
    private String updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;




    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_OPTION_NAME = "option_name";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}