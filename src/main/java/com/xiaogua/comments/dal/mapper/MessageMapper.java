package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.bean.message.MessagePageInfoDal;
import com.xiaogua.comments.dal.entity.Message;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MessageMapper {
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
    int insert(Message record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(Message record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return Message
     */
    Message selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(Message record);

    /**
     * updateByPrimaryKeyWithBLOBs
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(Message record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(Message record);

    /**
     * selectByCode
     * 
     * @param code
     * @return Message
     */
    Message selectByCode(String code);

    int count(@Param("userCode") String userCode, @Param("keyword") String keyword, @Param("startTime") Date startTime, @Param("endTime") Date endTime
            , @Param("isRead") Integer isRead, @Param("type") Integer type);

    int groupByCount(@Param("userCode") String userCode, @Param("keyword") String keyword, @Param("startTime") Date startTime, @Param("endTime") Date endTime
            , @Param("isRead") Integer isRead, @Param("type") Integer type);

    List<Message> findPage(@Param("index") Integer index, @Param("pageSize") Integer pageSize, @Param("userCode") String userCode, @Param("keyword") String keyword, @Param("startTime") Date startTime
            , @Param("endTime") Date endTime, @Param("isRead") Integer isRead, @Param("type") Integer type, @Param("isGroupId") Integer isGroupId);

    void updateMessage(@Param("messageCode") String messageCode, @Param("userCode") String userCode);

    void deleteByPrimaryGroupId(@Param("groupId") String groupId);
}