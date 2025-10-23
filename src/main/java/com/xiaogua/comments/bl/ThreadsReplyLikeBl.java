package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.ThreadsReplyLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.ThreadsReplyLikeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 帖子回复点赞 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:44
 */
@Service
public class ThreadsReplyLikeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyLikeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsReplyLikeMapper threadsReplyLikeMapper;

    /**
     * 是否已赞
     *
     * @param threadsReplyCode 帖子回复编号
     * @param userCode 用户编号
     * @return
     */
    public boolean isLiked(String threadsReplyCode, String userCode) {
        boolean isLiked = false;
        if (!StringUtils.isEmpty(threadsReplyCode) && !StringUtils.isEmpty(userCode)) {
            ThreadsReplyLike threadsReplyLike = threadsReplyLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCode, userCode);
            if (threadsReplyLike != null) {
                isLiked = true;
            }
        }
        return isLiked;
    }

    /**
     * 获取单个点赞详情
     *
     * @param threadsReplyCode 帖子回复编号
     * @param userCode     用户编号
     * @return
     */
    public ThreadsReplyLike getThreadsReplyLike(String threadsReplyCode, String userCode) {
        ThreadsReplyLike threadsReplyLike =
            threadsReplyLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCode, userCode);

        return threadsReplyLike;
    }

    /**
     * 保存点赞记录
     *
     * @param threadsReplyCode 帖子回复编号
     * @param user 用户
     * @return
     */
    public ThreadsReplyLike save(String threadsReplyCode, User user) {

        ThreadsReplyLike threadsReplyLike =
            threadsReplyLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCode, user.getCode());

        if (threadsReplyLike != null) {
            throw new CommentsRuntimeException(-1, "已点赞，请勿重复点赞");
        }

        threadsReplyLike = new ThreadsReplyLike();
        threadsReplyLike.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_LIKE.getCode()));
        threadsReplyLike.setCommentsCode(threadsReplyCode);
        threadsReplyLike.setUserCode(user.getCode());
        threadsReplyLike.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        threadsReplyLikeMapper.insertSelective(threadsReplyLike);

        return threadsReplyLike;
    }

    /**
     * 删除点赞记录
     *
     * @param threadsReplyCode 帖子回复编号
     * @param userCode 用户编号
     * @return
     */
    public void delete(String threadsReplyCode, String userCode) {
        ThreadsReplyLike threadsReplyLike =
            threadsReplyLikeMapper.selectByCommentCodeAndUserCode(threadsReplyCode, userCode);

        if (threadsReplyLike == null) {
            throw new CommentsRuntimeException(-1, "未点赞，不可取消点赞");
        }

        threadsReplyLikeMapper.deleteByPrimaryKey(threadsReplyLike.getId());
    }
}
