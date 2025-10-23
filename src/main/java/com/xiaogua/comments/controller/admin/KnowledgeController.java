package com.xiaogua.comments.controller.admin;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.knowledge.KnowledgeAdminPageInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgeAudit;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.KnowledgeDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

/**
 * 干货信息Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "干货信息 Admin Web Controller")
@RestController("admin.KnowledgeController")
@RequestMapping("/api/admin/knowledge")
public class KnowledgeController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(
        KnowledgeController.class);

    @Autowired
    KnowledgeDomain knowledgeDomain;

    /**
     * 审核通过干货信息
     *
     * @param request  request
     * @param audit audit
     * @return
     */
    @ApiOperation(value = "审核通过干货信息")
    @PostMapping(value = "/auditSuccess", produces = "application/json")
    public String auditSuccess(HttpServletRequest request, @RequestBody KnowledgeAudit audit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = knowledgeDomain.auditSuccess(audit, userLoginState.getCode());
            LOGGER.info("审核通过干货信息[Admin.KnowledgeController.auditSuccess]正常，参数：{}", JSON.toJSONString(audit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("审核通过干货信息[Admin.KnowledgeController.auditSuccess]告警，参数：{}", JSON.toJSONString(audit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("审核通过干货信息[Admin.KnowledgeController.auditSuccess]异常，参数：{}", JSON.toJSONString(audit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 审核不通过干货信息
     *
     * @param request  request
     * @param audit audit
     * @return
     */
    @ApiOperation(value = "审核不通过干货信息")
    @PostMapping(value = "/auditFailed", produces = "application/json")
    public String auditFailed(HttpServletRequest request, @RequestBody KnowledgeAudit audit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = knowledgeDomain.auditFailed(audit, userLoginState.getCode());
            LOGGER.info("审核不通过干货信息[Admin.KnowledgeController.auditFailed]正常，参数：{}", JSON.toJSONString(audit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("审核不通过干货信息[Admin.KnowledgeController.auditFailed]告警，参数：{}", JSON.toJSONString(audit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("审核不通过干货信息[Admin.KnowledgeController.auditFailed]异常，参数：{}", JSON.toJSONString(audit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 更新干货积分
     *
     * @param request  request
     * @param code 干货编号
     * @param credit 更新积分
     * @return
     */
    @ApiOperation(value = "更新干货积分")
    @PutMapping(value = "/updateCredit", produces = "application/json")
    public String updateCredit(HttpServletRequest request,
        @ApiParam(name = "code", value = "干货编号") @RequestParam("code") String code,
        @ApiParam(name = "credit", value = "更新积分") @RequestParam("credit") Integer credit,
        @ApiParam(name = "categoryType", value = "干货分类") @RequestParam("categoryType") Integer categoryType,
        @ApiParam(name = "priority", value = "排序权重") @RequestParam("priority") Integer priority) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = knowledgeDomain.updateCredit(code, credit, categoryType, priority, userLoginState.getCode());
            LOGGER.info("更新干货积分[Admin.KnowledgeController.updateCredit]正常，参数：{}", String.format("code: %s, credit: %s,", code, credit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("更新干货积分[Admin.KnowledgeController.updateCredit]告警，参数：{}", String.format("code: %s, credit: %s", code, credit));
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("更新干货积分[Admin.KnowledgeController.updateCredit]异常，参数：{}", String.format("code: %s, credit: %s", code, credit));
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-分页获取干货信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation(value = "admin-分页获取干货信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getPage(HttpServletRequest request, @RequestBody KnowledgeAdminPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = knowledgeDomain.getAdminPage(pageInfo);
            LOGGER.info("分页获取干货信息[Admin.KnowledgeController.getPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取干货信息[Admin.KnowledgeController.getPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取干货信息[Admin.KnowledgeController.getPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-获取干货信息详情
     *
     * @param request  request
     * @param code code
     * @return
     */
    @ApiOperation(value = "admin-获取干货信息详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String get(HttpServletRequest request, @ApiParam(name = "code", value = "干货编号") @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = knowledgeDomain.getAdmin(code);
            LOGGER.info("获取干货信息详情[Admin.KnowledgeController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取干货信息详情[Admin.KnowledgeController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取干货信息详情[Admin.KnowledgeController.get]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
