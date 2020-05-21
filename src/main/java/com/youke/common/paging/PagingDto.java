package com.youke.common.paging;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;


public abstract class PagingDto {
    @ApiModelProperty(example = "第几页")
    @Min(value = 1, message = "page必须为正整数")
    private int page;
    @ApiModelProperty(example = "每页容量")
    @Min(value = 1, message = "length必须为正整数")
    private int length;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "PagingDto{" +
                "page=" + page +
                ", length=" + length +
                '}';
    }
}
