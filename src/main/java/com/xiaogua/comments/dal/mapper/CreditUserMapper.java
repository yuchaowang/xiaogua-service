package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CreditUser;
import org.apache.ibatis.annotations.Param;

public interface CreditUserMapper {
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
    int insert(CreditUser record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(CreditUser record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return CreditUser
     */
    CreditUser selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CreditUser record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CreditUser record);

    /**
     * selectByCode
     * 
     * @param code
     * @return CreditUser
     */
    CreditUser selectByCode(String code);


    /**
     * selectByByIdForUpdate
     *
     * @param code
     * @return CreditUser
     */
    CreditUser selectByByIdForUpdate(@Param("id") Integer id);

    /**
     * 获取用户积分信息
     * @param userCode
     * @return
     */
    CreditUser selectByUserCode(@Param("userCode") String userCode);
}