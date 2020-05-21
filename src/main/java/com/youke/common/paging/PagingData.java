package com.youke.common.paging;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class PagingData<T> {
    @ApiModelProperty(value = "数据")
    private List<T> data;
    @ApiModelProperty(example = "数据总量")
    private int recordsTotal;

    public PagingData() {
    }

    public PagingData(List<T> data, int recordsTotal) {
        this.data = data;
        this.recordsTotal = recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(int recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    @Override
    public String toString() {
        return "PagingData{" +
                "data=" + data +
                ", recordsTotal=" + recordsTotal +
                '}';
    }
}
