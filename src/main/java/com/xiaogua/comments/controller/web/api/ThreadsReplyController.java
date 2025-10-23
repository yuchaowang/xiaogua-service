package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.threadsReply.ThreadsReplyPageInfo;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ThreadsReplyDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 帖子回复Web API Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "帖子回复Web API Controller")
@RestController("api.ThreadsReplyController")
@RequestMapping("/api/threadsReply")
public class ThreadsReplyController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsReplyController.class);

    @Autowired
    ThreadsReplyDomain threadsReplyDomain;

    /**
     * 分页获取帖子回复信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "分页获取帖子回复信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getThreadsReplyPage(HttpServletRequest request, @RequestBody ThreadsReplyPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = getUserLoginState(request);
            builder = threadsReplyDomain.getThreadsReplyPage(pageInfo, userLoginState);
            LOGGER.info("分页获取帖子回复信息[api.ThreadsReplyController.getThreadsReplyPage]正常，参数：{}",
                        JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取帖子回复信息[api.ThreadsReplyController.getThreadsReplyPage]告警，参数：{}",
                        JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取帖子回复信息[api.ThreadsReplyController.getThreadsReplyPage]异常，参数：{}",
                         JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
