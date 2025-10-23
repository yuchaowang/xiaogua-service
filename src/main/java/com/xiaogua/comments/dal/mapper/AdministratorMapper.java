package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.Administrator;

public interface AdministratorMapper {
    /**
     * deleteByPrimaryKey
     * 
     * @param userCode
     * @return int
     */
    int deleteByPrimaryKey(String userCode);

    /**
     * insert
     * 
     * @param record
     * @return int
     */
    int insert(Administrator record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(Administrator record);

    /**
     * selectByPrimaryKey
     * 
     * @param userCode
     * @return Administrator
     */
    Administrator selectByPrimaryKey(String userCode);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Administrator record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Administrator record);
}