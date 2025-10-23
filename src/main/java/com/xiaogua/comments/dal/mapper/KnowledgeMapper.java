package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.Knowledge;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KnowledgeMapper {
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
    int insert(Knowledge record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(Knowledge record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return Knowledge
     */
    Knowledge selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Knowledge record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Knowledge record);

    /**
     * selectByCode
     * 
     * @param code
     * @return Knowledge
     */
    Knowledge selectByCode(String code);

    /**
     * 获取数量
     *
     * @param status
     * @return
     */
    int count(@Param("categoryType") Integer categoryType, @Param("status") Integer status, @Param("keyword") String keyword, @Param("userCode") String userCode);

    /**
     * 批量获取
     *
     * @param index    index
     * @param pageSize pageSize
     * @param sort     sort
     * @param status   status
     * @param keyword  关键词（干货标题）
     * @param userCode 上传用户编号
     * @return
     */
    List<Knowledge> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize,
                             @Param("sort") String sort, @Param("status") Integer status, @Param("keyword") String keyword,
                             @Param("userCode") String userCode, @Param("categoryType") Integer categoryType, @Param("prioritySort") String prioritySort);
}