package com.xiaogua.comments.controller.web;

import com.xiaogua.comments.bl.AdministratorBl;
import com.xiaogua.comments.bl.UserLoginStateBl;
import com.xiaogua.comments.common.constant.ResponseStatus;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * web接口验证登陆态
 * @author wangyc
 */
public abstract class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    UserDomain userDomain;

    @Autowired
    UserLoginStateBl userLoginStateBl;

    @Autowired
    AdministratorBl administratorBl;

    protected UserLoginState isLogin(HttpServletRequest request) throws CommentsRuntimeException {
        UserLoginState userLoginState = getUserLoginState(request);
        isLogin(userLoginState);
        return userLoginState;
    }

    protected UserLoginState isAdmin(HttpServletRequest request) throws CommentsRuntimeException {
        UserLoginState userLoginState = getUserLoginState(request);
        isAdmin(userLoginState);
        return userLoginState;
    }

    protected UserLoginState getLoginInfo(HttpServletRequest request) {
        UserLoginState userLoginState = getUserLoginState(request);
        if (userLoginState == null) {
            userLoginState = new UserLoginState();
        }
        return userLoginState;
    }


    /**
     * 是否登录
     *
     * @param userLoginState  对象
     */
    private void isLogin(UserLoginState userLoginState) throws CommentsRuntimeException {
        if (userLoginState == null) {
            throw new CommentsRuntimeException(ResponseStatus.NO_LOGIN.value(), "用户未登录");
        }
    }

    /**
     * 是否管理员
     *
     * @param userLoginState  对象
     */
    private void isAdmin(UserLoginState userLoginState) throws CommentsRuntimeException {
        isLogin(userLoginState);
        if (!administratorBl.check(userLoginState.getCode())) {
            throw new CommentsRuntimeException(ResponseStatus.ISNOT_ADMINISTRATOR.value(), "非管理员，无法访问");
        }
    }

    protected UserLoginState getUserLoginState(HttpServletRequest request) {
        String token = userDomain.getToken(request, "token");
        return this.getUserLoginState(token);
    }

    protected UserLoginState getUserLoginState(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        } else {
            UserLoginState state = this.checkLogin(token);
            if (state == null) {
                return null;
            } else {
                UserLoginState userLoginState = new UserLoginState();
                userLoginState.setCode(state.getCode());
                return userLoginState;
            }
        }
    }

    private UserLoginState checkLogin(String token) {
        try {
            UserLoginState userLoginState = userLoginStateBl.checkLogin(token);
            if (userLoginState == null) {
                return null;
            }
            return userLoginState;
        } catch (Exception e) {
            LOGGER.error("验证登录态[BaseSsoController.checkLogin].异常：", e);
            return null;
        }
    }
}
