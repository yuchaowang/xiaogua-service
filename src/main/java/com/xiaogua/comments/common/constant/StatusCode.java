package com.xiaogua.comments.common.constant;

/**
 * 状态码
 * @author wangyc
 * @date 2020-11-21 21:36
 */
public enum StatusCode {

    /**
     * User 用户 10~
     */

    /**
     * 用户类型 1001~
     */
    /**
     * 普通用户/爱好者
     */
    USER_TYPE_AMATEUR(100101),
    /**
     * 专家
     */
    USER_TYPE_EXPERT(100102),
    /**
     * 开发商
     */
    USER_TYPE_DEVELOPER(100103),
    /**
     * 消费者
     */
    USER_TYPE_CONSUMER(100104),

    /**
     * 用户类型状态 1002~
     */
    /**
     * 未审核
     */
    USER_TYPE_STATUS_UNADUIT(100201),

    /**
     * 审核通过
     */
    USER_TYPE_STATUS_ADUIT(100202),

    /**
     * 审核不通过
     */
    USER_TYPE_STATUS_ADUIT_FAILED(100203),

    /**
     * 干货 11~
     */
    /**
     * 干货积分未支付
     */
    KNOWLEDGE_NOT_CONSUMED(110101),

    /**
     * 帖子 12~
     */

    /** 帖子分类1201~ */
    /**
     * 经验分享
     */
    THREADS_TYPE_SHARE(120101),
    /**
     * 项目招聘
     */
    THREADS_TYPE_TECH(120102),
    /**
     * 问题咨询
     */
    THREADS_TYPE_ASK(120103),
    /**
     * 行业探讨
     */
    THREADS_TYPE_TRADE(120104),

    /** 留言板1301~ */
    /**
     * 留言未回复
     */
    MESSAGE_BOARD_UNREPLY(130101),
    /**
     * 留言已回复
     */
    MESSAGE_BOARD_REPLYED(130102),

    /** 活动1401~ */
    /**
     * 活动待发布
     */
    ACTIVITY_UNREADY(140100),
    /**
     * 活动待开始
     */
    ACTIVITY_READY(140101),

    /**
     * 活动进行中
     */
    ACTIVITY_RUNNING(140102),

    /**
     * 活动结束
     */
    ACTIVITY_SUCCESS(140103),

    /**
     * 活动取消
     */
    ACTIVITY_CANCEL(140104);

    private int code;

    StatusCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
