package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.brand.BrandPage;
import com.xiaogua.comments.bean.brand.BrandPageInfo;
import com.xiaogua.comments.bean.brand.BrandSubmit;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.Brand;
import com.xiaogua.comments.dal.mapper.BrandMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.PinyinUtils;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 品牌信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:38:06
 */
@Service
public class BrandBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrandBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private BrandMapper brandMapper;

    /**
     * 分页获取品牌信息
     *
     * @param pageInfo
     * @param deleted  是否要查询删除
     * @return
     */
    public BrandPage getBrandsPage(BrandPageInfo pageInfo, boolean deleted) {
        // 1.验证参数
        // 2.转换参数
        List<String> initals = null;
        if ("0-9".equals(pageInfo.getInital())) {
            initals = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");
        } else if (!StringUtils.isEmpty(pageInfo.getInital())) {
            initals = Arrays.asList(pageInfo.getInital());
        }

        // 3.获取数据
        int count = 0;

        if (deleted) {
            count = brandMapper
                .countWithDel(initals, StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword());
        } else {
            count =
                brandMapper.count(initals, StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword());
        }

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<Brand> brands = new ArrayList<>();
        if (deleted) {
            brands = brandMapper.findPageWithDel(pagingInfo.getIndex(), pagingInfo.getPageSize(), initals,
                                                 StringUtils.isEmpty(pageInfo.getKeyword()) ? null
                                                     : pageInfo.getKeyword(), pageInfo.getSort());
        } else {
            brands = brandMapper.findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), initals,
                                          StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                                          pageInfo.getSort());
        }

        BrandPage brandPage = new BrandPage();

        if (!CollectionUtils.isEmpty(brands)) {
            brandPage.setBrands(brands);
        }
        brandPage.setPagingInfo(pagingInfo);
        return brandPage;
    }

    /**
     * 获取单个品牌详情（不含删除）
     *
     * @param code
     * @return
     */
    public Brand getBrand(String code) {
        VerifyParamsUtil.hasText(code, "品牌编号不可为空");

        Brand brand = brandMapper.selectByCodeWithoutDel(code);

        if (brand == null) {
            throw new CommentsRuntimeException(-1, "品牌不存在");
        }

        return brand;
    }

    /**
     * 获取单个品牌详情（含删除）
     *
     * @param code
     * @return
     */
    public Brand getBrandWithDel(String code) {
        VerifyParamsUtil.hasText(code, "品牌编号不可为空");

        Brand brand = brandMapper.selectByCode(code);

        if (brand == null) {
            throw new CommentsRuntimeException(-1, "品牌不存在");
        }

        return brand;
    }

    /**
     * 删除品牌
     *
     * @param code 品牌编号
     */
    public void delete(String code) {
        Brand brand = brandMapper.selectByCode(code);
        if (brand == null) {
            throw new CommentsRuntimeException("品牌不存在");
        }
        if (brand.getDeleted()) {
            throw new CommentsRuntimeException("品牌已删除，请勿重复操作");
        }
        brand.setDeleted(true);

        brandMapper.updateByPrimaryKeySelective(brand);

    }

    /**
     * 新增或更新品牌
     *
     * @param submit
     * @return
     */
    public void saveBrand(BrandSubmit submit) {
        // 1.验证参数
        VerifyParamsUtil.notNull(submit, "品牌信息不可为空");

        // 2.保存品牌
        saveBrandDal(submit);
    }

    /**
     * 保存品牌
     *
     * @param brand
     * @return
     */
    public Brand saveBrandDal(BrandSubmit brand) {
        // 1.验证必填参数
        VerifyParamsUtil.hasText(brand.getNameEn(), "品牌英文名不可为空");

        // 2.补充首字母信息
        brand.setInital(getInital(brand));

        // 3.验证品牌名称
        validateName(brand);

        // 3.保存品牌信息
        Brand saveBrand = null;
        // 更新
        if (!StringUtils.isEmpty(brand.getCode())) {
            saveBrand = updateBrand(brand);
            // 新增
        } else {
            saveBrand = mappingBrand(brand);
            brandMapper.insertSelective(saveBrand);
        }

        return saveBrand;
    }

    /**
     * 更新品牌
     *
     * @param brand
     * @return
     */
    public void saveBrand(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 提取首字母
     *
     * @param brand
     * @return
     */
    private String getInital(BrandSubmit brand) {
        String initialName = "";
        if (PinyinUtils.isChinese(brand.getNameEn())) {
            initialName = PinyinUtils.chineseToPinyin(brand.getNameEn());
        } else {
            initialName = brand.getNameEn();
        }
        return initialName.substring(0, 1).toUpperCase();
    }

    /**
     * 统计同品牌名的品牌数量
     *
     * @param code   品牌编号
     * @param nameEn 品牌英文名
     * @param nameCn 品牌中文名
     * @return
     */
    public Map<String, Boolean> validateBrandName(String code, String nameEn, String nameCn) {
        int[] counts = countDuplicateName(nameEn, nameCn, code);

        Map<String, Boolean> map = new HashMap<>();
        map.put("IsEnReapted", counts[0] > 0);
        map.put("IsCnReapted", counts[1] > 0);
        return map;
    }

    /**
     * 统计名称重复数量
     *
     * @param nameEn 英文名
     * @param nameCn 中文名
     * @param code   品牌编号
     * @return
     */
    private int[] countDuplicateName(String nameEn, String nameCn, String code) {
        int enCount = 0, cnCount = 0;

        if (!StringUtils.isEmpty(nameEn)) {
            List<Brand> existBrandEns = brandMapper.selectByName(nameEn);
            // 英文名存在
            if (!CollectionUtils.isEmpty(existBrandEns)) {
                int existBrandCount = existBrandEns.size();
                // 若为更新品牌，则需先剔除原品牌再判重复
                if (!StringUtils.isEmpty(code)) {
                    Set<String> codes = existBrandEns.stream().map(b -> b.getCode()).collect(Collectors.toSet());
                    if (codes.contains(code)) {
                        existBrandCount--;
                    }
                }
                enCount = existBrandCount;
            }
        }

        if (!StringUtils.isEmpty(nameCn)) {
            List<Brand> existBrandCns = brandMapper.selectByName(nameCn);
            // 中文名存在
            if (!CollectionUtils.isEmpty(existBrandCns)) {
                int existBrandCount = existBrandCns.size();
                // 若为更新品牌，则需先剔除原品牌再判重复
                if (!StringUtils.isEmpty(code)) {
                    Set<String> codes = existBrandCns.stream().map(b -> b.getCode()).collect(Collectors.toSet());
                    if (codes.contains(code)) {
                        existBrandCount--;
                    }
                }
                cnCount = existBrandCount;
            }
        }

        int[] counts = {enCount, cnCount};
        return counts;
    }

    /**
     * 验证品牌名称
     *
     * @param brand
     */
    private void validateName(BrandSubmit brand) {
        int[] counts = countDuplicateName(brand.getNameEn(), brand.getNameCn(), brand.getCode());
        if (counts[0] > 0) {
            throw new CommentsRuntimeException(-1, "英文名与其他品牌名相同");
        }

        if (counts[1] > 0) {
            throw new CommentsRuntimeException(-1, "中文名与其他品牌名相同");
        }

        if (StringUtils.isEmpty(brand.getInital())) {
            throw new CommentsRuntimeException(-1, "品牌首字母不可为空");
        }
    }

    /**
     * 更新品牌信息
     *
     * @param brand
     */
    private Brand updateBrand(BrandSubmit brand) {
        Brand existBrand = brandMapper.selectByCode(brand.getCode());

        if (existBrand == null) {
            throw new CommentsRuntimeException(-1, "品牌不存在,请确认品牌编号");
        }

        if (!existBrand.getNameCn().equals(brand.getNameCn())) {
            existBrand.setNameCn(brand.getNameCn());
        }

        if (!existBrand.getNameEn().equals(brand.getNameEn())) {
            existBrand.setNameEn(brand.getNameEn());
        }

        if (!existBrand.getInital().equals(brand.getInital())) {
            existBrand.setInital(brand.getInital());
        }

        if (!StringUtils.isEmpty(brand.getLogoCode()) && !existBrand.getLogoCode().equals(brand.getLogoCode())) {
            existBrand.setLogoCode(brand.getLogoCode());
        }

        if (!existBrand.getSeries().equals(brand.getSeries())) {
            existBrand.setSeries(brand.getSeries());
        }

        if (!existBrand.getBrief().equals(brand.getBrief())) {
            existBrand.setBrief(brand.getBrief());
        }

        if (!existBrand.getApplication().equals(brand.getApplication())) {
            existBrand.setApplication(brand.getApplication());
        }

        if (!existBrand.getUrl().equals(brand.getUrl())) {
            existBrand.setUrl(brand.getUrl());
        }

        brandMapper.updateByPrimaryKeySelective(existBrand);
        return existBrand;
    }

    /**
     * 品牌 grpc 转换 dal
     *
     * @param brand
     * @return
     */
    private Brand mappingBrand(BrandSubmit brand) {
        Brand brandDal = new Brand();
        brandDal.setApplication(brand.getApplication());
        brandDal.setBrief(brand.getBrief());
        brandDal.setInital(brand.getInital());
        brandDal.setLogoCode(brand.getLogoCode());
        brandDal.setNameCn(brand.getNameCn());
        brandDal.setNameEn(brand.getNameEn());
        brandDal.setSeries(brand.getSeries());
        brandDal.setUrl(brand.getUrl());

        brandDal.setCode(BizCodeUtil.genLongBizCode(TableCode.T_BRAND.getCode()));
        brandDal.setDeleted(false);
        brandDal.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        return brandDal;
    }

    public List<Brand> getBrandByBrandCodesOrder(List<String> codes) {
        return brandMapper.selectBrandByBrandCodesOrder(codes);
    }
}
