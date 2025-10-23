package com.xiaogua.comments.utils;

import com.xiaogua.comments.api.entity.PagingInfo;

/**
 * 分页工具类
 *
 * @author: wangyc
 * @date: 2020-11-17 11:19:38
 */
public class PagingUtil {

    /**
     * 默认页码
     */
    private static final Integer DEFAULT_PAGENUMBER = 1;

    /**
     * 默认页数
     */
    public static final Integer DEFAULT_PAGESIZE = 10;

    /**
     * 获取分页信息
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param totalCount 总数
     * @return
     */
    public static PagingInfo getPagingInfo(int pageNumber, int pageSize, int totalCount) {
        pageNumber = pageNumber < 1 ? DEFAULT_PAGENUMBER : pageNumber;
        pageSize = pageSize < 1 ? DEFAULT_PAGESIZE : pageSize;

        int totalPage = 0;
        if (totalCount > 0) {
            totalPage = getTotalPage(pageSize, totalCount);
            pageNumber = totalPage > pageNumber ? pageNumber : totalPage;
        }

        PagingInfo pagingInfo = new PagingInfo();
        pagingInfo.setPageNumber(pageNumber);
        pagingInfo.setPageSize(pageSize);
        pagingInfo.setTotalCount(totalCount);
        pagingInfo.setTotalPage(totalPage);
        pagingInfo.setIndex(pageSize * (pageNumber - 1));

        return pagingInfo;
    }

    /**
     * 获取总页数
     *
     * @param pageSize    页大小
     * @param totalNumber 总数
     * @return
     */
    public static int getTotalPage(int pageSize, int totalNumber) {
        int page = totalNumber / pageSize;
        return totalNumber % pageSize == 0 ? page : page + 1;
    }
}
