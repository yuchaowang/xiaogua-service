package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsReply;
import com.xiaogua.comments.dal.entity.ThreadsReplyComment;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThreadsReplyCommentMapper {
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
    int insert(ThreadsReplyComment record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ThreadsReplyComment record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ThreadsReplyComment
     */
    ThreadsReplyComment selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsReplyComment record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsReplyComment record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ThreadsReplyComment
     */
    ThreadsReplyComment selectByCode(String code);

    /**
     * 统计帖子回复评论数量
     * @param replyCode 帖子回复编号
     * @param showDeleted 是否展示被删除
     * @return
     */
    int count(@Param("replyCode") String replyCode, @Param("showDeleted") Boolean showDeleted);

    /**
     * 获取分页帖子回复信息
     * @param index 起始值
     * @param pageSize 页大小
     * @param replyCode 帖子回复编号
     * @param showDeleted 是否展示被删除
     * @return
     */
    List<ThreadsReplyComment> findPage(@Param("index") Integer index,
        @Param("pageSize") Integer pageSize,
        @Param("replyCode") String replyCode,
        @Param("showDeleted") Boolean showDeleted);
}