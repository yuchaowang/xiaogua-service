package com.xiaogua.comments.api.entity;

/**
 * @author wangyc
 * @date 2020-11-17 11:13
 */
public class PagingInfo {

    /**
     * 总页数
     */
    int totalPage;

    /**
     * 总条数
     */
    int totalCount;

    /**
     * 每页条数
     */
    int pageSize;

    /**
     * 当前页码
     */
    int pageNumber;

    /**
     * 当前页起始值
     */
    int index;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
