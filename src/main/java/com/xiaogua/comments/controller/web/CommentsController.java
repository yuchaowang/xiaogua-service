package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.comments.CommentsCommonPageInfo;
import com.xiaogua.comments.bean.comments.CommentsInfoSubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CommentsDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController
@Api(tags = "评论Web Controller")
@RequestMapping("/api/comments")
public class CommentsController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    CommentsDomain commentsDomain;

    /**
     * 分页获取我的评论信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取我的评论信息")
    @PostMapping(value = "/page/my", produces = "application/json")
    public String getMyCommentsPage(HttpServletRequest request, @RequestBody CommentsCommonPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsDomain.getMyCommentsPage(pageInfo, userLoginState);
            LOGGER.info("分页获取我的评论信息[CommentsController.getMyCommentsPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的评论信息[CommentsController.getMyCommentsPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的评论信息[CommentsController.getMyCommentsPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 保存评论
     *
     * @param request      request
     * @param commentsInfo 评论信息
     * @return
     */
    @ApiOperation(value = "保存评论")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody CommentsInfoSubmit commentsInfo) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsDomain.save(commentsInfo, userLoginState.getCode());
            LOGGER.info("保存评论[CommentsController.save]正常，参数：{}", JSON.toJSONString(commentsInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存评论[CommentsController.save]告警，参数：{}", JSON.toJSONString(commentsInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存评论[CommentsController.save]异常，参数：{}", JSON.toJSONString(commentsInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 点赞评论
     *
     * @param request
     * @return
     */
    @PutMapping(value = "/like", produces = "application/json")
    public String like(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsDomain.like(code, userLoginState.getCode());
            LOGGER.info("点赞评论[CommentsController.like]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("点赞评论[CommentsController.like]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("点赞评论[CommentsController.like]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 评论取消点赞
     *
     * @param request
     * @return
     */
    @PutMapping(value = "/unlike", produces = "application/json")
    public String unlike(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsDomain.unlike(code, userLoginState.getCode());
            LOGGER.info("评论取消点赞[CommentsController.unlike]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("评论取消点赞[CommentsController.unlike]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("评论取消点赞[CommentsController.unlike]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 删除评论
     *
     * @param request
     * @return
     */
    //    @DeleteMapping(value = "/delete", produces = "application/json")
    @Deprecated
    public String delete(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsDomain.delete(code, userLoginState.getCode());
            LOGGER.info("删除评论[CommentsController.delete]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("删除评论[CommentsController.delete]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("删除评论[CommentsController.delete]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
