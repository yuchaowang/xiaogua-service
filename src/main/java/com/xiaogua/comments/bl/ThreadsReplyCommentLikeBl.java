package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.ThreadsReplyCommentLike;
import com.xiaogua.comments.dal.entity.ThreadsReplyLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.ThreadsReplyCommentLikeMapper;
import com.xiaogua.comments.dal.mapper.ThreadsReplyLikeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 帖子回复评论点赞 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:44
 */
@Service
public class ThreadsReplyCommentLikeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyCommentLikeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsReplyCommentLikeMapper replyCommentLikeMapper;

    /**
     * 是否已赞
     *
     * @param threadsReplyCommentCode 帖子回复评论编号
     * @param userCode 用户编号
     * @return
     */
    public boolean isLiked(String threadsReplyCommentCode, String userCode) {
        boolean isLiked = false;
        if (!StringUtils.isEmpty(threadsReplyCommentCode) && !StringUtils.isEmpty(userCode)) {
            ThreadsReplyCommentLike replyCommentLike = replyCommentLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCommentCode, userCode);
            if (replyCommentLike != null) {
                isLiked = true;
            }
        }
        return isLiked;
    }

    /**
     * 获取单个点赞详情
     *
     * @param threadsReplyCommentCode 帖子回复评论编号
     * @param userCode     用户编号
     * @return
     */
    public ThreadsReplyCommentLike getThreadsReplyCommetnLike(String threadsReplyCommentCode, String userCode) {
        ThreadsReplyCommentLike replyCommentLike =
            replyCommentLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCommentCode, userCode);

        return replyCommentLike;
    }

    /**
     * 保存点赞记录
     *
     * @param threadsReplyCommentCode 帖子回复评论编号
     * @param threadsCode 帖子编号
     * @param user 用户
     * @return
     */
    public ThreadsReplyCommentLike save(String threadsReplyCommentCode, String threadsCode, User user) {
        ThreadsReplyCommentLike replyCommentLike =
            replyCommentLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCommentCode, user.getCode());

        if (replyCommentLike != null) {
            throw new CommentsRuntimeException(-1, "已点赞，请勿重复点赞");
        }

        replyCommentLike = new ThreadsReplyCommentLike();
        replyCommentLike.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_LIKE.getCode()));
        replyCommentLike.setCommentsCode(threadsReplyCommentCode);
        replyCommentLike.setUserCode(user.getCode());
        replyCommentLike.setThreadsCode(threadsCode);
        replyCommentLike.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        replyCommentLikeMapper.insertSelective(replyCommentLike);

        return replyCommentLike;
    }

    /**
     * 删除点赞记录
     *
     * @param threadsReplyCommentCode 帖子回复评论编号
     * @param userCode 用户编号
     * @return
     */
    public void delete(String threadsReplyCommentCode, String userCode) {
        ThreadsReplyCommentLike replyCommentLike =
            replyCommentLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCommentCode, userCode);

        if (replyCommentLike == null) {
            throw new CommentsRuntimeException(-1, "未点赞，不可取消点赞");
        }

        replyCommentLikeMapper.deleteByPrimaryKey(replyCommentLike.getId());
    }
}
