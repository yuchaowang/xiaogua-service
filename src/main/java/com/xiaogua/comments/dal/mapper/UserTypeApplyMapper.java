package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.UserTypeApply;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTypeApplyMapper {
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
    int insert(UserTypeApply record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(UserTypeApply record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return UserTypeApply
     */
    UserTypeApply selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserTypeApply record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserTypeApply record);

    /**
     * selectByCode
     * 
     * @param code
     * @return UserTypeApply
     */
    UserTypeApply selectByCode(String code);

    /**
     * 通过审核状态统计用户申请信息
     * @param status
     * @return
     */
    int count(@Param("status") Integer status);

    /**
     * 分页获取用户申请信息
     * @param index
     * @param pageSize
     * @param status
     * @return
     */
    List<UserTypeApply> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize, @Param("status") Integer status);

    /**
     * 通过用户类型编号获取用户类型申请信息
     * @param userTypeCode
     * @return
     */
    UserTypeApply selectByUserTypeCode(@Param("userTypeCode") String userTypeCode);
}