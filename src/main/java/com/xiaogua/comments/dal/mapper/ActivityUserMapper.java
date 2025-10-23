package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ActivityUser;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityUserMapper {
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
    int insert(ActivityUser record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ActivityUser record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ActivityUser
     */
    ActivityUser selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ActivityUser record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ActivityUser record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ActivityUser
     */
    ActivityUser selectByCode(String code);

    /**
     * 通过活动编号和用户编号查询参加活动用户信息
     * @param activityCode 活动编号
     * @param userCode 用户编号
     * @return
     */
    ActivityUser selectByActivityCodeAndUserCode(@Param("activityCode") String activityCode, @Param("userCode") String userCode);

    /**
     * 统计参加活动用户数量
     * @param activityCode 活动编号
     * @param userCode 用户编号
     * @return
     */
    int count(@Param("activityCode") String activityCode, @Param("userCode") String userCode);

    /**
     * 分页获取参加活动用户信息
     *
     * @param index    起始
     * @param pageSize 页大小
     * @param activityCode 活动编号
     * @param userCode 用户编号
     * @return
     */
    List<ActivityUser> findPage(@Param("index") Integer index,
        @Param("pageSize") Integer pageSize,
        @Param("activityCode") String activityCode,
        @Param("userCode") String userCode);

    /**
     * 通过活动编号获取全部活动用户信息
     * @param activityCode 活动编号
     * @return
     */
    List<ActivityUser> findByActivityCode(@Param("activityCode") String activityCode);
}