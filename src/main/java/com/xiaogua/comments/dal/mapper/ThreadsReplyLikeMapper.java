package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsReplyLike;
import org.apache.ibatis.annotations.Param;

public interface ThreadsReplyLikeMapper {
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
    int insert(ThreadsReplyLike record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ThreadsReplyLike record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ThreadsReplyLike
     */
    ThreadsReplyLike selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsReplyLike record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsReplyLike record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ThreadsReplyLike
     */
    ThreadsReplyLike selectByCode(String code);

    /**
     * 通过帖子回复编号和用户编号获取帖子回复点赞信息
     * @param commentsCode 帖子回复编号
     * @param userCode 用户编号
     * @return
     */
    ThreadsReplyLike selectByCommentCodeAndUserCode(@Param("commentsCode") String commentsCode, @Param("userCode") String userCode);
}