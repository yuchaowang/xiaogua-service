package com.xiaogua.comments.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.messageBoard.MessageBoardPageInfo;
import com.xiaogua.comments.bean.messageBoard.MessageBoardReply;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.MessageBoardDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 留言板Admin Controller
 *
 * @author wangyc
 * @date 2021-02-18 15:13:13
 */
@Api(tags = "留言板Admin Controller")
@RestController(value = "admin.MessageBoardController")
@RequestMapping("/api/admin/messageboard")
public class MessageBoardController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBoardController.class);

    @Autowired
    MessageBoardDomain messageBoardDomain;

    /**
     * admin-分页获取留言信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "管理平台-分页获取留言信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getPage(HttpServletRequest request, @RequestBody MessageBoardPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = messageBoardDomain.getAdminPage(pageInfo);
            LOGGER.info("分页获取留言信息[Admin.MessageBoardController.getPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取留言信息[Admin.MessageBoardController.getPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取留言信息[Admin.MessageBoardController.getPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-获取单条留言详情
     *
     * @param request request
     * @param code    留言编号
     * @return
     */
    @ApiOperation(value = "管理平台-获取单条留言详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String get(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = messageBoardDomain.getAdmin(code);
            LOGGER.info("获取单条留言详情[Admin.MessageBoardController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单条留言详情[Admin.MessageBoardController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单条留言详情[Admin.MessageBoardController.get]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-阅读留言
     *
     * @param request request
     * @param code    留言编号
     * @return
     */
    @ApiOperation(value = "管理平台-阅读留言")
    @PutMapping(value = "/read", produces = "application/json")
    public String read(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = messageBoardDomain.read(code);
            LOGGER.info("阅读留言[Admin.MessageBoardController.read]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("阅读留言[Admin.MessageBoardController.read]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("阅读留言[Admin.MessageBoardController.read]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                    CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-批量阅读留言
     *
     * @param request request
     * @param codes   留言编号数组
     * @return
     */
    @ApiOperation(value = "管理平台-批量阅读留言（返回data为成功阅读数量，报错（不存在，已阅）不阻断）")
    @PostMapping(value = "/batch/read", produces = "application/json")
    public String batchRead(HttpServletRequest request, @RequestBody List<String> codes) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = messageBoardDomain.batchRead(codes);
            LOGGER.info("批量阅读留言[Admin.MessageBoardController.batchRead]正常，参数：{}", JSONArray.toJSONString(codes));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("批量阅读留言[Admin.MessageBoardController.batchRead]告警，参数：{}", JSONArray.toJSONString(codes), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("批量阅读留言[Admin.MessageBoardController.batchRead]异常，参数：{}", JSONArray.toJSONString(codes), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                    CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-回复留言
     *
     * @param request request
     * @param reply   回复留言信息
     * @return
     */
    @ApiOperation(value = "管理平台-回复留言")
    @PostMapping(value = "/reply", produces = "application/json")
    public String reply(HttpServletRequest request, @RequestBody MessageBoardReply reply) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = messageBoardDomain.reply(reply, userLoginState.getCode());
            LOGGER.info("回复留言[Admin.MessageBoardController.reply]正常，参数：{}", JSON.toJSONString(reply));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("回复留言[Admin.MessageBoardController.reply]告警，参数：{}", JSON.toJSONString(reply), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("回复留言[Admin.MessageBoardController.reply]异常，参数：{}", JSON.toJSONString(reply), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
