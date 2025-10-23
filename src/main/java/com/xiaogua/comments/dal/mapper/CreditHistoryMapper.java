package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CreditHistory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CreditHistoryMapper {
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
    int insert(CreditHistory record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(CreditHistory record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return CreditHistory
     */
    CreditHistory selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(CreditHistory record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(CreditHistory record);

    /**
     * selectByCode
     * 
     * @param code
     * @return CreditHistory
     */
    CreditHistory selectByCode(String code);

    /**
     * 通过用户code和事件获取用户积分历史
     * @param userCode
     * @param event
     * @return
     */
    List<CreditHistory> selectByUserCodeAndEventAndDate(@Param("userCode") String userCode,
        @Param("event") Integer event,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate);

    /**
     * 通过关联code和事件获取用户积分历史
     * @param relCode
     * @param event
     * @return
     */
    List<CreditHistory> selectByRelCodeAndEventAndDate(@Param("relCode") String relCode,
        @Param("event") Integer event,
        @Param("startDate") String startDate,
        @Param("endDate") String endDate);
}