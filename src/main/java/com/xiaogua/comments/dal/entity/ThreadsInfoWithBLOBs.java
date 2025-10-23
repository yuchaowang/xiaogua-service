package com.xiaogua.comments.dal.entity;

/**
 * <pre>
 * 帖子信息表
 * 表名 : t_threads
 * </pre>
 * @author: Mybatis Generator
 */
public class ThreadsInfoWithBLOBs extends ThreadsInfo {
    /**
     * <pre>
     * 内容
     * 表字段 : t_threads.content
     * </pre>
     */
    private String content;

    /**
     * <pre>
     * 内容富文本
     * 表字段 : t_threads.content_html
     * </pre>
     */
    private String contentHtml;

    /**
     * <pre>
     * 内容
     * 表字段 : t_threads.content
     * </pre>
     */
    public String getContent() {
        return content;
    }

    /**
     * <pre>
     * 内容
     * 表字段 : t_threads.content
     * </pre>
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * <pre>
     * 内容富文本
     * 表字段 : t_threads.content_html
     * </pre>
     */
    public String getContentHtml() {
        return contentHtml;
    }

    /**
     * <pre>
     * 内容富文本
     * 表字段 : t_threads.content_html
     * </pre>
     */
    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml == null ? null : contentHtml.trim();
    }
}