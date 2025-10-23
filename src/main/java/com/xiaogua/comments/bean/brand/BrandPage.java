package com.xiaogua.comments.bean.brand;

import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.dal.entity.Brand;

import java.util.List;

/**
 * 分页品牌信息
 *
 * @author wangyc
 * @date 2020-11-17 17:09
 */
public class BrandPage {

    PagingInfo pagingInfo;

    List<Brand> brands;

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public void setPagingInfo(PagingInfo pagingInfo) {
        this.pagingInfo = pagingInfo;
    }

    public List<Brand> getBrands() {
        return brands;
    }

    public void setBrands(List<Brand> brands) {
        this.brands = brands;
    }
}
