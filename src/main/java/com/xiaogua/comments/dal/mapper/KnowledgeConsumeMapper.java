package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.KnowledgeConsume;
import org.apache.ibatis.annotations.Param;

public interface KnowledgeConsumeMapper {
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
    int insert(KnowledgeConsume record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(KnowledgeConsume record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return KnowledgeConsume
     */
    KnowledgeConsume selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(KnowledgeConsume record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(KnowledgeConsume record);

    /**
     * selectByCode
     * 
     * @param code
     * @return KnowledgeConsume
     */
    KnowledgeConsume selectByCode(String code);

    /**
     * 通过干货编号和用户编号获取干货消费信息
     * @param knowledgeCode 干货编号
     * @param userCode 用户编号
     * @return
     */
    KnowledgeConsume selectByKnowledgeCodeAndUserCode(@Param("knowledgeCode") String knowledgeCode, @Param("userCode") String userCode);

    /**
     * 通过干货编号统计干货消费信息数量
     * @param knowledgeCode
     * @return
     */
    int countByKnowledgeCode(@Param("knowledgeCode") String knowledgeCode);
}