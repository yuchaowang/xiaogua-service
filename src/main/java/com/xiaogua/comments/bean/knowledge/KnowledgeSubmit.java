package com.xiaogua.comments.bean.knowledge;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 * 干货提交信息
 */
@ApiModel(value = "干货提交信息")
public class KnowledgeSubmit {

    /**
     * 标题
     */
    @ApiModelProperty("干货提交信息")
    private String title;

    /**
     * 简介
     */
    @ApiModelProperty("简介")
    private String brief;

    /**
     * 简介
     */
    @ApiModelProperty("干货分类")
    private Integer categoryType;

    /**
     * 干货文件提交信息
     */
    @ApiModelProperty("干货文件提交信息")
    private List<KnowledgeFileSubmit> fileSubmits;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public List<KnowledgeFileSubmit> getFileSubmits() {
        return fileSubmits;
    }

    public void setFileSubmits(List<KnowledgeFileSubmit> fileSubmits) {
        this.fileSubmits = fileSubmits;
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }
}