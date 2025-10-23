package com.xiaogua.comments.bean.knowledge;

import com.xiaogua.comments.bean.common.CommonPageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangyc
 * @date 2020-12-06 20:37
 */
@ApiModel("干货分页信息")
public class KnowledgePageInfo extends CommonPageInfo {
    /**
     * 关键字
     */
    @ApiModelProperty("关键字")
    private String keyword;

    /**
     * 干货信息状态
     */
    @ApiModelProperty("干货信息状态 100201:未审核,100202:审核通过,100203:审核不通过")
    private Integer status;

    /**
     * 干货信息状态
     */
    @ApiModelProperty("干货分类")
    private Integer categoryType;

    /**
     * 权重排序
     */
    @ApiModelProperty("权重排序")
    private String prioritySort;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public String getPrioritySort() {
        return prioritySort;
    }

    public void setPrioritySort(String prioritySort) {
        this.prioritySort = prioritySort;
    }
}
