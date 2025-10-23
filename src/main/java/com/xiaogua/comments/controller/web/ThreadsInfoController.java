package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.comments.CommentsCommonPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.threads.ThreadsPageInfo;
import com.xiaogua.comments.bean.threads.ThreadsSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ThreadsInfoDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖子Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "帖子Web Controller")
@RestController
@RequestMapping("/api/threads")
public class ThreadsInfoController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(
        ThreadsInfoController.class);

    @Autowired
    ThreadsInfoDomain threadsInfoDomain;

    /**
     * 保存帖子
     *
     * @param request  request
     * @param submit 提交帖子信息
     * @return
     */
    @ApiOperation(value = "保存帖子")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody ThreadsSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsInfoDomain.save(submit, userLoginState);
            LOGGER.info("保存帖子[ThreadsInfoController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存帖子[ThreadsInfoController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存帖子[ThreadsInfoController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 点赞帖子
     *
     * @param code 帖子编号
     * @param request request
     * @return
     */
    @ApiOperation(value = "点赞帖子")
    @PutMapping(value = "/like", produces = "application/json")
    public String like(@ApiParam(name = "code", value = "帖子编号", required = true) @Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsInfoDomain.like(code, userLoginState.getCode());
            LOGGER.info("点赞帖子[ThreadsInfoController.like]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("点赞帖子[ThreadsInfoController.like]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("点赞帖子[ThreadsInfoController.like]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 取消点赞帖子
     *
     * @param code 帖子编号
     * @param request request
     * @return
     */
    @PutMapping(value = "/unlike", produces = "application/json")
    public String unlike(@ApiParam(name = "code", value = "帖子编号", required = true) @Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsInfoDomain.unlike(code, userLoginState.getCode());
            LOGGER.info("取消点赞帖子[ThreadsInfoController.unlike]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("取消点赞帖子[ThreadsInfoController.unlike]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("取消点赞帖子[ThreadsInfoController.unlike]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 分页获取我的帖子
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取我的帖子")
    @PostMapping(value = "/page/my", produces = "application/json")
    public String getMyCommentsPage(HttpServletRequest request, @RequestBody ThreadsPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsInfoDomain.getMyCommentsPage(pageInfo, userLoginState);
            LOGGER.info("分页获取我的帖子[ThreadsInfoController.getMyCommentsPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的帖子[ThreadsInfoController.getMyCommentsPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的帖子[ThreadsInfoController.getMyCommentsPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
