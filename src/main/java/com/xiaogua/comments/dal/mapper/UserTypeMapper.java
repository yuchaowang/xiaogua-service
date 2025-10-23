package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.UserType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTypeMapper {
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
    int insert(UserType record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(UserType record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return UserType
     */
    UserType selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserType record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserType record);

    /**
     * selectByCode
     *
     * @param code
     * @return UserType
     */
    UserType selectByCode(String code);

    /**
     * 通过用户编号 和 状态 获取用户类型数组
     *
     * @param userCode 用户编号
     * @param status   状态
     * @return
     */
    List<UserType> selectByUserCodeAndStatus(@Param("userCode") String userCode, @Param("status") Integer status);

    /**
     * 通过用户编号获取当前用户类型列表
     *
     * @param userCode 用户编号
     * @return
     */
    List<UserType> selectByUserCode(@Param("userCode") String userCode);

    /**
     * 通过用户编号和用户类型获取用户类型信息
     *
     * @param userCode 用户编号
     * @param type     类型
     * @return
     */
    UserType selectByUserCodeAndType(@Param("userCode") String userCode, @Param("type") Integer type);

}