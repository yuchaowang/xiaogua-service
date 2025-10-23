package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CommentsReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentsReplyMapper {
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
    int insert(CommentsReply record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(CommentsReply record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return CommentsReply
     */
    CommentsReply selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CommentsReply record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CommentsReply record);

    /**
     * selectByCode
     *
     * @param code
     * @return CommentsReply
     */
    CommentsReply selectByCode(String code);

    /**
     * 获取数量
     *
     * @param commentsCode 评论编号
     * @param showDeleted  是否展示删除
     * @return
     */
    int count(@Param("commentsCode") String commentsCode, @Param("showDeleted") boolean showDeleted,
        @Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 分页获取评论回复信息
     *
     * @param commentsCode 评论编号
     * @param showDeleted  是否展示删除
     * @return
     */
    List<CommentsReply> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize,
        @Param("commentsCode") String commentsCode, @Param("showDeleted") boolean showDeleted);

    /**
     * 获取评论回复（不含删除）
     *
     * @param code
     * @return
     */
    CommentsReply selectByCodeWithOutDel(@Param("code") String code);

}