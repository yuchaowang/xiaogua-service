package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.UserTypeFile;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserTypeFileMapper {
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
    int insert(UserTypeFile record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(UserTypeFile record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return UserTypeFile
     */
    UserTypeFile selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(UserTypeFile record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(UserTypeFile record);

    /**
     * 通过用户编号和用户类型获取用户类型文件信息
     * @param userCode
     * @param userType
     * @return
     */
    List<UserTypeFile> selectByUserCodeAndUserType(@Param("userCode") String userCode, @Param("userType") Integer userType);

    /**
     * 通过用户编号和用户类型删除用户类型文件信息
     * @param userCode
     * @param userType
     * @return
     */
    int deleteByUserCodeAndUserType(@Param("userCode") String userCode, @Param("userType") Integer userType);
}