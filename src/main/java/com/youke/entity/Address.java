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

@ApiModel(value="com-youke-entity-Address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "address")
public class Address implements Serializable {
    /**
     * 用户收货地址表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="用户收货地址表id")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 0是默认，1是普通地址
     */
    @TableField(value = "type")
    @ApiModelProperty(value="0是默认，1是普通地址")
    private Integer type;

    /**
     * 地址区域
     */
    @TableField(value = "area")
    @ApiModelProperty(value="地址区域")
    private String area;

    /**
     * 地址详情
     */
    @TableField(value = "address_detail")
    @ApiModelProperty(value="地址详情")
    private String addressDetail;

    /**
     * 收货人
     */
    @TableField(value = "receiver")
    @ApiModelProperty(value="收货人")
    private String receiver;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ApiModelProperty(value="手机号")
    private String phone;

    @TableField(value = "numbers")
    @ApiModelProperty(value="门牌号")
    private String numbers;

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

    public static final String COL_USER_ID = "user_id";

    public static final String COL_TYPE = "type";

    public static final String COL_AREA = "area";

    public static final String COL_ADDRESS_DETAIL = "address_detail";

    public static final String COL_RECEIVER = "receiver";

    public static final String COL_PHONE = "phone";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}