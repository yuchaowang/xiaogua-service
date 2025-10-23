package com.xiaogua.comments.domain;

import com.xiaogua.comments.bl.CommentsInfoLikeBl;
import com.xiaogua.comments.dal.entity.CommentsInfoLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 评论点赞 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class CommentsLikeDomain {

    @Autowired
    private CommentsInfoLikeBl commentsInfoLikeBl;

    /**
     * 是否已赞
     *
     * @return
     */
    public boolean isLiked(String commentInfoId, String userCode) {
        boolean isLiked = false;
        if (!StringUtils.isEmpty(commentInfoId) && !StringUtils.isEmpty(userCode)) {
            CommentsInfoLike commentsInfoLike = commentsInfoLikeBl.getCommentsInfoLike(commentInfoId, userCode);
            if (commentsInfoLike != null) {
                isLiked = true;
            }
        }
        return isLiked;
    }

}


