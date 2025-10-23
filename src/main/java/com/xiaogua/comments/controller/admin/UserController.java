package com.xiaogua.comments.controller.admin;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.user.UserPageInfo;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户 Admin Controller
 * @author wangyc
 * @date 2021-02-19 16:23
 */
@Api(tags = "用户 Admin Controller")
@RestController("admin.UserController")
@RequestMapping("/api/admin/user")
public class UserController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserDomain userDomain;

    /**
     * 分页获取用户信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "管理平台-分页获取用户信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getPage(HttpServletRequest request, @RequestBody UserPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = userDomain.getAdminPage(pageInfo);
            LOGGER.info("管理平台-分页获取用户信息[admin.UserController.getPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER
                    .warn("管理平台-分页获取用户信息[admin.UserController.getPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("管理平台-分页获取用户信息[admin.UserController.getPage]异常，参数：{}", JSON.toJSONString(pageInfo),
                    e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
