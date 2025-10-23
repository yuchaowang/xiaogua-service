package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.comments.CommentsCommonPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CommentsDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController("api.CommentsController")
@RequestMapping("/api/comments")
public class CommentsController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsController.class);

    @Autowired
    CommentsDomain commentsDomain;

    /**
     * 分页获取评论信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @PostMapping(value = "/page", produces = "application/json")
    public String getCommentsPage(HttpServletRequest request, @RequestBody CommentsCommonPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = getLoginInfo(request);
            builder = commentsDomain.getCommentsPage(pageInfo, userLoginState);
            LOGGER.info("分页获取评论信息[CommentsController.getCommentsPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取评论信息[CommentsController.getCommentsPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取评论信息[CommentsController.getCommentsPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取单条评论信息
     *
     * @param request request
     * @param code    评论编号
     * @return
     */
    @GetMapping(value = "/get", produces = "application/json")
    public String getComment(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = getLoginInfo(request);
            builder = commentsDomain.getComment(code, userLoginState);
            LOGGER.info("获取单条评论信息[CommentsController.getComment]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单条评论信息[CommentsController.getComment]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单条评论信息[CommentsController.getComment]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
