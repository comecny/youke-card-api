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

@ApiModel(value = "com-youke-entity-ShopsScore")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "shops_score")
public class ShopsScore implements Serializable {
    /**
     * 评分表
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "评分表")
    private Integer id;

    /**
     * 分数起始
     */
    @TableField(value = "score_start")
    @ApiModelProperty(value = "分数起始")
    private String scoreStart;

    /**
     * 分数结束
     */
    @TableField(value = "score_end")
    @ApiModelProperty(value = "分数结束")
    private String scoreEnd;

    /**
     * 等级描述
     */
    @TableField(value = "grade_describe")
    @ApiModelProperty(value = "等级描述")
    private String gradeDescribe;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    @ApiModelProperty(value = "创建时间")
    private String createTime;

    /**
     * 最后修改时间
     */
    @TableField(value = "update_time")
    @ApiModelProperty(value = "最后修改时间")
    private String updateTime;

    private static final long serialVersionUID = 1L;

    public static final String COL_ID = "id";

    public static final String COL_SCORE_START = "score_start";

    public static final String COL_SCORE_END = "score_end";

    public static final String COL_GRADE_DESCRIBE = "grade_describe";

    public static final String COL_CREATE_TIME = "create_time";

    public static final String COL_UPDATE_TIME = "update_time";

    public static ShopsScoreBuilder builder() {
        return new ShopsScoreBuilder();
    }
}