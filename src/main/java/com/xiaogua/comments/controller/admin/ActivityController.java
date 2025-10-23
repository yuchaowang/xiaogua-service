package com.xiaogua.comments.controller.admin;


import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.activity.ActivityPageInfo;
import com.xiaogua.comments.bean.activity.ActivitySubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ActivityDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动Admin Controller
 *
 * @author wangyc
 * @date 2021-6-2
 */
@Api(tags = "活动Admin Controller")
@RestController(value = "admin.ActivityController")
@RequestMapping("/api/admin/activity")
public class ActivityController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    ActivityDomain activityDomain;

    /**
     * admin-获取活动信息
     *
     * @param request  request
     * @param code 活动编号
     * @return
     */
    @ApiOperation("管理平台-获取活动信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "活动编号", required = true)})
    @GetMapping(value = "/get", produces = "application/json")
    public String get(HttpServletRequest request, @Param("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.adminGet(code);
            LOGGER.info("获取活动信息[Admin.ActivityController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取活动信息[Admin.ActivityController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取活动信息[Admin.ActivityController.get]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-分页获取活动用户信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("管理平台-分页获取活动信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getActivitysPage(HttpServletRequest request, @RequestBody ActivityPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.adminGetActivitysPage(pageInfo);
            LOGGER.info("分页获取活动信息[Admin.ActivityController.getActivitysPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取活动信息[Admin.ActivityController.getActivitysPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取活动信息[Admin.ActivityController.getActivitysPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }


    /**
     * 保存活动信息
     *
     * @param request request
     * @param submit submit
     * @return
     */
    @ApiOperation(value = "保存活动信息，有编号视为更新，仅待发布活动可以更新")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody ActivitySubmit submit) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            UserLoginState userLoginState = isAdmin(request);
            builder = activityDomain.save(submit, userLoginState.getCode());
            LOGGER.info("保存活动信息[Admin.ActivityController.save]正常，参数：{}", JSON.toJSONString(submit));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存活动信息[Admin.ActivityController.save]告警，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存活动信息[Admin.ActivityController.save]异常，参数：{}", JSON.toJSONString(submit), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 发布活动
     *
     * @param request request
     * @param code 活动编号
     * @return
     */
    @ApiOperation(value = "发布活动，活动变为可报名")
    @PostMapping(value = "/deploy/{code}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "活动编号", required = true)})
    public String deploy(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.deploy(code);
            LOGGER.info("发布活动[Admin.ActivityController.deploy]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("发布活动[Admin.ActivityController.deploy]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("发布活动[Admin.ActivityController.deploy]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }


    /**
     * 开启活动
     *
     * @param request request
     * @param code 活动编号
     * @return
     */
    @ApiOperation(value = "开启活动，活动变为进行中")
    @PostMapping(value = "/start/{code}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "活动编号", required = true)})
    public String start(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.start(code);
            LOGGER.info("开启活动[Admin.ActivityController.start]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("开启活动[Admin.ActivityController.start]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("开启活动[Admin.ActivityController.start]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 活动结束
     *
     * @param request request
     * @param code 活动编号
     * @return
     */
    @ApiOperation(value = "活动完成，活动变为活动结束")
    @PostMapping(value = "/close/{code}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "活动编号", required = true)})
    public String close(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.close(code);
            LOGGER.info("活动结束[Admin.ActivityController.close]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("活动结束[Admin.ActivityController.close]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("活动结束[Admin.ActivityController.close]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 活动取消
     *
     * @param request request
     * @param code 活动编号
     * @return
     */
    @ApiOperation(value = "活动取消，活动变为活动取消，除已完成状态都可以变为活动取消，进行中活动取消补偿机制待完善")
    @PostMapping(value = "/cancel/{code}", produces = "application/json")
    @ApiImplicitParams({@ApiImplicitParam(name = "code", value = "活动编号", required = true)})
    public String cancel(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.cancel(code);
            LOGGER.info("活动取消[Admin.ActivityController.cancel]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("活动取消[Admin.ActivityController.cancel]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("活动取消[Admin.ActivityController.cancel]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 更新直播地址
     *
     * @param request request
     * @param code 活动编号
     * @param streamingUrl 直播地址
     * @return
     */
    @ApiOperation(value = "更新直播地址")
    @PostMapping(value = "/streaming/update/streamingurl/{code}", produces = "application/json")
    public String updateStreamingUrl(HttpServletRequest request, @PathVariable("code") String code, @Param("streamingUrl") String streamingUrl) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.updateStreamingUrl(code, URLDecoder.decode(streamingUrl));
            LOGGER.info("更新直播地址[Admin.ActivityController.updateStreamingUrl]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("更新直播地址[Admin.ActivityController.updateStreamingUrl]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("更新直播地址[Admin.ActivityController.updateStreamingUrl]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 更新小程序直播id
     *
     * @param request request
     * @param code 活动编号
     * @param streamingId 小程序直播id
     * @return
     */
    @ApiOperation(value = "更新小程序直播id")
    @PostMapping(value = "/streaming/update/streamingid/{code}", produces = "application/json")
    public String updateStreamingId(HttpServletRequest request, @PathVariable("code") String code, @Param("streamingId") String streamingId) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = activityDomain.updateStreamingId(code, streamingId);
            LOGGER.info("更新小程序直播id[Admin.ActivityController.updateStreamingId]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("更新小程序直播id[Admin.ActivityController.updateStreamingId]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("更新小程序直播id[Admin.ActivityController.updateStreamingId]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
