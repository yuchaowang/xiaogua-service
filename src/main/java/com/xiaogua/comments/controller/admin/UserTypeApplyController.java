package com.xiaogua.comments.controller.admin;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.user.UserTypeApplyPageInfo;
import com.xiaogua.comments.bean.user.UserTypeApplySubmit;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.UserTypeApplyDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户类型申请Admin Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "用户类型申请Admin Controller")
@RestController(value = "admin.UserTypeApplyController")
@RequestMapping("/api/admin/userTypeApply")
public class UserTypeApplyController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserTypeApplyController.class);

    @Autowired
    UserTypeApplyDomain applyDomain;

    /**
     * 分页获取用户类型申请
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("管理平台-分页获取用户类型申请")
    @PostMapping(value = "/page", produces = "application/json")
    public String getApplysPage(HttpServletRequest request, @RequestBody UserTypeApplyPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = applyDomain.adminGetApplysPage(pageInfo);
            LOGGER.info("分页获取用户申请信息[Admin.UserTypeApplyController.getApplysPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取用户申请信息[Admin.UserTypeApplyController.getApplysPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取用户申请信息[Admin.UserTypeApplyController.getApplysPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-获取用户类型申请信息详情
     *
     * @param request  request
     * @param code code
     * @return
     */
    @ApiOperation("管理平台-获取用户类型申请信息详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String getApply(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = applyDomain.adminGetApply(code);
            LOGGER.info("获取用户类型申请信息详情[Admin.UserTypeApplyController.getApply]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取用户类型申请信息详情[Admin.UserTypeApplyController.getApply]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取用户类型申请信息详情[Admin.UserTypeApplyController.getApply]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-审核通过用户类型申请信息
     *
     * @param request  request
     * @param submit 用户类型申请审核信息
     * @return
     */
    @ApiOperation("管理平台-审核通过用户类型申请信息")
    @PostMapping(value = "/auditSuccess", produces = "application/json")
    public String auditSuccess(HttpServletRequest request, @RequestBody UserTypeApplySubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = applyDomain.auditSuccess(submit, userLoginState.getCode());
            LOGGER.info("审核通过用户类型申请信息[Admin.UserTypeApplyController.auditSuccess]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("审核通过用户类型申请信息[Admin.UserTypeApplyController.auditSuccess]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("审核通过用户类型申请信息[Admin.UserTypeApplyController.auditSuccess]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-审核不通过用户类型申请信息
     *
     * @param request  request
     * @param submit 用户类型申请审核信息
     * @return
     */
    @ApiOperation("管理平台-审核不通过用户类型申请信息")
    @PostMapping(value = "/auditFailed", produces = "application/json")
    public String auditFailed(HttpServletRequest request, @RequestBody UserTypeApplySubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = applyDomain.auditFailed(submit, userLoginState.getCode());
            LOGGER.info("审核不通过用户类型申请信息[Admin.UserTypeApplyController.auditFailed]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("审核不通过用户类型申请信息[Admin.UserTypeApplyController.auditFailed]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("审核不通过用户类型申请信息[Admin.UserTypeApplyController.auditFailed]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
