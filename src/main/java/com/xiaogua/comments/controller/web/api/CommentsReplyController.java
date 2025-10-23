package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.comments.CommentsReplyPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.CommentsReplyDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 评论回复Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController("api.CommentsReplyController")
@RequestMapping("/api/commentsReply")
public class CommentsReplyController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsReplyController.class);

    @Autowired
    CommentsReplyDomain commentsReplyDomain;

    /**
     * 分页获取评论回复信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @PostMapping(value = "/page", produces = "application/json")
    public String getCommentsReplyPage(HttpServletRequest request, @RequestBody CommentsReplyPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = getUserLoginState(request);
            builder = commentsReplyDomain.getCommentsReplyPage(pageInfo, userLoginState);
            LOGGER.info("分页获取评论回复信息[api.CommentsReplyController.getCommentsReplyPage]正常，参数：{}",
                        JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取评论回复信息[api.CommentsReplyController.getCommentsReplyPage]告警，参数：{}",
                        JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取评论回复信息[api.CommentsReplyController.getCommentsReplyPage]异常，参数：{}",
                         JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
