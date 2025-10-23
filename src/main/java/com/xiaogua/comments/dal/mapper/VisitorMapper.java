package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.Visitor;

import java.util.List;

public interface VisitorMapper {
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
    int insert(Visitor record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(Visitor record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return Visitor
     */
    Visitor selectByPrimaryKey(Integer id);

    /**
     * selectByOpenId
     *
     * @param openId
     * @return Visitor
     */
    Integer selectByOpenId(String openId);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Visitor record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Visitor record);

    /**
     * selectByCode
     * 
     * @param code
     * @return Visitor
     */
    Visitor selectByCode(String code);

    /**
     * 获取所有的openId
     * @return
     */
    List<String> selectAllOpenId();
}