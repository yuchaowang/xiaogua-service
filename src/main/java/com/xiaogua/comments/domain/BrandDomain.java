package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.brand.BrandAdmin;
import com.xiaogua.comments.bean.brand.BrandPage;
import com.xiaogua.comments.bean.brand.BrandPageInfo;
import com.xiaogua.comments.bean.brand.BrandRest;
import com.xiaogua.comments.bean.brand.BrandSimpleRest;
import com.xiaogua.comments.bean.brand.BrandSubmit;
import com.xiaogua.comments.bean.brand.ErrorMsg;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bl.BrandBl;
import com.xiaogua.comments.bl.CommentsBl;
import com.xiaogua.comments.controller.web.api.BrandController;
import com.xiaogua.comments.dal.entity.Brand;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 品牌 domain
 *
 * @author wangyc
 * @date 2019-6-12 11:47:53
 **/

@Service
public class BrandDomain {

    @Autowired
    private BrandBl brandBl;

    @Autowired
    CommentsBl commentsBl;

    @Autowired
    CommentsDomain commentsDomain;

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    /**
     * 分页获取品牌信息
     *
     * @param pageInfo
     * @return
     */
    public ResponsePageBuilder getBrandsPage(BrandPageInfo pageInfo) {
        BrandPage brandPage = brandBl.getBrandsPage(pageInfo, false);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换品牌信息
        if (brandPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToBrandRests(brandPage.getBrands()));
        }

        builder.setPageInfo(brandPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取品牌信息
     *
     * @param pageInfo
     * @return
     */
    public ResponsePageBuilder adminGetBrandsPage(BrandPageInfo pageInfo) {
        BrandPage brandPage = brandBl.getBrandsPage(pageInfo, true);
        ResponsePageBuilder builder = new ResponsePageBuilder();

        // 转换品牌信息
        if (brandPage.getPagingInfo().getTotalCount() > 0) {
            builder.setData(convertToBrandAdmins(brandPage.getBrands()));
        }

        builder.setPageInfo(brandPage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 批量转换品牌信息Rest
     *
     * @param brands
     * @return
     */
    private List<BrandRest> convertToBrandRests(List<Brand> brands) {
        List<BrandRest> brandRestList = new ArrayList<>();
        for (Brand brand : brands) {
            brandRestList.add(convertToBrandRest(brand));
        }

        return brandRestList;
    }

    /**
     * 更新品牌评论数
     *
     * @param brandCode 品牌编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateCommentNum(String brandCode) {
        VerifyParamsUtil.hasText(brandCode, "品牌编号不可为空");
        Brand brand = brandBl.getBrand(brandCode);
        if (brand == null) {
            throw new CommentsRuntimeException(-1, "品牌信息不存在");
        }

        Integer commentNum = commentsBl.getCountByToCode(brandCode);
        brand.setCommentNum(commentNum);
        brandBl.saveBrand(brand);
    }

    /**
     * 批量转换品牌信息Admin
     *
     * @param brands
     * @return
     */
    private List<BrandAdmin> convertToBrandAdmins(List<Brand> brands) {
        List<BrandAdmin> brandAdminList = new ArrayList<>();
        // 转换品牌logo文件信息
        //        GetFileInfosResponse fileInfosResponse =
        //            fileDomain.getFileInfos(brands.stream().map(b -> b.getLogoCode()).collect(Collectors.toList()));
        for (Brand brand : brands) {
            brandAdminList.add(convertToBrandAdmin(brand, ""));
        }

        return brandAdminList;
    }

    /**
     * 转换品牌信息Rest
     *
     * @param brand   品牌信息
     * @return
     */
    private BrandRest convertToBrandRest(Brand brand) {
        BrandRest brandRest = new BrandRest(brand);
        brandRest.setLikeNum(commentsBl.sumLikeByBrandCode(brand.getCode()));
        brandRest.setCommentsInfoRest(commentsDomain.getMaxLikeNumCommentsInfoRest(brand.getCode()));
        return brandRest;
    }

    /**
     * 转换品牌信息SimpleRest
     *
     * @param brand   品牌信息
     * @return
     */
    private BrandSimpleRest convertToBrandSimpleRest(Brand brand) {
        BrandSimpleRest brandRest = new BrandSimpleRest(brand);
        brandRest.setLikeNum(commentsBl.sumLikeByBrandCode(brand.getCode()));
        return brandRest;
    }

    /**
     * 转换品牌信息Admin
     *
     * @param brand   品牌信息
     * @param logoUrl logo地址
     * @return
     */
    private BrandAdmin convertToBrandAdmin(Brand brand, String logoUrl) {
        BrandAdmin brandAdmin = new BrandAdmin(brand);
        return brandAdmin;
    }

    /**
     * 获取单个品牌详情
     *
     * @param code
     * @return
     */
    public ResponseBuilder getBrand(String code) {
        VerifyParamsUtil.hasText(code, "品牌编号不可为空");

        Brand brand = brandBl.getBrand(code);
        ResponseBuilder builder = new ResponseBuilder();
        if (brand != null) {
            builder.setData(convertToBrandRests(Arrays.asList(brand)).get(0));
        }
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取品牌简易信息
     * @param code 品牌编号
     * @return
     */
    public BrandSimpleRest getBrandSimpleRest(String code) {
        Brand brand = brandBl.getBrand(code);
        BrandSimpleRest rest = null;
        if (brand != null) {
            rest = convertToBrandSimpleRest(brand);
        }
        return rest;
    }

    /**
     * admin-获取单个品牌详情
     *
     * @param code
     * @return
     */
    public ResponseBuilder adminGetBrand(String code) {
        VerifyParamsUtil.hasText(code, "品牌编号不可为空");

        Brand brand = brandBl.getBrandWithDel(code);
        ResponseBuilder builder = new ResponseBuilder();
        if (brand != null) {
            builder.setData(convertToBrandAdmins(Arrays.asList(brand)).get(0));
        }
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 删除品牌
     *
     * @param code 品牌编号
     * @return
     */
    public ResponseBuilder delete(String code) {
        VerifyParamsUtil.hasText(code, "品牌编号不可为空");

        brandBl.delete(code);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 保存品牌信息
     *
     * @param submit
     * @return
     */
    public ResponseBuilder save(BrandSubmit submit) {
        // 1.验证参数
        VerifyParamsUtil.notNull(submit, "品牌信息不可为空");
        List<ErrorMsg> errorMsgs = validateBrand(submit);
        if (!CollectionUtils.isEmpty(errorMsgs)) {
            errorMsgs.forEach(e -> {
                LOGGER.error(e.getName() + " : " + e.getMsg());
            });
            throw new CommentsRuntimeException(-1, String.format("品牌验证不通过，%s", errorMsgs.get(0).getMsg()));
        }

        // 2.转换品牌信息
        brandBl.saveBrand(submit);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        return builder;
    }

    /**
     * 验证品牌信息
     *
     * @param brand 品牌信息
     * @return
     */
    public ResponseBuilder validate(BrandSubmit brand) {
        // 1.验证参数
        VerifyParamsUtil.notNull(brand, "品牌信息不可为空");

        ResponseBuilder builder = new ResponseBuilder();

        List<ErrorMsg> errorMsgs = validateBrand(brand);

        if (CollectionUtils.isEmpty(errorMsgs)) {
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        } else {
            RespHeader respHeader = new RespHeader(1, "验证失败");
            builder.setRespHeader(respHeader);
            builder.setData(errorMsgs);
        }
        return builder;
    }

    /**
     * 验证品牌信息
     *
     * @param brand
     * @return
     */
    public List<ErrorMsg> validateBrand(BrandSubmit brand) {
        List<ErrorMsg> errorMsgs = new ArrayList<>();
        if (StringUtils.isEmpty(brand.getNameEn())) {
            errorMsgs.add(new ErrorMsg("nameEn", "英文品牌名不可为空"));
        } else {
            Map<String, Boolean> validateName =
                brandBl.validateBrandName(brand.getCode(), brand.getNameEn(), brand.getNameCn());
            if (validateName.get("IsEnReapted")) {
                errorMsgs.add(new ErrorMsg("nameEn", "英文名与其他品牌名相同"));
            }

            if (validateName.get("IsCnReapted")) {
                errorMsgs.add(new ErrorMsg("nameCn", "中文名与其他品牌名相同"));
            }
        }

        if (!StringUtils.isEmpty(brand.getSeries()) && brand.getSeries().length() > 100) {
            errorMsgs.add(new ErrorMsg("series", "主营产品不可超过100个字"));
        }

        if (!StringUtils.isEmpty(brand.getApplication()) && brand.getApplication().length() > 100) {
            errorMsgs.add(new ErrorMsg("application", "应用领域不可超过100个字"));
        }

        if (!StringUtils.isEmpty(brand.getBrief()) && brand.getBrief().length() > 250) {
            errorMsgs.add(new ErrorMsg("brief", "品牌介绍不可超过250个字"));
        }

        if (!StringUtils.isEmpty(brand.getUrl()) && brand.getUrl().length() > 100) {
            errorMsgs.add(new ErrorMsg("url", "官网地址不可超过100个字"));
        }

        return errorMsgs;
    }

    public ResponseBuilder getBrandOrderByCommentCreateDate() {
        List<String> brandCodes = commentsBl.getDistinctBrandCodeOrderByCommentCreateDate(20);

        ResponseBuilder builder = new ResponseBuilder();
        if (!CollectionUtils.isEmpty(brandCodes)) {
            List<Brand> brands = brandBl.getBrandByBrandCodesOrder(brandCodes);
            builder.setData(convertToBrandRests(brands));
        } else {
            builder.setData(new ArrayList());
        }

        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }
}


