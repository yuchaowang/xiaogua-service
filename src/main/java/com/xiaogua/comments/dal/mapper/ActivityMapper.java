package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.Activity;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityMapper {
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
    int insert(Activity record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(Activity record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return Activity
     */
    Activity selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Activity record);

    /**
     * selectByCode
     * 
     * @param code
     * @return Activity
     */
    Activity selectByCode(String code);

    /**
     * 统计参加活动数量
     * @param name 活动名称
     * @param isFree 是否免费
     * @param statusList 状态
     * @return
     */
    int count(@Param("name") String name, @Param("isFree") Boolean isFree, @Param("statusList") List<Integer> statusList);

    /**
     * 分页获取参加活动信息
     *
     * @param index    起始
     * @param pageSize 页大小
     * @param name 活动名称
     * @param isFree 是否免费
     * @param statusList 状态
     * @return
     */
    List<Activity> findPage(@Param("index") Integer index,
        @Param("pageSize") Integer pageSize,
        @Param("name") String name,
        @Param("isFree") Boolean isFree,
        @Param("statusList") List<Integer> statusList);

    /**
     * 通过活动名称获取活动信息
     * @param name
     * @return
     */
    List<Activity> selectByName(@Param("name") String name);
}