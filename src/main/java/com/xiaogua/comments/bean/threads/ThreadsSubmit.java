package com.xiaogua.comments.bean.threads;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 提交帖子
 * @author wangyc
 * @date 2021-01-23 18:08
 */
@ApiModel(value = "帖子提交信息")
public class ThreadsSubmit {

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    private String title;

    /**
     * 帖子内容（纯文本）
     */
    @ApiModelProperty(value = "帖子内容（纯文本）")
    private String content;

    /**
     * 帖子内容（富文本）
     */
    @ApiModelProperty(value = "帖子内容（富文本）")
    private String contentHtml;

    /**
     * 封面文件编号
     */
    @ApiModelProperty(value = "封面文件编号")
    private String cover;

    /**
     * 分类
     */
    @ApiModelProperty(value = "分类")
    private Integer type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
