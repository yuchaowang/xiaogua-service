package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.activity.ActivityPageInfo;
import com.xiaogua.comments.bean.brand.BrandPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.domain.ActivityDomain;
import com.xiaogua.comments.domain.BrandDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "活动Web Controller")
@RestController("api.ActivityController")
@RequestMapping("/api/activity")
public class ActivityController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActivityController.class);

    @Autowired
    ActivityDomain activityDomain;

    /**
     * 分页获取活动信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("分页获取活动信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String page(HttpServletRequest request, @RequestBody ActivityPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            builder = activityDomain.getActivityPage(pageInfo);
            LOGGER.info("分页获取活动信息[ActivityController.page]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取活动信息[ActivityController.page]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取活动信息[ActivityController.page]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取单个活动详情
     *
     * @param request request
     * @param code    品牌编号
     * @return
     */
    @ApiOperation("获取单个活动详情")
    @GetMapping(value = "/get/{code}", produces = "application/json")
    public String get(HttpServletRequest request, @PathVariable("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = activityDomain.get(code);
            LOGGER.info("获取单个活动详情[ActivityController.get]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单个活动详情[ActivityController.get]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单个活动详情[ActivityController.get]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
