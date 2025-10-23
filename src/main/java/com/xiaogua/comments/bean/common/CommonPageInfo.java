package com.xiaogua.comments.bean.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用分页类
 *
 * @author wangyc
 * @date 2020-11-19 19:53
 */
@ApiModel(value = "通用分页类")
public class CommonPageInfo {
    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小")
    int pageSize;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码")
    int pageNumber;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    String sort;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
