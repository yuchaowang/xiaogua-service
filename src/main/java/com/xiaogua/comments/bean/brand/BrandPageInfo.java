package com.xiaogua.comments.bean.brand;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 品牌请求页码信息
 *
 * @author wangyc
 * @date 2020-11-16 19:17:39
 */
@ApiModel(value = "品牌请求分页信息")
public class BrandPageInfo extends CommonPageInfo {

    /**
     * 首字母
     */
    @ApiModelProperty(value = "首字母")
    String inital;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字")
    String keyword;

    public String getInital() {
        return inital;
    }

    public void setInital(String inital) {
        this.inital = inital;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
