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

@ApiModel(value = "com-youke-entity-ProductsEvaluate")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "products_evaluate")
public class ProductsEvaluate implements Serializable {
    /**
     * 商品评论表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品评论表id")
    private Integer id;

    /**
     * 商铺id
     */
    @TableField(value = "shops_id")
    @ApiModelProperty(value = "商铺id")
    private Integer shopsId;

    /**
     * 商品id
     */
    @TableField(value = "products_id")
    @ApiModelProperty(value = "商品id")
    private Integer productsId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    /**
     * 审核人id
     */
    @TableField(value = "backgroud_user_id")
    @ApiModelProperty(value = "审核人id")
    private Integer backgroudUserId;

    /**
     * 评价内容
     */
    @TableField(value = "content")
    @ApiModelProperty(value = "评价内容")
    private String content;

    /**
     * 审核 0是为通过，1是为通过
     */
    @TableField(value = "pass")
    @ApiModelProperty(value = "审核 0是为通过，1是为通过")
    private Integer pass;

    /**
     * 审核类型 0是人工 1是自动
     */
    @TableField(value = "pass_type")
    @ApiModelProperty(value = "审核类型 0是人工 1是自动")
    private Integer passType;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "最后更新时间")
    private String updateTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value = "表状态")
    private String status;

    @TableField(exist = false)
    private Shops shops;

    @TableField(exist = false)
    private Products products;

    @TableField(exist = false)
    private User user;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SHOPS_ID = "shops_id";

    public static final String COL_PRODUCTS_ID = "products_id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_BACKGROUD_USER_ID = "backgroud_user_id";

    public static final String COL_CONTENT = "content";

    public static final String COL_PASS = "pass";

    public static final String COL_PASS_TYPE = "pass_type";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";

    public static ProductsEvaluateBuilder builder() {
        return new ProductsEvaluateBuilder();
    }
}