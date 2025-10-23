package com.xiaogua.comments.bean.message;

/**
 * 消息数据库查询页码信息
 *
 */
public class MessagePageInfoDal extends MessageCommonPageInfo {

    /**
     * 消息用户编号
     */
    String userCode;


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public MessagePageInfoDal() {}

    public MessagePageInfoDal(MessageCommonPageInfo pageInfo) {
        this.setPageNumber(pageInfo.getPageNumber());
        this.setPageSize(pageInfo.getPageSize());
        this.setSort(pageInfo.getSort());
    }
}
