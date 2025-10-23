package com.xiaogua.comments.bean.threads;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 帖子查询分页信息
 *
 * @author wangyc
 * @date 2020-11-20 15:55
 */
@ApiModel(value = "帖子查询分页信息")
public class ThreadsPageInfo extends CommonPageInfo {

    /**
     * 是否加精
     */
    @ApiModelProperty("是否加精,1:是，0：否")
    private Integer isEssence;

    /**
     * 分类
     */
    @ApiModelProperty("分类")
    private Integer type;

    /**
     * 关键字
     */
    @ApiModelProperty("关键字，目前支持标题模糊匹配")
    private String keyword;

    public Integer getIsEssence() {
        return isEssence;
    }

    public void setIsEssence(Integer isEssence) {
        this.isEssence = isEssence;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
