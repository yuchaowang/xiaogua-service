package com.xiaogua.comments.bean.threads;

import io.swagger.annotations.ApiModel;

/**
 * 帖子查询分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
@ApiModel(value = "帖子查询分页信息")
public class ThreadsPageInfoDal extends ThreadsPageInfo {

    /**
     * 发帖人编号
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public ThreadsPageInfoDal() {}

    public ThreadsPageInfoDal(ThreadsPageInfo pageInfo) {
        this.setPageNumber(pageInfo.getPageNumber());
        this.setPageSize(pageInfo.getPageSize());
        this.setSort(pageInfo.getSort());
        this.setIsEssence(pageInfo.getIsEssence());
        this.setKeyword(pageInfo.getKeyword());
        this.setType(pageInfo.getType());
    }
}
