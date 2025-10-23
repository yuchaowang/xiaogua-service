package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CommentsInfoLike;
import org.apache.ibatis.annotations.Param;

public interface CommentsInfoLikeMapper {
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
    int insert(CommentsInfoLike record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(CommentsInfoLike record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return CommentsInfoLike
     */
    CommentsInfoLike selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CommentsInfoLike record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CommentsInfoLike record);

    /**
     * selectByCode
     *
     * @param code
     * @return CommentsInfoLike
     */
    CommentsInfoLike selectByCode(String code);

    /**
     * 通过 评论编号 和 用户编号 获取评论点赞信息
     *
     * @param commentsCode 评论编号
     * @param userCode     用户编号
     * @return
     */
    CommentsInfoLike selectByCommentsCodeAndUserCode(String commentsCode, String userCode);

    /**
     * 获取点赞数量
     *
     * @param commentsInfoCode 点评编号
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    int count(@Param("commentsInfoCode") String commentsInfoCode,
              @Param("startDate") String startDate,
              @Param("endDate") String endDate);
}