package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.CreateUser;

public interface CreateUserMapper {
    /**
     * insert
     * 
     * @param record
     * @return int
     */
    int insert(CreateUser record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(CreateUser record);
}