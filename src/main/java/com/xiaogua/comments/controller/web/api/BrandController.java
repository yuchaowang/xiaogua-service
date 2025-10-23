package com.xiaogua.comments.controller.web.api;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.brand.BrandPageInfo;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.domain.BrandDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 品牌Web Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "品牌Web Controller")
@RestController("api.BrandController")
@RequestMapping("/api/brand")
public class BrandController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandDomain brandDomain;

    /**
     * 分页获取品牌信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("分页获取品牌信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getBrandsPage(HttpServletRequest request, @RequestBody BrandPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            builder = brandDomain.getBrandsPage(pageInfo);
            LOGGER.info("分页获取品牌信息[BrandController.getBrandsPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取品牌信息[BrandController.getBrandsPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取品牌信息[BrandController.getBrandsPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取单个品牌详情
     *
     * @param request request
     * @param code    品牌编号
     * @return
     */
    @ApiOperation("获取单个品牌详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String getBrand(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = brandDomain.getBrand(code);
            LOGGER.info("获取单个品牌详情[BrandController.getBrand]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单个品牌详情[BrandController.getBrand]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单个品牌详情[BrandController.getBrand]异常，参数：{}", code, e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * 获取根据评论发布时间排序的品牌信息
     *
     * @param request
     * @return
     */
    @ApiOperation("获取根据评论发布时间排序的品牌信息")
    @GetMapping(value = "/get/byCommentCreateDate", produces = "application/json")
    public String getBrandOrderByCommentCreateDate(HttpServletRequest request) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = brandDomain.getBrandOrderByCommentCreateDate();
            LOGGER.info("获取根据评论发布时间排序的品牌信息[BrandController.getBrandOrderByCommentCreateDate]正常");
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取根据评论发布时间排序的品牌信息[BrandController.getBrandOrderByCommentCreateDate]告警", e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取根据评论发布时间排序的品牌信息[BrandController.getBrandOrderByCommentCreateDate]异常", e);
            RespHeader respHeader =
                new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
