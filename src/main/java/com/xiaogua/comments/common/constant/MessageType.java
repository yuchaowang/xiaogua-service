package com.xiaogua.comments.common.constant;

/**
 * 消息类型
 *
 */
public enum MessageType {

    /**
     * 新用户注册时，发送一条欢迎消息
     */
    REGISTER_USER(100101,"<p style=\"margin-bottom: 8px; font-size: 14px;\">尊敬的%s用户，欢迎加入平台高净值用户群体大家庭。</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">欢迎点评、发帖、上传干货，获取金瓜子，换取海量福利。</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">平台让用户迅速了解真实行业情况、快速积累行业知识、不断提升自身专业性，共同推动行业发展！</p>"),

    /**
     * 邀请的用户成功注册时，邀请人收到一条消息
     */
    INVITE_USER(100102,"<p style=\"margin-bottom: 8px; font-size: 14px;\">您邀请的用户%s已经注册成功，奖励%s金瓜子。</p>"),

    /**
     * 专家身份审核结果反馈，收到一条消息，点击跳转到个人中心
     */
    EXPERT_AUDIT_SUCCESS(100103, "<p style=\"margin-bottom: 8px; font-size: 14px;\">恭喜您通过专家身份审核，成为平台专家库成员，直接获取2000金瓜子，写评价、发帖可获取更多金瓜子，享受海量福利。</p>"),

    /**
     * 专家身份审核结果反馈，收到一条消息，点击跳转到个人中心
     */
    EXPERT_AUDIT_FAIL(100114, "<p style=\"margin-bottom: 8px; font-size: 14px;\">很遗憾，您未通过专家身份审核，原因是：%s。</p>"),

    /**
     * 干货审核结果反馈，收到一条消息，点击跳转到个人中心
     */
    KNOWLEDGE_AUDIT_SUCCESS(100104, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您上传的资料已经通过审核，奖励%s金瓜子。</p>"),

    /**
     * 干货审核结果反馈，收到一条消息，点击跳转到个人中心
     */
    KNOWLEDGE_AUDIT_FAIL(100115, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您上传的资料未通过审核，再接再厉。</p>"),

    /**
     * 密码修改成功时，收到一条消息
     */
    EDIT_PASSWORD(100105, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您的密码已经修改成功：%s，请惠存。</p>"),

    /**
     * 帖子被点赞，用户收到一条消息，跳转帖子详情
     */
    THREADS_LIKE(100106, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您有一条帖子被点赞，点击查看详情。</p>"),

    /**
     * 评价被被点赞，用户收到一条消息，跳转品牌详情
     */
    COMMENT_LIKE(100107, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您有一条评论被点赞，点击查看详情。</p>"),

    /**
     * 帖子被回复，用户收到一条消息，跳转帖子详情
     */
    THREADS_REPLY(100108, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您有一条帖子被评价，点击查看详情。</p>"),

    /**
     * 评价 被回复，用户收到一条消息，跳转品牌详情
     */
    COMMENT_REPLY(100109, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您有一条评论被评价，点击查看详情。</p>"),

    /**
     * 积分变动，用户收到一条消息，点击跳转个人中心
     */
    INTEGRAL_CHANGE(100110, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您收到一份奖励，金瓜子增加%s，点击查看详情。</p>"),

    /**
     * 帖子被精选，通知所有用户，点击跳转该帖子详情
     */
    THREADS_ESSENCE(100111, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您发布的帖子《%s》被精选，奖励%s金瓜子，点击查看详情。</p>"),

    /**
     * 帖子被精选，通知所有用户，点击跳转该帖子详情
     */
    THREADS_ESSENCE_ALL(100111, "<p style=\"margin-bottom: 8px; font-size: 14px;\">帖子《%s》被精选，快去看看这篇帖子吧！</p>"),

    /**
     * 邀请专家，专家激活之后，收到一条消息，内容包含欢迎词，初始密码，引导修改密码等
     */
    INVITE_EXPERT(100112, "<p style=\"margin-bottom: 8px; font-size: 14px;\">尊敬的%s专家您好，欢迎您正式入驻智能家居评价平台，首先送您%s金瓜子，发表点评、帖子可以获取更多金瓜子，享受海量福利。</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">您对行业的深刻认知，必将给广大行业用户带来指导意义，推动整个行业健康有序发展。</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">您专属账号的初始密码为000000，点击修改密码。</p>"),

    /**
     * 用户保存留言
     */
    SAVE_MESSAGE_BOARD(100117, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您的留言已发送成功，我们会尽快跟您取得联系。</p>"),

    /**
     * 后台人员回复留言
     */
    REPLY_MESSAGE_BOARD(100116, "<p style=\"margin-bottom: 8px; font-size: 14px;\">您收到一条留言回复</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px\">您的留言:</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">%s</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">系统回复:</p>" +
            "<p style=\"margin-bottom: 8px; font-size: 14px;\">%s</p>"),

    /**
     * 当平台需要发送更新日志或其他系统级消息时，管理员可在后台管理系统自定义发送
     */
    SYSTEM_MESSAGE(100113, "");

    private int code;

    private String message;

    MessageType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(Integer code){
        if(code == null){
            return "";
        }
        for(MessageType e : values()){
            if(code.equals(e.getCode())){
                return e.getMessage();
            }
        }
        return "";
    }
}
