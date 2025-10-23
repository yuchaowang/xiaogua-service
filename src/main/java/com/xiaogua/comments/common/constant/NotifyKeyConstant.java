package com.xiaogua.comments.common.constant;

/**
 * 消息通知Key常量
 *
 **/
public class NotifyKeyConstant {
    //统一前缀
    private static final String prefix = "SUBSCRIBE_MESSAGE";

    /**
     * 帖子点赞通知
     */
    public static final String THREADS_LIKE = prefix + "_THREADS_LIKE";

    /**
     * 帖子回复点赞通知
     */
    public static final String THREADS_REPLY_LIKE = prefix + "_THREADS_REPLY_LIKE";

    /**
     * 帖子回复的评论点赞通知
     */
    public static final String THREADS_REPLY_COMMENTS_LIKE = prefix + "_THREADS_REPLY_COMMENTS_LIKE";

    /**
     * 评论点赞通知
     */
    public static final String COMMENTS_LIKE = prefix + "_COMMENTS_LIKE";

    /**
     * 评论回复点赞通知
     */
    public static final String COMMENTS_REPLY_LIKE = prefix + "_COMMENTS_REPLY_LIKE";
}
