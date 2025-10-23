package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CommentsReplyLike;
import org.apache.ibatis.annotations.Param;

public interface CommentsReplyLikeMapper {
    /**
     * deleteByPrimaryKey
     *
     * @param id
     * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     *
     * @param record
     * @return int
     */
    int insert(CommentsReplyLike record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(CommentsReplyLike record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return CommentsReplyLike
     */
    CommentsReplyLike selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CommentsReplyLike record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CommentsReplyLike record);

    /**
     * selectByCode
     *
     * @param code
     * @return CommentsReplyLike
     */
    CommentsReplyLike selectByCode(String code);

    /**
     * 通过评论回复编号和用户编号获取评论回复点赞信息
     *
     * @param commentsReplyCode
     * @param userCode
     * @return
     */
    CommentsReplyLike selectByCommentsReplyCodeAndUserCode(@Param("commentsReplyCode") String commentsReplyCode,
        @Param("userCode") String userCode);
}