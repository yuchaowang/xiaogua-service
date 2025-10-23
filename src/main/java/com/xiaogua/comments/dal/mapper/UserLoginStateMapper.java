package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.UserLoginState;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface UserLoginStateMapper {
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
    int insert(UserLoginState record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(UserLoginState record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return UserLoginState
     */
    UserLoginState selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserLoginState record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserLoginState record);

    /**
     * selectByCode
     * 
     * @param code
     * @return UserLoginState
     */
    UserLoginState selectByCode(String code);

    /**
     * 通过用户编号 和 用户登录类型 获取最大有效期的 用户登录状态信息
     * @param code 用户编号
     * @param cacheKey 用户登录类型
     * @return
     */
    UserLoginState selectMaxByCodeAndCacheKey(@Param("code") String code, @Param("cacheKey") String cacheKey);

    /**
     * 通过token获取最大有效期的用户登录状态信息
     * @param token token
     * @return
     */
    UserLoginState selectByToken(@Param("token") String token);

    /**
     * 通过用户编号 和 token 获取用户登录状态信息
     * @param code 用户编号
     * @param token token
     * @return
     */
    UserLoginState selectByCodeAndToken(@Param("code") String code, @Param("token") String token);
}