package com.youke.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class BackgroundMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     *后台菜单表id
     */
    private Integer id;

    /**
     *父id
     */
    private Integer pid;

    /**
     *索引
     */
    private String index;

    /**
     *标题
     */
    private String title;

    /**
     *图标
     */
    private String icon;

    /**
     *跳转路径
     */
    private String path;

    /**
     *权重
     */
    private Integer weight;

    private List<BackgroundMenu> childList;

}
