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

@ApiModel(value="com-youke-entity-Banner")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "banner")
public class Banner implements Serializable {
    /**
     * banner表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="banner表id")
    private Integer id;

    /**
     * 图片地址
     */
    @TableField(value = "url")
    @ApiModelProperty(value="图片地址")
    private String url;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    @ApiModelProperty(value="备注")
    private String remarks;

    /**
     * 权重
     */
    @TableField(value = "weight")
    @ApiModelProperty(value="权重")
    private Double weight;

    /**
     * 1为图片 2为视频
     */
    @TableField(value = "type")
    @ApiModelProperty(value="1为图片 2为视频")
    private Integer type;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value="创建时间")
    private String createTime;

    /**
     * 表状态
     */
    @TableField(value = "status")
    @ApiModelProperty(value="表状态")
    private String status;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value="最后修改时间")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_URL = "url";

    public static final String COL_REMARKS = "remarks";

    public static final String COL_WEIGHT = "weight";

    public static final String COL_TYPE = "type";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_STATUS = "status";

    public static final String COL_UPDATE_TIME = "update_time";
}