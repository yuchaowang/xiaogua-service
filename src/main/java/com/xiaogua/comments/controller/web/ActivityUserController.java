package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.activity.ActivityPageInfo;
import com.xiaogua.comments.bean.activity.ActivityUserSubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ActivityUserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动用户Web Controller
 *
 * @author wangyc
 * @date 2021-5-16
 */
@RestController
@Api(tags = "活动用户Web Controller")
@RequestMapping("/api/activityuser")
public class ActivityUserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityUserController.class);

    @Autowired
    ActivityUserDomain activityUserDomain;

    /**
     * 报名
     *
     * @param request request
     * @param submit submit
     * @return
     */
    @ApiOperation(value = "报名")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody ActivityUserSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = activityUserDomain.save(submit, userLoginState.getCode());
            LOGGER.info("报名[ActivityUserController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("报名[ActivityUserController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("报名[ActivityUserController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 验证是否已报名
     *
     * @param request request
     * @param activityCode 活动编号
     * @return
     */
    @ApiOperation(value = "验证是否已报名")
    @ApiImplicitParams({@ApiImplicitParam(name = "activityCode", value = "活动编号", required = false)})
    @GetMapping(value = "/validateexist", produces = "application/json")
    public String validateExist(HttpServletRequest request, @Param("activityCode") String activityCode) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = activityUserDomain.validateExist(activityCode, userLoginState.getCode());
            LOGGER.info("验证是否已报名[ActivityUserController.validateExist]正常，参数：{}", userLoginState.getCode());
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("验证是否已报名[ActivityUserController.validateExist]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("验证是否已报名[ActivityUserController.validateExist]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 分页获取我的报名活动
     *
     * @param request request
     * @param pageInfo pageInfo
     * @return
     */
    @ApiOperation(value = "分页获取我的报名活动")
    @PostMapping(value = "/apply", produces = "application/json")
    public String getApplyActivity(HttpServletRequest request, @RequestBody ActivityPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = activityUserDomain.getApplyActivity(pageInfo, userLoginState.getCode());
            LOGGER.info("分页获取我的报名活动[ActivityUserController.getApplyActivity]正常，参数：{}", userLoginState.getCode());
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的报名活动[ActivityUserController.getApplyActivity]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的报名活动[ActivityUserController.getApplyActivity]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取我的票券详情
     *
     * @param request request
     * @param code 票券编号
     * @return
     */
    @ApiOperation(value = "获取我的票券详情")
    @GetMapping(value = "/apply/{code}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "票券编号", required = true)})
    public String getApplyActivityUserDetail(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = activityUserDomain.getActivityUserDetail(code, userLoginState.getCode());
            LOGGER.info("获取我的票券详情[ActivityUserController.getApplyActivityUserDetail]正常，参数：{}, {}", code, userLoginState.getCode());
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取我的票券详情[ActivityUserController.getApplyActivityUserDetail]告警，参数：{}", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取我的票券详情[ActivityUserController.getApplyActivityUserDetail]异常，参数：{}", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
