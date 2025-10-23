package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.CommentsInfoLike;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.CommentsInfoLikeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评论点赞 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:44
 */
@Service
public class CommentsInfoLikeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsInfoLikeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsInfoLikeMapper commentsInfoLikeMapper;

    /**
     * 获取单个点赞详情
     *
     * @param commentsCode 评论编号
     * @param userCode     用户编号
     * @return
     */
    public CommentsInfoLike getCommentsInfoLike(String commentsCode, String userCode) {
        VerifyParamsUtil.hasText(commentsCode, "评论编号不可为空");
        VerifyParamsUtil.hasText(userCode, "用户编号不可为空");

        CommentsInfoLike commentsInfoLike =
            commentsInfoLikeMapper.selectByCommentsCodeAndUserCode(commentsCode, userCode);

        return commentsInfoLike;
    }

    /**
     * 保存点赞记录
     *
     * @param commentsCode 评论编号
     * @return
     */
    public CommentsInfoLike save(String commentsCode, User user) {

        CommentsInfoLike commentsInfoLike =
            commentsInfoLikeMapper.selectByCommentsCodeAndUserCode(commentsCode, user.getCode());

        if (commentsInfoLike != null) {
            throw new CommentsRuntimeException(-1, "已点赞，请勿重复点赞");
        }

        commentsInfoLike = new CommentsInfoLike();
        commentsInfoLike.setCode(BizCodeUtil.genLongBizCode(TableCode.T_COMMENTS_INFO_LIKE.getCode()));
        commentsInfoLike.setCommentsCode(commentsCode);
        commentsInfoLike.setUserCode(user.getCode());
        commentsInfoLike.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        commentsInfoLikeMapper.insertSelective(commentsInfoLike);

        return commentsInfoLike;
    }

    /**
     * 删除点赞记录
     *
     * @param commentsCode 评论编号
     * @return
     */
    public void delete(String commentsCode, String userCode) {
        CommentsInfoLike commentsInfoLike =
            commentsInfoLikeMapper.selectByCommentsCodeAndUserCode(commentsCode, userCode);

        if (commentsInfoLike == null) {
            throw new CommentsRuntimeException(-1, "未点赞，不可取消点赞");
        }

        commentsInfoLikeMapper.deleteByPrimaryKey(commentsInfoLike.getId());
    }


    /**
     * 获取点赞数量
     *
     * @param commentsInfoCode 点评编号
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    public int count(String commentsInfoCode, String startDate, String endDate) {
        return commentsInfoLikeMapper.count(commentsInfoCode, startDate, endDate);
    }
}
