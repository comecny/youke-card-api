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

@ApiModel(value="com-youke-entity-ProductsResources")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_resources")
public class ProductsResources implements Serializable {
    /**
     * 商品资源表id  图片和视频
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="商品资源表id  图片和视频")
    private Integer id;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value="商品id")
    private Integer productsId;

    /**
     * 资源链接
     */
    @TableField(value = "url")
    @ApiModelProperty(value="资源链接")
    private String url;

    /**
     * 资源类型  0是图片 1是视频
     */
    @TableField(value = "type")
    @ApiModelProperty(value="资源类型  0是图片 1是视频")
    private Integer type;


    @TableField(value = "major_type")
    @ApiModelProperty(value="主图标识 0是普通 1是主图")
    private Integer majorType;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_URL = "url";

    public static final String COL_TYPE = "type";

    public static final String COL_MAJOR_TYPE = "major_type";

    public static final String COL_CREATE_TIME = "create_time";
}