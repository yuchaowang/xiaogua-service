package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.FileInfo;

public interface FileInfoMapper {
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
    int insert(FileInfo record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(FileInfo record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return FileInfo
     */
    FileInfo selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(FileInfo record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(FileInfo record);

    /**
     * selectByCode
     * 
     * @param code
     * @return FileInfo
     */
    FileInfo selectByCode(String code);
}