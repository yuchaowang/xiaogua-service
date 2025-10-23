package com.xiaogua.comments.common.constant;

/**
 * 用来生成code的表前缀
 *
 * @author wangyc
 * @date 2018-10-10 14:20
 **/
public enum TableCode {

    /**
     * 品牌
     */
    T_BRAND("BR"),

    /**
     * 评论
     */
    T_COMMENTS_INFO("CI"),

    /**
     * 评论点赞
     */
    T_COMMENTS_INFO_LIKE("CL"),

    /**
     * 评论回复
     */
    T_COMMENTS_REPLY("CR"),

    /**
     * 评论回复点赞
     */
    T_COMMENTS_REPLY_LIKE("RL"),

    /**
     * 用户
     */
    T_USER("UR"),

    /**
     * 用户类型
     */
    T_USER_TYPE("UT"),

    /**
     * 用户类型申请
     */
    T_USER_TYPE_APPLY("UA"),

    /**
     * 文件
     */
    T_FILE("FL"),
    /**
     * 干货
     */
    T_KNOWLEDGE("KL"),
    /**
     * 干货文件
     */
    T_KNOWLEDGE_FILE("KF"),
    /**
     * 干货用户消费
     */
    T_KNOWLEDGE_CONSUME("KC"),

    /**
     * 用户积分信息
     */
    T_CREDIT_USER("CU"),

    /**
     * 用户积分规则
     */
    T_CREDIT_RULE("CR"),

    /**
     * 用户积分历史
     */
    T_CREDIT_HISTORY("CH"),

    /**
     * 帖子
     */
    T_THREADS_INFO("TH"),

    /**
     * 帖子回复
     */
    T_THREADS_REPLY("TR"),

    /**
     * 帖子回复评论
     */
    T_THREADS_REPLY_COMMENT("TC"),

    /**
     * 帖子点赞
     */
    T_THREADS_LIKE("TL"),

    /**
     * 游客
     */
    T_VISITOR("TV"),

    /**
     * 游客
     */
    T_MESSAGE("ME"),

    /**
     * 留言板
     */
    T_MESSAGE_BOARD("MB"),

    /**
     * 活动用户
     */
    T_ACTIVITY_USER("AU"),

    /**
     * 活动
     */
    T_ACTIVITY("AT");

    private String code;

    TableCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
