package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.CommentsReplyLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.CommentsReplyLikeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论回复点赞 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:44
 */
@Service
public class CommentsReplyLikeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsReplyLikeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsReplyLikeMapper commentsReplyLikeMapper;

    /**
     * 获取单个点赞详情
     *
     * @param commentsReplyCode 评论回复编号
     * @param userCode          用户编号
     * @return
     */
    public CommentsReplyLike getCommentsReplyLike(String commentsReplyCode, String userCode) {
        CommentsReplyLike commentsReplyLike =
            commentsReplyLikeMapper.selectByCommentsReplyCodeAndUserCode(commentsReplyCode, userCode);

        return commentsReplyLike;
    }

    /**
     * 保存点赞记录
     *
     * @param commentsReplyCode 评论回复编号
     * @return
     */
    public CommentsReplyLike save(String commentsReplyCode, User user) {

        CommentsReplyLike commentsReplyLike =
            commentsReplyLikeMapper.selectByCommentsReplyCodeAndUserCode(commentsReplyCode, user.getCode());

        if (commentsReplyLike != null) {
            throw new CommentsRuntimeException(-1, "已点赞，请勿重复点赞");
        }

        commentsReplyLike = new CommentsReplyLike();
        commentsReplyLike.setCode(BizCodeUtil.genLongBizCode(TableCode.T_COMMENTS_REPLY_LIKE.getCode()));
        commentsReplyLike.setCommentsCode(commentsReplyCode);
        commentsReplyLike.setUserCode(user.getCode());
        commentsReplyLike.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        commentsReplyLikeMapper.insertSelective(commentsReplyLike);

        return commentsReplyLike;
    }

    /**
     * 删除ß点赞记录
     *
     * @param commentsCode 评论编号
     * @return
     */
    public void delete(String commentsCode, String userCode) {
        CommentsReplyLike commentsReplyLike =
            commentsReplyLikeMapper.selectByCommentsReplyCodeAndUserCode(commentsCode, userCode);

        if (commentsReplyLike == null) {
            throw new CommentsRuntimeException(-1, "未点赞，不可取消点赞");
        }

        commentsReplyLikeMapper.deleteByPrimaryKey(commentsReplyLike.getId());
    }

    public boolean isLiked(String commentsReplyCode, String userCode) {
        CommentsReplyLike commentsReplyLike =
            commentsReplyLikeMapper.selectByCommentsReplyCodeAndUserCode(commentsReplyCode, userCode);

        return commentsReplyLike != null;
    }
}
