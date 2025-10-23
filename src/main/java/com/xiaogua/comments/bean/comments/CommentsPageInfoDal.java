package com.xiaogua.comments.bean.comments;

/**
 * 评论数据库查询页码信息
 *
 * @author wangyc
 * @date 2020-11-16 19:17:39
 */
public class CommentsPageInfoDal extends CommentsCommonPageInfo {

    /**
     * 评论用户编号
     */
    String userCode;

    /**
     * 是否展示删除数据
     */
    boolean showDeleted;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean isShowDeleted() {
        return showDeleted;
    }

    public void setShowDeleted(boolean showDeleted) {
        this.showDeleted = showDeleted;
    }

    public CommentsPageInfoDal() {}

    public CommentsPageInfoDal(CommentsCommonPageInfo pageInfo) {
        this.setPageNumber(pageInfo.getPageNumber());
        this.setPageSize(pageInfo.getPageSize());
        this.setSort(pageInfo.getSort());
        this.setToCode(pageInfo.getToCode());
        this.setShowDeleted(false);
        this.setUserType(pageInfo.getUserType());
    }
}
