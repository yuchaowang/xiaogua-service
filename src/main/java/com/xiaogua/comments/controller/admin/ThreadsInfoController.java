package com.xiaogua.comments.controller.admin;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 帖子admin Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "帖子Web Admin Controller")
@RestController("admin.ThreadsInfoController")
@RequestMapping("/api/admin/threads")
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
    @ApiOperation(value = "管理平台-分页获取帖子")
    @PostMapping(value = "/page", produces = "application/json")
    public String getThreadsInfoPage(HttpServletRequest request, @RequestBody ThreadsPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = threadsInfoDomain.getAdminPage(pageInfo);
            LOGGER.info("管理平台-分页获取帖子[admin.ThreadsInfoController.getThreadsInfoPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER
                .warn("管理平台-分页获取帖子[admin.ThreadsInfoController.getThreadsInfoPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("管理平台-分页获取帖子[admin.ThreadsInfoController.getThreadsInfoPage]异常，参数：{}", JSON.toJSONString(pageInfo),
                         e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 管理平台-获取单条帖子详情
     *
     * @param request request
     * @param code    帖子编号
     * @return
     */
    @ApiOperation(value = "管理平台-获取单条帖子详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String getThreadsInfo(HttpServletRequest request,
        @ApiParam(name = "code", value = "帖子编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = threadsInfoDomain.getAdminThreadsInfo(code);
            LOGGER.info("管理平台-获取单条帖子详情[admin.ThreadsInfoController.getThreadsInfo]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("管理平台-获取单条帖子详情[admin.ThreadsInfoController.getThreadsInfo]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("管理平台-获取单条帖子详情[admin.ThreadsInfoController.getThreadsInfo]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 帖子加精
     *
     * @param request request
     * @param code    帖子编号
     * @return
     */
    @ApiOperation(value = "帖子加精")
    @PutMapping(value = "/essence", produces = "application/json")
    public String essence(HttpServletRequest request,
        @ApiParam(name = "code", value = "帖子编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = threadsInfoDomain.essence(code, userLoginState);
            LOGGER.info("帖子加精[admin.ThreadsInfoController.essence]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("帖子加精[admin.ThreadsInfoController.essence]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("帖子加精[admin.ThreadsInfoController.essence]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 取消帖子加精
     *
     * @param request request
     * @param code    帖子编号
     * @return
     */
    @ApiOperation(value = "取消帖子加精")
    @PutMapping(value = "/unessence", produces = "application/json")
    public String unessence(HttpServletRequest request,
        @ApiParam(name = "code", value = "帖子编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = threadsInfoDomain.unessence(code, userLoginState);
            LOGGER.info("取消帖子加精[admin.ThreadsInfoController.unessence]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("取消帖子加精[admin.ThreadsInfoController.unessence]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("取消帖子加精[admin.ThreadsInfoController.unessence]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 删除帖子
     *
     * @param request request
     * @param code    帖子编号
     * @return
     */
    @ApiOperation(value = "删除帖子")
    @DeleteMapping(value = "/delete", produces = "application/json")
    public String delete(HttpServletRequest request, @ApiParam(name = "code", value = "帖子编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = threadsInfoDomain.delete(code);
            LOGGER.info("删除帖子[admin.ThreadsInfoController.delete]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("删除帖子[admin.ThreadsInfoController.delete]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("删除帖子[admin.ThreadsInfoController.delete]异常，参数：{}", code, e);
            RespHeader respHeader =
                    new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
