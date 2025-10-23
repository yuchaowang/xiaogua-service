package com.xiaogua.comments.common.constant;

/**
 * 积分时间
 *
 * @author wangyc
 * @date 2020-11-21 21:36
 */
public enum CreditEvent {

    /**
     * 20~
     */

    /**
     * 注册用户
     */
    REGISTER_USER(200101,"新用户注册即送金瓜子"),

    /**
     * 下载干货
     */
    DOWNLOAD_KNOWLEDGE(200102,"下载干货消费%s金瓜子"),

    /**
     * 下载干货-上传者加分
     */
    DOWNLOAD_KNOWLEDGE_FOR_UPLOADEE(200103, "干货被下载奖励"),

    /**
     * 审核通过干货
     */
    AUDIT_SUCCESS_KNOWLEDGE(200104, "干货审核通过奖励"),

    /**
     * 完善用户信息
     */
    COMPLETE_USER(200105, "个人信息完善成功奖励"),

    /**
     * 评论回复-作者加分
     */
    COMMENT_REPLY(200106, "评论被回复奖励"),

    /**
     * 一般用户邀请新用户
     */
    INVITE_USER(200107, "邀请新用户成功奖励"),

    /**
     * 专家邀请新用户
     */
    EXPERT_INVITE_USER(200108, "邀请新用户成功奖励"),

    /**
     * 评论点赞-作者加分
     */
    COMMENT_LIKE(200109, "评论被点赞奖励"),

    /**
     * 评论点赞取消-作者扣分
     */
    COMMENT_LIKE_CANCEL(200110, "评论点赞取消减分"),

    /**
     * 帖子加精
     */
    THREADS_ESSENCE(200111, "帖子被加精奖励"),

    /**
     * 帖子取消加精
     */
    THREADS_UNESSENCE(200112, "帖子取消加精减分"),

    /**
     * 帖子回复-作者加分
     */
    THREADS_REPLY(200113, "帖子被回复奖励"),

    /**
     * 帖子点赞-作者加分
     */
    THREADS_LIKE(200114, "帖子被点赞奖励"),

    /**
     * 帖子取消点赞-作者扣分
     */
    THREADS_UNLIKE(200115, "帖子取消点赞减分"),;

    private int code;

    private String event;

    CreditEvent(int code, String event) {
        this.code = code;
        this.event = event;
    }

    public int getCode() {
        return code;
    }

    public String getEvent() {
        return event;
    }

    public static String getEvent(Integer code){
        if(code == null){
            return "";
        }
        for(CreditEvent e : values()){
            if(code.equals(e.getCode())){
                return e.getEvent();
            }
        }
        return "";
    }
}
