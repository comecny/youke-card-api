package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-ProductsAttribute")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_attribute")
public class ProductsAttribute implements Serializable {
    /**
     * 商品属性表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="商品属性表")
    private Integer id;

    /**
     * 属性名
     */
    @TableField(value = "attribute_name")
    @ApiModelProperty(value="属性名")
    private String attributeName;

    /**
     * 创建时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 最后修改时间
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后修改时间")
    private String updateTime;

    /**
     * 表状态
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_ATTRIBUTE_NAME = "attribute_name";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}