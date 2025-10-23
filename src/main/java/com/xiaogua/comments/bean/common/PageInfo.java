package com.xiaogua.comments.bean.common;

/**
 * 分页信息
 *
 * @author wangyc
 * @date 2020-11-16 20:12
 */
public class PageInfo {

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
}
