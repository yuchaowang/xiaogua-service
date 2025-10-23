package com.xiaogua.comments.common.constant;

/**
 * 缓存Key常量
 *
 * @author: wangyc
 * @date: 2020-11-19 19:12
 **/
public class TemplateConstant {
    /**
     * 新用户加入通知
     *
     * 用户名
     * {{thing1.DATA}}
     * 手机号
     * {{phone_number3.DATA}}
     * 备注
     * {{thing2.DATA}}
     * 邀请人
     * {{thing4.DATA}}
     */
    public static final String NEW_USER_TEMPLATE_ID = "XXogdftzSMmSlbVAwvTRT9ryNZua7dl0kZJNuCBURYs";

    /**
     * 新用户加入跳转页面
     */
    public static final String NEW_USER_TEMPLATE_PAGE = "/pages/index/index";

    /**
     * 新用户加入formid
     */
    public static final String NEW_USER_TEMPLATE_FORMID = "11000";


    /**
     * 帖子被精选通知
     *
     * 帖子标题
     * {{thing1.DATA}}
     * 时间
     * {{time2.DATA}}
     * 获取积分
     * {{number3.DATA}}
     */
    public static final String ESSENCE_TEMPLATE_ID = "-iCY-C8McTJVuiGMnfvDga0A_S2wgMWaWrluHzUbWLs";

    /**
     * 新用户加入跳转页面
     * code为帖子的code
     */
    public static final String ESSENCE_TEMPLATE_PAGE = "/pages/chatDetail/chatDetail?code=%s";

    /**
     * 新用户加入formid
     */
    public static final String ESSENCE_TEMPLATE_FORMID = "19538";


    /**
     * 帖子被赞通知
     *
     * 点赞人
     * {{thing1.DATA}}
     * 点赞时间
     * {{time2.DATA}}
     * 帖子主题
     * {{thing3.DATA}}
     *
     */
    public static final String THREADS_LIKE_TEMPLATE_ID = "2TTEuryZJWUqPg2z4TWfGdoPjTivGJ7M6ctvfd2bDvc";

    /**
     * 帖子被赞通知跳转页面
     * code为帖子的code
     */
    public static final String THREADS_LIKE_TEMPLATE_PAGE = "/pages/chatDetail/chatDetail?code=%s";

    /**
     * 帖子被赞通知formid
     */
    public static final String THREADS_LIKE_TEMPLATE_FORMID = "10173";

    /**
     * 评论点赞通知
     *
     * 评论内容
     * {{thing1.DATA}}
     * 点赞数
     * {{number3.DATA}}
     * 点赞用户
     * {{thing2.DATA}}
     * 点赞时间
     * {{time4.DATA}}
     *
     */
    public static final String COMMENTS_LIKE_TEMPLATE_ID = "8CJ5NS6j7AVkjuNc_iJzN6lBNbjmOtl96NKt4Ydtwpw";

    /**
     * 帖子被赞通知跳转页面
     * code为品牌的code
     */
    public static final String COMMENTS_LIKE_TEMPLATE_PAGE = "/pages/brandDetail/brandDetail?code=%s";

    /**
     * 帖子被赞通知formid
     */
    public static final String COMMENTS_LIKE_TEMPLATE_FORMID = "9007";

    /**
     * 审核提醒
     *
     * 审核结果
     * {{thing1.DATA}}
     * 通过时间
     * {{date4.DATA}}
     *
     */
    public static final String AUDIT_TEMPLATE_ID = "Rwm10ym8r6qMmJOQNLcmlRzUArIol9As3r9JAgZ7EXQ";

    /**
     * 审核通过跳转页面
     * code为品牌的code
     */
    public static final String  AUDIT_TEMPLATE_PAGE = "pages/my/index";

    /**
     * 审核通过通知formid
     */
    public static final String  AUDIT_TEMPLATE_FORMID = "2234";


    /**
     * 积分变动
     *
     * 当前积分
     * {{number2.DATA}}
     * 变动原因
     * {{thing3.DATA}}
     *
     */
    public static final String INTEGRAL_CHANGE_TEMPLATE_ID = "i36NMKs6PJ9JeJXP9bEFiFm68LNpbmwXFYjj6AB5pFo";

    /**
     * 审核通过跳转页面
     * code为品牌的code
     */
    public static final String  INTEGRAL_CHANGE_TEMPLATE_PAGE = "pages/my/index";

    /**
     * 审核通过通知formid
     */
    public static final String  INTEGRAL_CHANGE_TEMPLATE_FORMID = "732";



    /**
     * 新的回复提醒
     *
     * 回复内容
     * {{thing2.DATA}}
     * 回复时间
     * {{date3.DATA}}
     *
     */
    public static final String REPLY_TEMPLATE_ID = "7oruAvJ_4WaUh3Iy-FAO2lMJzCAwZAa1XfC4hWKCqHc";

    /**
     * 帖子新的回复提醒
     * code为帖子的code
     */
    public static final String  THREADS_REPLY_TEMPLATE_PAGE = "/pages/chatDetail/chatDetail?code=%s";

    /**
     * 评论新的回复提醒
     * code为评论的code
     */
    public static final String  COMMENTS_REPLY_TEMPLATE_PAGE = "/pages/brandDetail/brandDetail?code=%s";

    /**
     * 审核通过通知formid
     */
    public static final String  REPLY_CHANGE_TEMPLATE_FORMID = "696";


}
