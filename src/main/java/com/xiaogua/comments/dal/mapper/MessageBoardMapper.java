package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.MessageBoard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageBoardMapper {
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
    int insert(MessageBoard record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(MessageBoard record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return MessageBoard
     */
    MessageBoard selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(MessageBoard record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(MessageBoard record);

    /**
     * selectByCode
     * 
     * @param code
     * @return MessageBoard
     */
    MessageBoard selectByCode(String code);

    /**
     * 统计数量
     * @param isRead 是否已阅
     * @param status 昨天
     * @param userCode 留言人code
     * @return
     */
    int count(@Param("isRead") Boolean isRead,
              @Param("status") Integer status,
              @Param("userCode") String userCode);

    /**
     * 分页获取留言信息
     * @param isRead 是否已阅
     * @param status 昨天
     * @param userCode 留言人code
     * @return
     */
    List<MessageBoard> findPage(@Param("index") Integer index,
                                        @Param("pageSize") Integer pageSize,
                                        @Param("isRead") Boolean isRead,
                                        @Param("status") Integer status,
                                        @Param("userCode") String userCode);
}