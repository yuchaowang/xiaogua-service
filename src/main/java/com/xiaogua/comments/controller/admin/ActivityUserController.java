package com.xiaogua.comments.controller.admin;


import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.activity.ActivityUserPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.domain.ActivityUserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动用户Admin Controller
 *
 * @author wangyc
 * @date 2021-5-16
 */
@Api(tags = "活动用户Admin Controller")
@RestController(value = "admin.ActivityUserController")
@RequestMapping("/api/admin/activityuser")
public class ActivityUserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityUserController.class);

    @Autowired
    ActivityUserDomain activityUserDomain;

    /**
     * admin-分页获取活动用户信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("管理平台-分页获取活动用户信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getActivityUsersPage(HttpServletRequest request, @RequestBody ActivityUserPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = activityUserDomain.adminGetActivityUsersPage(pageInfo);
            LOGGER.info("分页获取活动用户信息[Admin.ActivityUserController.adminGetActivityUsersPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取活动用户信息[Admin.ActivityUserController.adminGetActivityUsersPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取活动用户信息[Admin.ActivityUserController.adminGetActivityUsersPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }


    /**
     * 下载参加活动用户信息
     * @param request request
     * @param response response
     * @param activityCode 活动编号
     * @return
     */
    @GetMapping(value = "/download")
    public String downloadUsers(HttpServletRequest request, HttpServletResponse response, @Param("activityCode") String activityCode) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            activityUserDomain.downloadUsers(response, activityCode);
            LOGGER.info("下载参加活动用户信息[Admin.ActivityUserController.downloadUsers]正常，参数：{}", activityCode);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("下载参加活动用户信息[Admin.ActivityUserController.downloadUsers]告警，参数：{}", activityCode, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("下载参加活动用户信息[Admin.ActivityUserController.downloadUsers]异常，参数：{}", activityCode, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return "";
    }

}
