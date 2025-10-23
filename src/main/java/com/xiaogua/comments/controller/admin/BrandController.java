package com.xiaogua.comments.controller.admin;

import com.alibaba.fastjson.JSON;
import com.xiaogua.comments.bean.brand.BrandPageInfo;
import com.xiaogua.comments.bean.brand.BrandSubmit;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.controller.web.BaseController;
import com.xiaogua.comments.domain.BrandDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 品牌Admin Controller
 *
 * @author wangyc
 * @date 2020-11-16 19:06:48
 */
@Api(tags = "品牌Admin Controller")
@RestController(value = "admin.BrandController")
@RequestMapping("/api/admin/brand")
public class BrandController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    BrandDomain brandDomain;

    /**
     * admin-分页获取品牌信息
     *
     * @param request  request
     * @param pageInfo 分页信息
     * @return
     */
    @ApiOperation("管理平台-分页获取品牌信息")
    @PostMapping(value = "/page", produces = "application/json")
    public String getBrandsPage(HttpServletRequest request, @RequestBody BrandPageInfo pageInfo) {
        ResponsePageBuilder builder = new ResponsePageBuilder();
        try {
            isAdmin(request);
            builder = brandDomain.adminGetBrandsPage(pageInfo);
            LOGGER.info("分页获取品牌信息[Admin.BrandController.getBrandsPage]正常，参数：{}", JSON.toJSONString(pageInfo));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("分页获取品牌信息[Admin.BrandController.getBrandsPage]告警，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("分页获取品牌信息[Admin.BrandController.getBrandsPage]异常，参数：{}", JSON.toJSONString(pageInfo), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-获取单个品牌详情
     *
     * @param request request
     * @param code    品牌编号
     * @return
     */
    @ApiOperation("管理平台-获取单个品牌详情")
    @GetMapping(value = "/get", produces = "application/json")
    public String getBrand(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = brandDomain.adminGetBrand(code);
            LOGGER.info("获取单个品牌详情[Admin.BrandController.getBrand]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("获取单个品牌详情[Admin.BrandController.getBrand]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("获取单个品牌详情[Admin.BrandController.getBrand]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-删除单个品牌
     *
     * @param request request
     * @param code    品牌编号
     * @return
     */
    @ApiOperation("管理平台-删除单个品牌")
    @DeleteMapping(value = "/delete", produces = "application/json")
    public String delete(HttpServletRequest request, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = brandDomain.delete(code);
            LOGGER.info("删除单个品牌[Admin.BrandController.delete]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("删除单个品牌[Admin.BrandController.delete]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("删除单个品牌[Admin.BrandController.delete]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-保存品牌
     *
     * @param request request
     * @param brand   品牌信息
     * @return
     */
    @ApiOperation("管理平台-保存品牌")
    @PostMapping(value = "/save", produces = "application/json")
    public String save(HttpServletRequest request, @RequestBody BrandSubmit brand) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = brandDomain.save(brand);
            LOGGER.info("保存品牌信息[Admin.BrandController.save]正常，参数：{}", JSON.toJSONString(brand));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("保存品牌信息[Admin.BrandController.save]告警，参数：{}", JSON.toJSONString(brand), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("保存品牌信息[Admin.BrandController.save]异常，参数：{}", JSON.toJSONString(brand), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }

    /**
     * admin-验证品牌信息
     *
     * @param request request
     * @param brand   品牌信息
     * @return
     */
    @ApiOperation("管理平台-验证品牌信息")
    @PostMapping(value = "/validate", produces = "application/json")
    public String validate(HttpServletRequest request, @RequestBody BrandSubmit brand) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            isAdmin(request);
            builder = brandDomain.validate(brand);
            LOGGER.info("验证品牌信息[Admin.BrandController.validate]正常，参数：{}", JSON.toJSONString(brand));
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("验证品牌信息[Admin.BrandController.validate]告警，参数：{}", JSON.toJSONString(brand), e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("验证品牌信息[Admin.BrandController.validate]异常，参数：{}", JSON.toJSONString(brand), e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100,
                                                   CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
