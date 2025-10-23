package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsReply;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ThreadsReplyMapper {
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
    int insert(ThreadsReply record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(ThreadsReply record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return ThreadsReply
     */
    ThreadsReply selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsReply record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsReply record);

    /**
     * selectByCode
     *
     * @param code
     * @return ThreadsReply
     */
    ThreadsReply selectByCode(String code);

    /**
     * 统计帖子回复数量
     * @param threadsCode 帖子编号
     * @param showDeleted 是否展示被删除
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    int count(@Param("threadsCode") String threadsCode,
        @Param("showDeleted") Boolean showDeleted,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate);

    /**
     * 获取分页帖子回复信息
     * @param index 起始值
     * @param pageSize 页大小
     * @param threadsCode 帖子编号
     * @param showDeleted 是否展示被删除
     * @return
     */
    List<ThreadsReply> findPage(@Param("index") Integer index,
        @Param("pageSize") Integer pageSize,
        @Param("threadsCode") String threadsCode,
        @Param("showDeleted") Boolean showDeleted);
}