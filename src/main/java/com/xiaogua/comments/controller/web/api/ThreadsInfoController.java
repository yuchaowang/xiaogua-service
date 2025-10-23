package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.threads.ThreadsPageInfo;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ThreadsInfoDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "帖子Web API Controller")
@RestController("api.ThreadsInfoController")
@RequestMapping("/api/threads")
public class ThreadsInfoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsInfoController.class);

    @Autowired
    ThreadsInfoDomain threadsInfoDomain;

    /**
     * 分页获取帖子
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取帖子")
    @PostMapping(value = "/page", produces = "application/json")
    public String getThreadsInfoPage(HttpServletRequest request, @RequestBody ThreadsPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = getLoginInfo(request);
            builder = threadsInfoDomain.getPage(pageInfo, userLoginState);
            LOGGER.info("分页获取帖子[ThreadsInfoController.getThreadsInfoPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取帖子[ThreadsInfoController.getThreadsInfoPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取帖子[ThreadsInfoController.getThreadsInfoPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取单条帖子详情
     *
     * @param request request
     * @param code    帖子编号
     * @return
     */
    @ApiOperation(value = "获取单条帖子详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String getThreadsInfo(HttpServletRequest request,
        @ApiParam(name = "code", value = "帖子编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = getLoginInfo(request);
            builder = threadsInfoDomain.getThreadsInfo(code, userLoginState);
            LOGGER.info("获取单条帖子详情[ThreadsInfoController.getThreadsInfo]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单条帖子详情[ThreadsInfoController.getThreadsInfo]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单条帖子详情[ThreadsInfoController.getThreadsInfo]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
