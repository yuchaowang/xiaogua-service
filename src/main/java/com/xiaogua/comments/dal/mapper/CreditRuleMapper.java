package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CreditRule;
import org.apache.ibatis.annotations.Param;

public interface CreditRuleMapper {
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
    int insert(CreditRule record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(CreditRule record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return CreditRule
     */
    CreditRule selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CreditRule record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CreditRule record);

    /**
     * selectByCode
     * 
     * @param code
     * @return CreditRule
     */
    CreditRule selectByCode(String code);

    /**
     * 通过时间获取积分规则
     * @param event
     * @return
     */
    CreditRule selectByEvent(@Param("event") Integer event);
}