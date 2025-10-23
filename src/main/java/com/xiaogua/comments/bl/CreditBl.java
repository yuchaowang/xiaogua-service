package com.xiaogua.comments.bl;

import com.alibaba.fastjson.JSONObject;
import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.common.constant.*;
import com.xiaogua.comments.dal.entity.CreditHistory;
import com.xiaogua.comments.dal.entity.CreditRule;
import com.xiaogua.comments.dal.entity.CreditUser;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.mapper.CreditHistoryMapper;
import com.xiaogua.comments.dal.mapper.CreditRuleMapper;
import com.xiaogua.comments.dal.mapper.CreditUserMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.RedisCache;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 积分 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class CreditBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CreditUserMapper creditUserMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CreditRuleMapper creditRuleMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CreditHistoryMapper creditHistoryMapper;

    @Autowired
    private ThreadsInfoLikeBl threadsInfoLikeBl;

    @Autowired
    private ThreadsReplyBl threadsReplyBl;

    @Autowired
    private CommentsReplyBl commentsReplyBl;

    @Autowired
    private CommentsInfoLikeBl commentsInfoLikeBl;

    @Autowired
    private UserBl userBl;

    @Autowired
    private MessageBl messageBl;

    @Autowired
    private RedisCache redisCache;

    // 最大有效次数
    private static final int MAX_TIMES = 999999998;

    /**
     * 获取单个用户积分详情
     *
     * @param userCode 用户编号
     * @return
     */
    public CreditUser getCreditUser(String userCode) {
        VerifyParamsUtil.hasText(userCode, "用户编号不可为空");

        CreditUser creditUser = creditUserMapper.selectByUserCode(userCode);

        if (creditUser == null) {
            throw new CommentsRuntimeException(-1, "用户金瓜子信息不存在");
        }

        return creditUser;
    }

    /**
     * 注册用户
     *
     * @param userCode 用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void registerUser(String userCode) {
        handleCredit(userCode, userCode, userCode, CreditEvent.REGISTER_USER, null, CreditRelType.USER_CODE, null);
    }

    /**
     * 专家邀请用户
     *
     * @param userCode        邀请人用户编号
     * @param invitedUserCode 被邀请人用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject registerUserByExpertInvited(String userCode, String invitedUserCode) {
        return handleCredit(userCode, userCode, invitedUserCode, CreditEvent.EXPERT_INVITE_USER, null, CreditRelType.USER_CODE, null);
    }

    /**
     * 一般用户邀请用户
     *
     * @param userCode        邀请人用户编号
     * @param invitedUserCode 被邀请人用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject registerUserByInvited(String userCode, String invitedUserCode) {
        return handleCredit(userCode, userCode, invitedUserCode, CreditEvent.INVITE_USER, null, CreditRelType.USER_CODE, null);
    }

    /**
     * 下载干货
     *
     * @param userCode             用户编号
     * @param knowledgeConsumeCode 干货消费记录编号
     * @param credit               分数
     */
    @Transactional(rollbackFor = Exception.class)
    public void downKnowledgeFile(String userCode, String knowledgeConsumeCode, Integer credit) {
        handleCredit(userCode, userCode, knowledgeConsumeCode, CreditEvent.DOWNLOAD_KNOWLEDGE, credit, CreditRelType.USER_CODE, null);
    }

    /**
     * 下载干货给上传人加分
     *
     * @param userCode             下载用户编号
     * @param relUserCode          上传干货用户编号
     * @param knowledgeConsumeCode 干货消费记录编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void downKnowledgeFileToUploader(String userCode, String relUserCode, String knowledgeConsumeCode) {
        handleCredit(userCode, relUserCode, knowledgeConsumeCode, CreditEvent.DOWNLOAD_KNOWLEDGE_FOR_UPLOADEE, null, CreditRelType.USER_CODE, null);
    }

    /**
     * 审核通过干货
     *
     * @param userCode      审核用户编号
     * @param relUserCode   上传干货用户编号
     * @param knowledgeCode 干货编号
     * @param credit        分数
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject auditSuccessKnowledge(String userCode, String relUserCode, String knowledgeCode, Integer credit) {
        return handleCredit(userCode, relUserCode, knowledgeCode, CreditEvent.AUDIT_SUCCESS_KNOWLEDGE, credit, CreditRelType.USER_CODE, null);
    }

    /**
     * 个人资料完善
     *
     * @param userCode 用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void completeUser(String userCode) {
        handleCredit(userCode, userCode, userCode, CreditEvent.COMPLETE_USER, null, CreditRelType.USER_CODE, null);
    }

    /**
     * 评论回复
     *
     * @param userCode         回复用户编号
     * @param relUserCode      点评用户编号
     * @param commentReplyCode 评论回复编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void commentsReply(String userCode, String relUserCode, String commentReplyCode) {
        handleCredit(userCode, relUserCode, commentReplyCode, CreditEvent.COMMENT_REPLY, null, CreditRelType.REL_CODE, null);
    }

    /**
     * 帖子加精
     *
     * @param userCode        后台操作加精人员信息
     * @param relUserCode     帖子发布人信息
     * @param threadsInfoCode 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public JSONObject essence(String userCode, String relUserCode, String threadsInfoCode) {
        return handleCredit(userCode, relUserCode, threadsInfoCode, CreditEvent.THREADS_ESSENCE, null, CreditRelType.REL_CODE, null);
    }

    /**
     * 帖子取消加精
     *
     * @param userCode        后台操作取消加精人员信息
     * @param relUserCode     帖子发布人信息
     * @param threadsInfoCode 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void unessence(String userCode, String relUserCode, String threadsInfoCode) {
        handleCredit(userCode, relUserCode, threadsInfoCode, CreditEvent.THREADS_UNESSENCE, null, CreditRelType.REL_CODE, null);
    }

    /**
     * 帖子回复
     *
     * @param userCode        回复用户编号
     * @param relUserCode     帖子发布用户编号
     * @param threadsInfoCode 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void threadsReply(String userCode, String relUserCode, String threadsInfoCode) {
        handleCredit(userCode, relUserCode, threadsInfoCode, CreditEvent.THREADS_REPLY, null, CreditRelType.REL_CODE, null);
    }

    /**
     * 帖子点赞-作者加分
     *
     * @param userCode        点赞用户编号
     * @param relUserCode     帖子发布用户编号
     * @param threadsInfoCode 帖子编号
     * @param checkKey        redis key值用于检查是否需要发送积分变动的消息
     */
    @Transactional(rollbackFor = Exception.class)
    public void threadsLike(String userCode, String relUserCode, String threadsInfoCode, String checkKey) {
        handleCredit(userCode, relUserCode, threadsInfoCode, CreditEvent.THREADS_LIKE, null, CreditRelType.REL_CODE, checkKey);
    }

    /**
     * 帖子点赞-作者加分
     *
     * @param userCode        点赞用户编号
     * @param relUserCode     帖子发布用户编号
     * @param threadsInfoCode 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void threadsUnLike(String userCode, String relUserCode, String threadsInfoCode) {
        handleCredit(userCode, relUserCode, threadsInfoCode, CreditEvent.THREADS_UNLIKE, null, CreditRelType.REL_CODE, null);
    }

    /**
     * 评论点赞
     * @param userCode 用户编号
     * @param relUserCode     帖子发布用户编号
     * @param commentsInfoCode 评论点赞编号
     * @param checkKey        redis key值用于检查是否需要发送积分变动的消息
     */
    @Transactional(rollbackFor = Exception.class)
    public void commentsLike(String userCode, String relUserCode, String commentsInfoCode,String checkKey) {
        handleCredit(userCode, relUserCode, commentsInfoCode, CreditEvent.COMMENT_LIKE, null, CreditRelType.REL_CODE, checkKey);
    }

    /**
     * 评论点赞取消
     * @param userCode 用户编号
     * @param relUserCode     帖子发布用户编号
     * @param commentsInfoCode 评论点赞编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void commentsLikeCancel(String userCode, String relUserCode, String commentsInfoCode) {
        handleCredit(userCode, relUserCode, commentsInfoCode, CreditEvent.COMMENT_LIKE_CANCEL, null,
                CreditRelType.REL_CODE, null);
    }

    /**
     * 处理积分
     *
     * @param userCode    发起用户编号
     * @param relUserCode 影响用户编号
     * @param relCode     关联编号
     * @param creditEvent 积分事件
     * @param num         分数
     * @param relType     积分统计类型
     */
    @Transactional(rollbackFor = Exception.class)
    protected JSONObject handleCredit(String userCode, String relUserCode, String relCode, CreditEvent creditEvent, Integer num, CreditRelType relType, String checkKey) {
        JSONObject jsonObject = new JSONObject();
        //操作的分数
        int score = 0;
        //分数操作类型默认0 1加 2减
        int numType = 0;
        //操作前积分
        int oldIntegral = 0;
        //操作前后积分
        int newIntegral = 0;

        CreditUser creditUser = creditUserMapper.selectByUserCode(relUserCode);
        VerifyParamsUtil.notNull(creditUser, "用户金瓜子信息不存在");

        CreditRule creditRule = creditRuleMapper.selectByEvent(creditEvent.getCode());
        VerifyParamsUtil.notNull(creditRule, "金瓜子规则不存在");

        int times = creditRule.getTimes();
        String cycle = creditRule.getCycle();
        int caculateNum = num == null ? creditRule.getNum() : num;
        Date startDate = null;
        Date endDate = null;
        switch (cycle) {
            case "ALL":
                break;
            case "DAY":
                Date date = new Date();

                startDate = com.xiaogua.comments.utils.DateUtil.getStartOfDay(date);
                endDate = com.xiaogua.comments.utils.DateUtil.getEndOfDay(date);
                break;
            default:
                throw new CommentsRuntimeException("金瓜子规则周期不存在");
        }

        List<CreditHistory> creditHistories = new ArrayList<>();

        // 有效次数
        int realTime = 0;
        // 按用户统计
        if (CreditRelType.USER_CODE.equals(relType)) {
            creditHistories = creditHistoryMapper.selectByUserCodeAndEventAndDate(userCode,
                    creditRule.getEvent(),
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));
            realTime = creditHistories.size();
        }

        // 按关联记录统计
        if (CreditRelType.REL_CODE.equals(relType)) {
            realTime = caculateRelEvent(creditEvent, relCode, startDate, endDate);
        }

        if (times == Constant.CREDIT_INFINITY || realTime < times) {
            if (creditRule.getType().intValue() == CreditType.ADD.getCode()) {
                newIntegral = add(creditUser.getId(), userCode, relCode, caculateNum, creditRule.getEvent());
                oldIntegral = newIntegral - score;
                score = caculateNum;
                numType = 1;
                //通知消息
                if (!StringUtils.isEmpty(checkKey)) {
                    if (!redisCache.hasKey(checkKey)) {
                        messageBl.changNotifyMessage(creditEvent, newIntegral, relUserCode, numType, caculateNum);
                    }
                } else {
                    messageBl.changNotifyMessage(creditEvent, newIntegral, relUserCode, numType, caculateNum);
                }
            }

            if (creditRule.getType().intValue() == CreditType.SUBTRACT.getCode()) {
                newIntegral = subtract(creditUser.getId(), userCode, relCode, caculateNum, creditRule.getEvent());
                oldIntegral = newIntegral + score;
                score = caculateNum;
                numType = 2;
                //通知消息
                if (!StringUtils.isEmpty(checkKey)) {
                    if (!redisCache.hasKey(checkKey)) {
                        messageBl.changNotifyMessage(creditEvent, newIntegral, relUserCode, numType, caculateNum);
                    }
                } else {
                    messageBl.changNotifyMessage(creditEvent, newIntegral, relUserCode, numType, caculateNum);
                }
            }
        }

        jsonObject.put("score", score);
        jsonObject.put("numType", numType);
        jsonObject.put("oldIntegral", oldIntegral);
        jsonObject.put("newIntegral", newIntegral);
        return jsonObject;
    }


    /**
     * 处理关联类型事件
     *
     * @param creditEvent 积分事件
     * @param relCode     关联对象编号
     * @param startDate   统计起始时间
     * @param endDate     统计截止时间
     */
    private int caculateRelEvent(CreditEvent creditEvent, String relCode, Date startDate, Date endDate) {
        // 评论回复
        if (CreditEvent.COMMENT_REPLY.equals(creditEvent) || CreditEvent.COMMENT_LIKE.equals(creditEvent)
            || CreditEvent.COMMENT_LIKE_CANCEL.equals(creditEvent)) {
            int replyNum = commentsReplyBl.count(relCode,
                    false,
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            int likeNum = commentsInfoLikeBl.count(relCode,
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            // 只有进行加积分时，需要把本次操作计算在内，以便外层进行times对比
            int needNum = replyNum + likeNum - (!CreditEvent.COMMENT_LIKE_CANCEL.equals(creditEvent) ? 1 : 0);
            return needNum;
        }

        // 帖子加精
        if (CreditEvent.THREADS_ESSENCE.equals(creditEvent)) {
            List<CreditHistory> addHistory = creditHistoryMapper.selectByRelCodeAndEventAndDate(relCode, CreditEvent.THREADS_ESSENCE.getCode(), startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE), endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            List<CreditHistory> subHistory = creditHistoryMapper.selectByRelCodeAndEventAndDate(relCode, CreditEvent.THREADS_UNESSENCE.getCode(), startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE), endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            if (CollectionUtils.isEmpty(addHistory) || CollectionUtils.isEmpty(subHistory)) {
                return addHistory.size();
            }

            if (!CollectionUtils.isEmpty(addHistory)) {
                if (addHistory.size() >= subHistory.size()) {
                    int realTime = addHistory.size() - subHistory.size();
                    return realTime;
                } else {
                    throw new CommentsRuntimeException(-1, "此贴加精、取消加精次数异常，请联系相关人员处理");
                }
            }
        }

        // 帖子取消加精
        if (CreditEvent.THREADS_UNESSENCE.equals(creditEvent)) {
            List<CreditHistory> addHistory = creditHistoryMapper.selectByRelCodeAndEventAndDate(relCode,
                    CreditEvent.THREADS_ESSENCE.getCode(),
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            List<CreditHistory> subHistory = creditHistoryMapper.selectByRelCodeAndEventAndDate(relCode,
                    CreditEvent.THREADS_UNESSENCE.getCode(),
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            if (CollectionUtils.isEmpty(addHistory)) {
                throw new CommentsRuntimeException(-1, "此帖未加精，无法进行取消加精扣分");
            }

            if (CollectionUtils.isEmpty(subHistory)) {
                return subHistory.size();
            } else {
                if (addHistory.size() >= subHistory.size()) {
                    return 0;
                } else {
                    throw new CommentsRuntimeException(-1, "此贴加精、取消加精次数异常，请联系相关人员处理");
                }
            }
        }

        // 帖子回复
        if (CreditEvent.THREADS_REPLY.equals(creditEvent) || CreditEvent.THREADS_LIKE.equals(creditEvent) || CreditEvent.THREADS_UNLIKE.equals(creditEvent)) {

            int replyNum = threadsReplyBl.count(relCode,
                    false,
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            int likeNum = threadsInfoLikeBl.count(relCode,
                    startDate == null ? null : DateUtil.toString(startDate, DateUtil.PATTERN_DATE),
                    endDate == null ? null : DateUtil.toString(endDate, DateUtil.PATTERN_DATE_TIME));

            // 只有进行加积分时，需要把本次操作计算在内，以便外层进行times对比
            int needNum = replyNum + likeNum - (!CreditEvent.THREADS_UNLIKE.equals(creditEvent) ? 1 : 0);
            return needNum;
        }

        return MAX_TIMES;
    }

    /**
     * 增加积分
     *
     * @param creditUserId 用户积分id
     * @param userCode     发起用户编号
     * @param relCode      关联编号
     * @param num          分数
     * @param creditEvent  积分事件
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer add(Integer creditUserId, String userCode, String relCode, Integer num, Integer creditEvent) {
        CreditUser creditUser = creditUserMapper.selectByByIdForUpdate(creditUserId);
        int total = creditUser.getTotal();
        total += num;
        int available = creditUser.getAvailable();
        available += num;

        creditUser.setAvailable(available);
        creditUser.setTotal(total);
        creditUser.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        creditUserMapper.updateByPrimaryKeySelective(creditUser);

        saveHistory(userCode, relCode, creditUser.getUserCode(), num, creditEvent, CreditType.ADD);
        return available;
    }

    /**
     * 扣减积分
     *
     * @param creditUserId 用户积分id
     * @param userCode     发起用户编号
     * @param relCode      关联编号
     * @param num          分数
     * @param creditEvent  积分事件
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer subtract(Integer creditUserId, String userCode, String relCode, Integer num, Integer creditEvent) {
        CreditUser creditUser = creditUserMapper.selectByByIdForUpdate(creditUserId);
        int total = creditUser.getTotal();
        total -= num;
        int available = creditUser.getAvailable();
        if (available < num) {
            throw new CommentsRuntimeException(-1, "金瓜子不足");
        }
        available -= num;
        creditUser.setAvailable(available);
        creditUser.setTotal(total);
        creditUser.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        creditUserMapper.updateByPrimaryKeySelective(creditUser);

        saveHistory(userCode, relCode, creditUser.getUserCode(), num, creditEvent, CreditType.SUBTRACT);
        return available;
    }

    /**
     * 保存积分变更历史
     *
     * @param userCode    发起用户编号
     * @param relCode     关联编号
     * @param relUserCode 影响用户编号
     * @param num         分数
     * @param creditEvent 积分事件
     * @param creditType  积分操作类型
     */
    @Transactional(rollbackFor = Exception.class)
    protected void saveHistory(String userCode, String relCode, String relUserCode, Integer num, Integer creditEvent, CreditType creditType) {
        if (num > 0) {
            CreditHistory creditHistory = new CreditHistory();
            creditHistory.setCode(BizCodeUtil.genLongBizCode(TableCode.T_CREDIT_HISTORY.getCode()));
            creditHistory.setType(creditType.getCode());
            creditHistory.setNum(num);
            creditHistory.setUserCode(userCode);
            creditHistory.setRelCode(relCode);
            creditHistory.setRelUserCode(relUserCode);
            creditHistory.setEvent(creditEvent);
            creditHistory.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
            creditHistory.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

            creditHistoryMapper.insertSelective(creditHistory);
        }
    }

}
