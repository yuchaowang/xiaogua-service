package com.xiaogua.comments.bean.threads;

import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;

/**
 * <pre>
 * 帖子简要信息-管理后台
 * </pre>
 */
public class ThreadsInfoAdminSimpleRest extends ThreadsInfoSimpleRest {

    /**
     * 是否删除
     */
    private Boolean deleted;

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public ThreadsInfoAdminSimpleRest() {
        super();
    }

    public ThreadsInfoAdminSimpleRest(ThreadsInfoWithBLOBs threadsInfo) {
        super();
        this.code        = threadsInfo.getCode();
        this.userCode    = threadsInfo.getUserCode();
        this.userName    = threadsInfo.getUserName();
        this.userAvatar  = threadsInfo.getUserAvatar();
        this.usertType   = threadsInfo.getUsertType();
        this.likeNum     = threadsInfo.getLikeNum();
        this.title       = threadsInfo.getTitle();
        this.cover       = threadsInfo.getCover();
        this.content     = threadsInfo.getContent();
        this.contentHtml = threadsInfo.getContentHtml();
        this.type        = threadsInfo.getType();
        this.isEssence   = threadsInfo.getIsEssence();
        this.createDate  = threadsInfo.getCreateDate();
        this.deleted     = threadsInfo.getDeleted();
    }
}