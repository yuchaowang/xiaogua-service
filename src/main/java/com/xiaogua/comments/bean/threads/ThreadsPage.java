package com.xiaogua.comments.bean.threads;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import java.util.List;

/**
 * 分页帖子信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class ThreadsPage {

    PagingInfo pagingInfo;

    List<ThreadsInfoWithBLOBs> threadsInfos;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<ThreadsInfoWithBLOBs> getThreadsInfos() {
        return threadsInfos;
    }

    public void setThreadsInfos(List<ThreadsInfoWithBLOBs> threadsInfos) {
        this.threadsInfos = threadsInfos;
    }
}
