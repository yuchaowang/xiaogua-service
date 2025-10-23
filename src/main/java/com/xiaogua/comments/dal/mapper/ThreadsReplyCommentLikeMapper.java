package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsReplyCommentLike;
import org.apache.ibatis.annotations.Param;

public interface ThreadsReplyCommentLikeMapper {
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
    int insert(ThreadsReplyCommentLike record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ThreadsReplyCommentLike record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ThreadsReplyCommentLike
     */
    ThreadsReplyCommentLike selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsReplyCommentLike record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsReplyCommentLike record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ThreadsReplyCommentLike
     */
    ThreadsReplyCommentLike selectByCode(String code);

    /**
     * 通过帖子回复评论编号和用户编号获取帖子回复点赞信息
     * @param commentsCode 帖子回复评论编号
     * @param userCode 用户编号
     * @return
     */
    ThreadsReplyCommentLike selectByCommentCodeAndUserCode(@Param("commentsCode") String commentsCode, @Param("userCode") String userCode);
}