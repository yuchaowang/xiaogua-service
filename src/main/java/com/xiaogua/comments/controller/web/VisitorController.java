package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.user.UserSubmit;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.bl.VisitorBl;
import com.xiaogua.comments.config.WXConfig;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.dal.mapper.VisitorMapper;
import com.xiaogua.comments.domain.UserDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.WxUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 游客controller
 *
 */
@Api(tags = "游客Web Controller")
@RestController
@RequestMapping("/api/visitor")
public class VisitorController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VisitorController.class);

    @Autowired
    private VisitorBl visitorBl;

    @Autowired
    UserDomain userDomain;

    @Autowired
    WxUtil wxUtil;

    /**
     * 新增游客
     *
     * @return
     */
    @ApiOperation(value = "新增游客")
    @PostMapping(value = "/add", produces = "application/json")
    public String update(HttpServletRequest request, @RequestBody JSONObject jsonObject) {
        ResponseBuilder builder = new ResponseBuilder();
        String code = jsonObject.getString("code");
        try {
            JSONObject jo = wxUtil.getWxInfo(code);
            String openId = jo.getString("openid");
            Integer count = visitorBl.selectByOpenId(openId);
            if (count == 0) {
                visitorBl.createVisitor(openId);
                LOGGER.info("新增游客[VisitorController.add]正常，参数：{}", JSON.toJSONString(openId));
            }
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("新增游客[VisitorController.add]告警，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("新增游客[VisitorController.add]异常，参数：{}", JSON.toJSONString(jsonObject), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

}
