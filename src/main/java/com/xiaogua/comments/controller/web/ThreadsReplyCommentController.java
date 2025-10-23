package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.threadsReplyComment.ThreadsReplyCommentSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ThreadsReplyCommentDomain;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖子回复评论Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController
@Api(tags = "帖子回复评论Web Controller")
@RequestMapping("/api/threadsReplyComment")
public class ThreadsReplyCommentController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyCommentController.class);

    @Autowired
    ThreadsReplyCommentDomain threadsReplyCommentDomain;

    /**
     * 新增帖子回复的评论
     *
     * @param request request
     * @param submit  帖子回复信息
     * @return
     */
    @ApiOperation(value = "新增帖子回复的评论")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody ThreadsReplyCommentSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsReplyCommentDomain.save(submit, userLoginState.getCode());
            LOGGER.info("新增帖子回复的评论[ThreadsReplyCommentController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("新增帖子回复的评论[ThreadsReplyCommentController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("新增帖子回复的评论[ThreadsReplyCommentController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 点赞帖子回复评论
     *
     * @param code 帖子回复评论编号
     * @param request request
     * @return
     */
    @ApiOperation(value = "点赞帖子回复评论")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "帖子回复评论编号", required = true)})
    @PutMapping(value = "/like", produces = "application/json")
    public String like(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsReplyCommentDomain.like(code, userLoginState.getCode());
            LOGGER.info("点赞帖子回复评论[ThreadsReplyCommentController.like]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("点赞帖子回复评论[ThreadsReplyCommentController.like]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("点赞帖子回复评论[ThreadsReplyCommentController.like]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 帖子回复评论取消点赞
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "帖子回复评论取消点赞")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "帖子回复评论编号", required = true)})
    @PutMapping(value = "/unlike", produces = "application/json")
    public String unlike(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = threadsReplyCommentDomain.unlike(code, userLoginState.getCode());
            LOGGER.info("帖子回复评论取消点赞[ThreadsReplyCommentController.unlike]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("帖子回复评论取消点赞[ThreadsReplyCommentController.unlike]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("帖子回复评论取消点赞[ThreadsReplyCommentController.unlike]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
