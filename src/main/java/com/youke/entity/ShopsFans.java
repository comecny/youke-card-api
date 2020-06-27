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

@ApiModel(value="com-youke-entity-ShopsFans")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops_fans")
public class ShopsFans implements Serializable {
    /**
     * 粉丝表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="粉丝表id")
    private Integer id;

    /**
     * 店铺id
     */
    @TableField(value = "shops_id")
    @ApiModelProperty(value="店铺id")
    private Integer shopsId;

    /**
     * 评分
     */
    @TableField(value = "fans_score")
    @ApiModelProperty(value="评分")
    private Integer fansScore;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SHOPS_ID = "shops_id";

    public static final String COL_FANS_SCORE = "fans_score";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_USER_ID = "user_id";
}