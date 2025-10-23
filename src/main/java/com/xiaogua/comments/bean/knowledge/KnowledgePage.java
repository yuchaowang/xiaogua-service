package com.xiaogua.comments.bean.knowledge;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.Knowledge;
import java.util.List;

/**
 * 分页干货信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class KnowledgePage {

    PagingInfo pagingInfo;

    List<Knowledge> knowledges;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<Knowledge> getKnowledges() {
        return knowledges;
    }

    public void setKnowledges(List<Knowledge> knowledges) {
        this.knowledges = knowledges;
    }
}
