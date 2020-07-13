package com.youke.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="com-youke-entity-Products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products")
public class Products implements Serializable {

    /**
     * 商品表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="商品表id")
    private Integer id;

    /**
     * 商品编号
     */
    @TableField(value = "products_no")
    @ApiModelProperty(value="商品编号")
    private String productsNo;

    /**
     * 店铺id
     */
    @TableField(value = "shops_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopsId;

    /**
     * 商品名
     */
    @TableField(value = "products_name")
    @ApiModelProperty(value="商品名")
    private String productsName;

    /**
     * 商品简介
     */
    @TableField(value = "introduction")
    @ApiModelProperty(value="商品简介")
    private String introduction;

    /**
     * 商品主图
     */
    @TableField(value = "major_picture")
    @ApiModelProperty(value="商品主图")
    private String majorPicture;

    /**
     * 价格区间(最低)
     */
    @TableField(value = "min_picture")
    @ApiModelProperty(value="价格区间(最低)")
    private BigDecimal minPicture;

    /**
     * 价格区间(最高)
     */
    @TableField(value = "max_picture")
    @ApiModelProperty(value="价格区间(最高)")
    private BigDecimal maxPicture;

    /**
     * 划线价格
     */
    @TableField(value = "scribe_picture")
    @ApiModelProperty(value="划线价格")
    private BigDecimal scribePicture;

    /**
     * 发货地
     */
    @TableField(value = "delive_place")
    @ApiModelProperty(value="发货地")
    private String delivePlace;

    /**
     * 0是为上架，1是已上架
     */
    @TableField(value = "products_status")
    @ApiModelProperty(value="0是为上架，1是已上架")
    private Integer productsStatus;

    /**
     * 商品内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value="商品内容")
    private Integer content;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后更新时间")
    private String updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_PRODUCTS_NO = "products_no";

    public static final String COL_SHOPS_ID = "shops_id";

    public static final String COL_PRODUCTS_NAME = "products_name";

    public static final String COL_INTRODUCTION = "introduction";

    public static final String COL_MAJOR_PICTURE = "major_picture";

    public static final String COL_MIN_PICTURE = "min_picture";

    public static final String COL_MAX_PICTURE = "max_picture";

    public static final String COL_SCRIBE_PICTURE = "scribe_picture";

    public static final String COL_DELIVE_PLACE = "delive_place";

    public static final String COL_PRODUCTS_STATUS = "products_status";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}