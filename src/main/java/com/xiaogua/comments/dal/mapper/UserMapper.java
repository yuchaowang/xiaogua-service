package com.xiaogua.comments.dal.mapper;

import com.xiaogua.comments.dal.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
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
    int insert(User record);

    /**
     * insertSelective
     * 
     * @param record
     * @return int
     */
    int insertSelective(User record);

    /**
     * selectByPrimaryKey
     * 
     * @param id
     * @return User
     */
    User selectByPrimaryKey(Integer id);

    /**
     * updateByPrimaryKeySelective
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * updateByPrimaryKey
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(User record);

    /**
     * updateByCodeSelective
     *
     * @param record
     * @return int
     */
    int updateByCodeSelective(User record);

    /**
     * selectByCode
     * 
     * @param code
     * @return User
     */
    User selectByCode(String code);

    /**
     * selectByOpenId
     *
     * @param openId
     * @return User
     */
    User selectByOpenId(String openId);

    /**
     * selectByWxOpenid
     *
     * @param wxOpenid
     * @return
     */
    User selectByWxOpenid(@Param("wxOpenid") String wxOpenid);

    /**
     * 通过手机号查询用户信息
     * @param mobile 手机号
     * @return
     */
    User selectByMobile(@Param("mobile") String mobile);

    /**
     * 通过用户编号和密码查询用户信息
     * @param code 用户编号
     * @param password 密码
     * @return
     */
    User selectByCodeAndPassword(@Param("code") String code, @Param("password") String password);

    /**
     * 统计用户数量
     * @param keywords 关键词
     * @param sex 性别
     * @param province 省
     * @param city 市
     * @param registerStartDate 注册起始时间
     * @param registerEndDate 注册截止时间
     * @return
     */
    int count(@Param("keywords") String keywords,
              @Param("sex") String sex,
              @Param("province") String province,
              @Param("city") String city,
              @Param("registerStartDate") String registerStartDate,
              @Param("registerEndDate") String registerEndDate);

    /**
     * 分页获取用户信息
     * @param keywords 关键词
     * @param sex 性别
     * @param province 省
     * @param city 市
     * @param registerStartDate 注册起始时间
     * @param registerEndDate 注册截止时间
     *
     * @return
     */
    List<User> findPage(@Param("index") Integer index,
                        @Param("pageSize") Integer pageSize,
                        @Param("keywords") String keywords,
                        @Param("sex") String sex,
                        @Param("province") String province,
                        @Param("city") String city,
                        @Param("registerStartDate") String registerStartDate,
                        @Param("registerEndDate") String registerEndDate);

    List<String> selectAllUserCode();

    /**
     * 根据扫码的微信openId删除用户
     * @param wxOpenid
     */
    void deleteByWxOpenid(@Param("wxOpenid") String wxOpenid);
}