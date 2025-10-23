package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.knowledge.KnowledgePageInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgeSubmit;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.KnowledgeDomain;
import com.xiaogua.comments.domain.KnowledgeFileDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 干货信息Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@RestController
@Api(tags = "干货信息Web Controller")
@RequestMapping("/api/knowledge")
public class KnowledgeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeController.class);

    @Autowired
    KnowledgeDomain knowledgeDomain;

    @Autowired
    KnowledgeFileDomain knowledgeFileDomain;

    /**
     * 保存干货信息
     *
     * @param request request
     * @param submit submit
     * @return
     */
    @ApiOperation(value = "保存干货信息")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody KnowledgeSubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = knowledgeDomain.save(submit, userLoginState.getCode());
            LOGGER.info("保存干货信息[KnowledgeController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存干货信息[KnowledgeController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存干货信息[KnowledgeController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 分页获取干货信息
     *
     * @param request  request
     * @param pageInfo pageInfo
     * @return
     */
    @ApiOperation(value = "分页获取干货信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getPage(HttpServletRequest request, @RequestBody KnowledgePageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            builder = knowledgeDomain.getPage(pageInfo);
            LOGGER.info("分页获取干货信息[KnowledgeController.getPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取干货信息[KnowledgeController.getPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取干货信息[KnowledgeController.getPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取干货信息详情
     *
     * @param request  request
     * @param code code
     * @return
     */
    @ApiOperation(value = "获取干货信息详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String get(HttpServletRequest request, @ApiParam(name = "code", value = "干货编号", required = true) @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = knowledgeDomain.get(code);
            LOGGER.info("获取干货信息详情[KnowledgeController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取干货信息详情[KnowledgeController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取干货信息详情[KnowledgeController.get]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 下载干货文件信息
     * @param request request
     * @param code code
     * @param useCredit 是否消费积分
     * @return
     */
    @ApiOperation(value = "下载干货文件信息")
    @GetMapping(value = "/downKnowledgeFile", produces = "application/json")
    public String downKnowledgeFile(HttpServletRequest request, @ApiParam(name = "code", value = "干货编号", required = true) @RequestParam("code") String code, @RequestParam(value = "useCredit", required = false) String useCredit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = knowledgeFileDomain.downKnowledgeFile(code, useCredit, userLoginState.getCode());
            LOGGER.info("获取干货信息详情[KnowledgeController.downKnowledgeFile]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取干货信息详情[KnowledgeController.downKnowledgeFile]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取干货信息详情[KnowledgeController.downKnowledgeFile]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 分页获取我的干货信息
     *
     * @param request  request
     * @param pageInfo pageInfo
     * @return
     */
    @ApiOperation(value = "分页获取我的干货信息")
    @PostMapping(value = "/my/page", produces = "application/json")
    public String getMyPage(HttpServletRequest request, @RequestBody KnowledgePageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isLogin(request);
            builder = knowledgeDomain.getMyPage(pageInfo, userLoginState);
            LOGGER.info("分页获取我的干货信息[KnowledgeController.getMyPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取我的干货信息[KnowledgeController.getMyPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取我的干货信息[KnowledgeController.getMyPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
