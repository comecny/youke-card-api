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
import java.util.List;

@ApiModel(value="com-youke-entity-Shops")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops")
public class Shops implements Serializable {
    /**
     * 店铺表id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value="店铺表id")
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    @ApiModelProperty(value="用户id")
    private Integer userId;

    /**
     * 公司名
     */
    @TableField(value = "company")
    @ApiModelProperty(value="公司名")
    private String company;

    /**
     * 联系人
     */
    @TableField(value = "contacts")
    @ApiModelProperty(value="联系人")
    private String contacts;

    /**
     * 联系人电话
     */
    @TableField(value = "contacts_phone")
    @ApiModelProperty(value="联系人电话")
    private String contactsPhone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ApiModelProperty(value="邮箱")
    private String email;

    /**
     * 公司地址
     */
    @TableField(value = "address_area")
    @ApiModelProperty(value="公司地址")
    private String addressArea;

    /**
     * 地址详情
     */
    @TableField(value = "address_detail")
    @ApiModelProperty(value="地址详情")
    private String addressDetail;

    /**
     * 营业执照
     */
    @TableField(value = "business_license")
    @ApiModelProperty(value="营业执照")
    private String businessLicense;

    /**
     * 资质、专利介绍
     */
    @TableField(value = "introduction")
    @ApiModelProperty(value="资质、专利介绍")
    private String introduction;

    /**
     * 资质和专利图片
     */
    @TableField(value = "iamges")
    @ApiModelProperty(value="资质和专利图片")
    private String iamges;

    /**
     * 认证状态 0是待认证 1是认证成功 2是认证失败
     */
    @TableField(value = "auth_status")
    @ApiModelProperty(value="认证状态 0是待认证 1是认证成功 2是认证失败")
    private Integer authStatus;

    /**
     * 会费状态 0是未交 1是已交
     */
    @TableField(value = "member_fee_status")
    @ApiModelProperty(value="会费状态 0是未交 1是已交")
    private Integer memberFeeStatus;

    /**
     * 店铺状态 0 为停店 1为正常运营
     */
    @TableField(value = "shops_status")
    @ApiModelProperty(value="店铺状态 0 为停店 1为正常运营")
    private Integer shopsStatus;

    /**
     * 店铺评分
     */
    @TableField(value = "shops_score")
    @ApiModelProperty(value="店铺评分")
    private Integer shopsScore;

    /**
     * 权重
     */
    @TableField(value = "weight")
    @ApiModelProperty(value="权重")
    private Double weight;

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

    @TableField(exist = false)
    private List<Images> imagesList;

    @TableField(exist = false)
    private List<Industry> industryList;

    @TableField(exist = false)
    private User user;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_USER_ID = "user_id";

    public static final String COL_COMPANY = "company";

    public static final String COL_CONTACTS = "contacts";

    public static final String COL_CONTACTS_PHONE = "contacts_phone";

    public static final String COL_EMAIL = "email";

    public static final String COL_ADDRESS_AREA = "address_area";

    public static final String COL_ADDRESS_DETAIL = "address_detail";

    public static final String COL_BUSINESS_LICENSE = "business_license";

    public static final String COL_INTRODUCTION = "introduction";

    public static final String COL_IAMGES = "iamges";

    public static final String COL_AUTH_STATUS = "auth_status";

    public static final String COL_MEMBER_FEE_STATUS = "member_fee_status";

    public static final String COL_SHOPS_STATUS = "shops_status";

    public static final String COL_SHOPS_SCORE = "shops_score";

    public static final String COL_WEIGHT = "weight";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static final String COL_STATUS = "status";
}