package com.xiaogua.comments.bl;

import com.xiaogua.comments.common.constant.CacheKeyConstant;
import com.xiaogua.comments.common.constant.ResponseStatus;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.dal.mapper.UserLoginStateMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:36
 */
@Service
public class UserLoginStateBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginStateBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserLoginStateMapper userLoginStateMapper;

    /**
     * 通过用户编号 和 token 获取用户登录状态信息
     * @param userCode 用户编号
     * @param token token
     * @return
     */
    public UserLoginState getUserLoginStateByCodeAndCacheKey(String userCode, String token) {
        UserLoginState userLoginState = userLoginStateMapper.selectByCodeAndToken(userCode, token);
        return userLoginState;
    }

    /**
     * 获取最大时效用户登录状态
     *
     * @param code 用户编号
     * @param cacheKey 用户登录类型
     */
    public UserLoginState getMaxUserLoginState(String code, String cacheKey) {
        UserLoginState userLoginState = userLoginStateMapper.selectMaxByCodeAndCacheKey(code, cacheKey);
        return userLoginState;
    }

    /**
     * 删除用户登录状态信息
     * @param userLoginState 用户登录状态信息
     */
    public void delete(UserLoginState userLoginState) {
        userLoginStateMapper.deleteByPrimaryKey(userLoginState.getId());
    }

    /**
     * 更新用户登录状态信息
     * @param userLoginState 用户登录状态信息
     */
    public void update(UserLoginState userLoginState) {
        userLoginStateMapper.updateByPrimaryKeySelective(userLoginState);
    }

    /**
     * 新增用户登录状态信息
     * @param userLoginState 用户登录状态信息
     */
    public void insert(UserLoginState userLoginState) {
        userLoginStateMapper.insertSelective(userLoginState);
    }

    /**
     * 验证登录态
     *
     * @param token token
     * @return
     */
    public UserLoginState checkLogin(String token) {
        UserLoginState userLoginState = checkUserLoginStateInfo(token);
        return userLoginState;
    }

    /**
     * 获取登录态信息
     *
     * @param token token
     * @return
     */
    public UserLoginState checkUserLoginStateInfo(String token) {
        if (StringUtils.isEmpty(token)) {
            throw new CommentsRuntimeException(ResponseStatus.NO_LOGIN.value(), "用户未登录");
        }
        UserLoginState userLoginState = userLoginStateMapper.selectByToken(token);
        if (userLoginState == null) {
            throw new CommentsRuntimeException(ResponseStatus.NO_LOGIN.value(), "用户未登录");
        }

        if (userLoginState.getExpir().before(new Date())) {
            throw new CommentsRuntimeException(ResponseStatus.TOKEN_EXPIRED.value(), "用户登录已过期");
        }
        return userLoginState;
    }
}
