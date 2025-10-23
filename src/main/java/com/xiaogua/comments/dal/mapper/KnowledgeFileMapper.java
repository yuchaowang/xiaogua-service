package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.KnowledgeFile;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgeFileMapper {
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
    int insert(KnowledgeFile record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(KnowledgeFile record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return KnowledgeFile
     */
    KnowledgeFile selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(KnowledgeFile record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(KnowledgeFile record);

    /**
     * selectByCode
     *
     * @param code
     * @return KnowledgeFile
     */
    KnowledgeFile selectByCode(String code);

    /**
     * selectByKnowledgeCode
     *
     * @param knowledgeCode
     * @return KnowledgeFile
     */
    List<KnowledgeFile> selectByKnowledgeCode(@Param("knowledgeCode") String knowledgeCode);
}