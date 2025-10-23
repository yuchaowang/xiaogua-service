package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CheckCodeState;
import org.apache.ibatis.annotations.Param;

public interface CheckCodeStateMapper {
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
    int insert(CheckCodeState record);

    /**
     * insertSelective
     *
     * @param record
     * @return int
     */
    int insertSelective(CheckCodeState record);

    /**
     * selectByPrimaryKey
     *
     * @param id
     * @return CheckCodeState
     */
    CheckCodeState selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CheckCodeState record);

    /**
     * updateByPrimaryKey
     *
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CheckCodeState record);

    /**
     * selectByCode
     *
     * @param code
     * @return CheckCodeState
     */
    CheckCodeState selectByCode(String code);

    /**
     * 通过用户信息和验证码类型获取验证码状态信息
     *
     * @param code
     * @param cacheKey
     * @return
     */
    CheckCodeState selectMaxByCodeAndCacheKey(@Param("code") String code, @Param("cacheKey") String cacheKey);
}