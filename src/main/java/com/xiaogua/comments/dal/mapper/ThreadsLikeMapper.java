package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsLike;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ThreadsLikeMapper {
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
    int insert(ThreadsLike record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ThreadsLike record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ThreadsLike
     */
    ThreadsLike selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsLike record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsLike record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ThreadsLike
     */
    ThreadsLike selectByCode(String code);

    /**
     * 通过帖子编号和用户编号获取帖子点赞信息
     * @param threadsCode 帖子编号
     * @param userCode 用户编号
     * @return
     */
    ThreadsLike selectByThreadsCodeAndUserCode(@Param("threadsCode") String threadsCode,
        @Param("userCode") String userCode);

    /**
     * 获取点赞数量
     *
     * @param threadsCode 帖子编号
     * @param startDate 起始时间
     * @param endDate 截止时间
     * @return
     */
    int count(@Param("threadsCode") String threadsCode,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate);
}