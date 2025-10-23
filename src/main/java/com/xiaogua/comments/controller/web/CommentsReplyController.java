package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.commentsReply.CommentsReplySubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CommentsReplyDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论回复Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController
@Api(tags = "评论回复Web Controller")
@RequestMapping("/api/commentsReply")
public class CommentsReplyController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsReplyController.class);

    @Autowired
    CommentsReplyDomain commentsReplyDomain;

    /**
     * 新增评论回复
     *
     * @param request request
     * @param submit  评论回复信息
     * @return
     */
    @ApiOperation(value = "新增评论回复")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody CommentsReplySubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsReplyDomain.save(submit, userLoginState.getCode());
            LOGGER.info("新增评论回复[CommentsReplyController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("新增评论回复[CommentsReplyController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("新增评论回复[CommentsReplyController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 点赞评论回复
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "点赞评论回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "评论回复编号", required = true)})
    @PutMapping(value = "/like", produces = "application/json")
    public String like(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsReplyDomain.like(code, userLoginState.getCode());
            LOGGER.info("点赞评论回复[CommentsReplyController.like]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("点赞评论回复[CommentsReplyController.like]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("点赞评论回复[CommentsReplyController.like]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 评论回复取消点赞
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "评论回复取消点赞")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "评论回复编号", required = true)})
    @PutMapping(value = "/unlike", produces = "application/json")
    public String unlike(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsReplyDomain.unlike(code, userLoginState.getCode());
            LOGGER.info("评论回复取消点赞[CommentsReplyController.unlike]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("评论回复取消点赞[CommentsReplyController.unlike]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("评论回复取消点赞[CommentsReplyController.unlike]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 删除评论回复
     *
     * @param code
     * @param request
     * @return
     */
    @ApiOperation(value = "删除评论回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "评论回复编号", required = true)})
    @DeleteMapping(value = "/delete", produces = "application/json")
    public String delete(@Param("code") String code, HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = commentsReplyDomain.delete(code, userLoginState.getCode());
            LOGGER.info("删除评论回复[CommentsReplyController.delete]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("删除评论回复[CommentsReplyController.delete]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("删除评论回复[CommentsReplyController.delete]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
