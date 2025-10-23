package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.ThreadsInfo;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ThreadsInfoMapper {
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
    int insert(ThreadsInfoWithBLOBs record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(ThreadsInfoWithBLOBs record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return ThreadsInfoWithBLOBs
     */
    ThreadsInfoWithBLOBs selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(ThreadsInfoWithBLOBs record);

    /**
     * updateByPrimaryKeyWithBLOBs
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(ThreadsInfoWithBLOBs record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(ThreadsInfo record);

    /**
     * selectByCode
     * 
     * @param code
     * @return ThreadsInfoWithBLOBs
     */
    ThreadsInfoWithBLOBs selectByCode(String code);

    int count(@Param("isEssence") boolean isEssence,
        @Param("type") Integer type,
        @Param("keyword") String keyword,
        @Param("userCode") String userCode,
        @Param("deleted") boolean deleted);

    List<ThreadsInfoWithBLOBs> findPage(@Param("index") Integer index,
        @Param("pageSize") Integer pageSize,
        @Param("isEssence") boolean isEssence,
        @Param("type") Integer type,
        @Param("keyword") String keyword,
        @Param("userCode") String userCode,
        @Param("deleted") boolean deleted);
}