package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.CommentsInfoLike;
import com.xiaogua.comments.dal.entity.ThreadsLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.ThreadsLikeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * 帖子点赞 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:44
 */
@Service
public class ThreadsInfoLikeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsInfoLikeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsLikeMapper threadsLikeMapper;

    /**
     * 是否已赞
     *
     * @param threadsCode 帖子编号
     * @param userCode 用户编号
     * @return
     */
    public boolean isLiked(String threadsCode, String userCode) {
        boolean isLiked = false;
        if (!StringUtils.isEmpty(threadsCode) && !StringUtils.isEmpty(userCode)) {
            ThreadsLike threadsLike = threadsLikeMapper.selectByThreadsCodeAndUserCode(threadsCode, userCode);
            if (threadsLike != null) {
                isLiked = true;
            }
        }
        return isLiked;
    }

    /**
     * 获取单个点赞详情
     *
     * @param threadsCode 帖子编号
     * @param userCode     用户编号
     * @return
     */
    public ThreadsLike getThreadsLike(String threadsCode, String userCode) {
        ThreadsLike threadsLike =
            threadsLikeMapper.selectByThreadsCodeAndUserCode(threadsCode, userCode);

        return threadsLike;
    }

    /**
     * 保存点赞记录
     *
     * @param threadsCode 帖子编号
     * @param user 用户
     * @return
     */
    public ThreadsLike save(String threadsCode, User user) {

        ThreadsLike threadsLike =
            threadsLikeMapper.selectByThreadsCodeAndUserCode(threadsCode, user.getCode());

        if (threadsLike != null) {
            throw new CommentsRuntimeException(-1, "已点赞，请勿重复点赞");
        }

        threadsLike = new ThreadsLike();
        threadsLike.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_LIKE.getCode()));
        threadsLike.setThreadsCode(threadsCode);
        threadsLike.setUserCode(user.getCode());
        threadsLike.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        threadsLikeMapper.insertSelective(threadsLike);

        return threadsLike;
    }

    /**
     * 删除点赞记录
     *
     * @param threadsCode 帖子编号
     * @param userCode 用户编号
     * @return
     */
    public void delete(String threadsCode, String userCode) {
        ThreadsLike threadsLike =
            threadsLikeMapper.selectByThreadsCodeAndUserCode(threadsCode, userCode);

        if (threadsLike == null) {
            throw new CommentsRuntimeException(-1, "未点赞，不可取消点赞");
        }

        threadsLikeMapper.deleteByPrimaryKey(threadsLike.getId());
    }

    /**
     * 获取点赞数量
     *
     * @param threadsCode 帖子编号
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    public int count(String threadsCode, String startDate, String endDate) {
        return threadsLikeMapper.count(threadsCode, startDate, endDate);
    }
}
