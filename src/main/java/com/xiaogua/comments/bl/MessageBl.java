package com.xiaogua.comments.bl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.message.MessageAdminDto;
import com.xiaogua.comments.bean.message.MessagePage;
import com.xiaogua.comments.bean.message.MessagePageInfoDal;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.user.UserTypeApplySubmit;
import com.xiaogua.comments.common.constant.CreditEvent;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.CommentsInfo;
import com.xiaogua.comments.dal.entity.CommentsReply;
import com.xiaogua.comments.dal.entity.Knowledge;
import com.xiaogua.comments.dal.entity.Message;
import com.xiaogua.comments.dal.entity.ThreadsInfoWithBLOBs;
import com.xiaogua.comments.dal.entity.ThreadsReply;
import com.xiaogua.comments.dal.entity.ThreadsReplyComment;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserTypeApply;
import com.xiaogua.comments.dal.mapper.CommentsInfoMapper;
import com.xiaogua.comments.dal.mapper.MessageMapper;
import com.xiaogua.comments.dal.mapper.ThreadsInfoMapper;
import com.xiaogua.comments.dal.mapper.UserMapper;
import com.xiaogua.comments.dal.mapper.VisitorMapper;
import com.xiaogua.comments.utils.MessageUtil;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.RedisCache;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 发送消息
 */
@Service
public class MessageBl {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageBl.class);

    @Autowired private RedisCache redisCache;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private UserMapper userMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsInfoMapper threadsInfoMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsInfoMapper commentsInfoMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private VisitorMapper visitorMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MessageMapper messageMapper;

    @Async
    public void createMessage(String userCode, String content, Integer type, String params) {
        Message message = new Message();
        message.setGroupId(UUID.randomUUID().toString().replaceAll("-", ""));
        message.setUserCode(userCode);
        message.setContent(content);
        message.setType(type);
        message.setIsRead(0);
        message.setParams(params);
        message.setCode(BizCodeUtil.genLongBizCode(TableCode.T_MESSAGE.getCode()));
        message.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        messageMapper.insertSelective(message);
    }

    @Async
    public void createSystemMessage(MessageAdminDto messageAdminDto) {
        String userCodes = messageAdminDto.getUserCodes();
        List<String> userCodeList = new ArrayList<>();
        if (StringUtils.isEmpty(userCodes)) {
            userCodeList = userMapper.selectAllUserCode();
            userCodeList = userCodeList.stream().filter(i -> !StringUtils.isEmpty(i)).collect(Collectors.toList());
        } else {
            userCodeList = Arrays.asList(userCodes.split(","));
        }

        String content = messageAdminDto.getContent();
        //发送消息
        String groupId = UUID.randomUUID().toString().replaceAll("-", "");
        for (String userCode : userCodeList) {
            Message message = new Message();
            message.setGroupId(groupId);
            message.setUserCode(userCode);
            message.setContent(content);
            message.setType(MessageType.SYSTEM_MESSAGE.getCode());
            message.setIsRead(0);
            message.setParams(null);
            message.setCode(BizCodeUtil.genLongBizCode(TableCode.T_MESSAGE.getCode()));
            message.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
            messageMapper.insertSelective(message);
        }
    }

    public void sendMessage(SubscribeMessageDto subscribeMessageDto) {
        MessageUtil.sendMessage(subscribeMessageDto);
    }

    @Async
    public void sendThreadsEssenceMessage(String code, ThreadsInfoWithBLOBs threadsInfo, JSONObject jsonObject) {
        try {
            //查询所有用户openId
            List<String> openIds = visitorMapper.selectAllOpenId();
            openIds = openIds.stream().filter(i -> !StringUtils.isEmpty(i)).collect(Collectors.toList());
            String userCode = threadsInfo.getUserCode();
            User user = userMapper.selectByCode(userCode);
            Integer scoreReal = jsonObject.getInteger("score");
            if (!CollectionUtils.isEmpty(openIds)) {
                for (String openId : openIds) {
                    try {
                        List<String> toUser = new ArrayList<>();
                        toUser.add(openId);
                        SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                        subscribeMessageDto.setToUser(toUser);
                        subscribeMessageDto.setTemplateId(TemplateConstant.ESSENCE_TEMPLATE_ID);
                        subscribeMessageDto.setPage(String.format(TemplateConstant.ESSENCE_TEMPLATE_PAGE, code));

                        Map<String, Object> dataMap = new HashMap<>();
                        dataMap.put("thing1", threadsInfo.getTitle());
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                        dataMap.put("time2", sdf.format(new Date()));
                        //帖子拥有人才加分
                        Integer score = 0;
                        if (user != null && !StringUtils.isEmpty(user.getOpenId())) {
                            if (openId.equals(user.getOpenId())) {
                                score = scoreReal;
                            }
                        }
                        dataMap.put("number3", score);
                        subscribeMessageDto.setData(dataMap);
                        this.sendMessage(subscribeMessageDto);
                    } catch (Exception e) {
                        e.printStackTrace();
                        LOGGER.error("加精发送订阅消息给openId为【" + openId + "】的用户失败");
                    }
                }
            }

            //查询所有用户ID
            List<String> userCodeList = userMapper.selectAllUserCode();
            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            userCodeList = userCodeList.stream().filter(i -> !StringUtils.isEmpty(i)).collect(Collectors.toList());
            for (String uCode : userCodeList) {
                if (uCode.equals(userCode)) {
                    MessageType messageType = MessageType.THREADS_ESSENCE;
                    //帖子拥有人发送不一样的模版
                    this.createMessage(uCode,
                                       String.format(messageType.getMessage(), threadsInfo.getTitle(), scoreReal),
                                       messageType.getCode(), JSON.toJSONString(params));
                } else {
                    MessageType messageType = MessageType.THREADS_ESSENCE_ALL;
                    this.createMessage(uCode, String.format(messageType.getMessage(), threadsInfo.getTitle()),
                                       messageType.getCode(), JSON.toJSONString(params));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("加精发送订阅消息失败，帖子code为【" + code + "】");
        }
    }

    @Async
    public void sendThreadsLikeMessage(String code, User user, ThreadsInfoWithBLOBs threadsInfo, String checkKey) {
        try {
            //帖子被赞通知
            //根据用户ID查询openId
            //生成该条记录点赞的redis记录
            if (!redisCache.hasKey(checkKey)) {
                User userDB = userMapper.selectByCode(threadsInfo.getUserCode());
                if (null != userDB && !StringUtils.isEmpty(userDB.getOpenId())) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(userDB.getOpenId());
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.THREADS_LIKE_TEMPLATE_ID);
                    subscribeMessageDto.setPage(String.format(TemplateConstant.THREADS_LIKE_TEMPLATE_PAGE, code));
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("thing1", user.getName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    dataMap.put("time2", sdf.format(new Date()));
                    dataMap.put("thing3", threadsInfo.getTitle());
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                } else {
                    LOGGER.warn("帖子点赞通知失败，帖子code为【" + code + "]通知失败,获取不到openId");
                }

                //消息通知
                MessageType messageType = MessageType.THREADS_LIKE;
                Map<String, Object> params = new HashMap<>();
                params.put("code", code);
                this.createMessage(threadsInfo.getUserCode(), messageType.getMessage(), messageType.getCode(),
                                   JSON.toJSONString(params));

                //记录redis值
                redisCache.setCacheObject(checkKey, new Date().getTime(),
                                          com.xiaogua.comments.utils.DateUtil.getLastSeconds(), TimeUnit.SECONDS);
            } else {
                LOGGER.error("帖子点赞通知失败，帖子code为【" + code + "]今天已经发送过了");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("帖子点赞通知失败，帖子code为【" + code + "]通知失败");
        }
    }

    //不加积分
    @Async
    public void sendThreadsReplyLikeMessage(String code, User user, ThreadsReply threadsReply, String threadsCode) {
        try {
            //帖子回复被赞通知
            //根据用户ID查询openId
            ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(threadsCode);
            User userDB = userMapper.selectByCode(threadsReply.getUserCode());
            String userCode = user.getCode();
            if (!redisCache.hasKey(NotifyKeyConstant.THREADS_REPLY_LIKE + code + "_" + userCode)) {
                if (null != userDB && !StringUtils.isEmpty(userDB.getOpenId())) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(userDB.getOpenId());
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.THREADS_LIKE_TEMPLATE_ID);
                    subscribeMessageDto
                        .setPage(String.format(TemplateConstant.THREADS_LIKE_TEMPLATE_PAGE, threadsCode));
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("thing1", user.getName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    dataMap.put("time2", sdf.format(new Date()));
                    dataMap.put("thing3", threadsInfo.getTitle());
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                } else {
                    LOGGER.warn("帖子回复点赞通知失败，帖子code为【" + threadsCode + "]通知失败,获取不到openId");
                }

                //消息通知
                MessageType messageType = MessageType.THREADS_LIKE;
                Map<String, Object> params = new HashMap<>();
                params.put("code", threadsCode);
                this.createMessage(threadsReply.getUserCode(), messageType.getMessage(), messageType.getCode(),
                                   JSON.toJSONString(params));

                //记录redis值
                redisCache
                    .setCacheObject(NotifyKeyConstant.THREADS_REPLY_LIKE + code + "_" + userCode, new Date().getTime(),
                                    com.xiaogua.comments.utils.DateUtil.getLastSeconds(), TimeUnit.SECONDS);
            } else {
                LOGGER.error("帖子回复点赞通知失败，帖子code为【" + threadsCode + "]今天已经发送过了");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("帖子回复点赞通知失败，帖子code为【" + threadsCode + "]通知失败");
        }
    }

    //不加积分
    @Async
    public void sendThreadsReplyCommentLikeMessage(String code, User user, ThreadsReplyComment replyComment,
        String threadsCode) {
        try {
            //帖子回复的评论被赞通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(replyComment.getUserCode());
            ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(threadsCode);
            String userCode = user.getCode();
            if (!redisCache.hasKey(NotifyKeyConstant.THREADS_REPLY_COMMENTS_LIKE + code + "_" + userCode)) {
                if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(userDB.getOpenId());
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.THREADS_LIKE_TEMPLATE_ID);
                    subscribeMessageDto
                        .setPage(String.format(TemplateConstant.THREADS_LIKE_TEMPLATE_PAGE, threadsCode));
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("thing1", user.getName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    dataMap.put("time2", sdf.format(new Date()));
                    dataMap.put("thing3", threadsInfo.getTitle());
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                } else {
                    LOGGER.warn("帖子回复的评论点赞通知失败，帖子code为【" + threadsCode + "]通知失败,获取不到openId");
                }

                //消息通知
                MessageType messageType = MessageType.THREADS_LIKE;
                Map<String, Object> params = new HashMap<>();
                params.put("code", threadsCode);
                this.createMessage(replyComment.getUserCode(), messageType.getMessage(), messageType.getCode(),
                                   JSON.toJSONString(params));

                //记录redis值
                redisCache.setCacheObject(NotifyKeyConstant.THREADS_REPLY_COMMENTS_LIKE + code + "_" + userCode,
                                          new Date().getTime(), com.xiaogua.comments.utils.DateUtil.getLastSeconds(),
                                          TimeUnit.SECONDS);
            } else {
                LOGGER.error("帖子回复的评论点赞通知失败，帖子code为【" + threadsCode + "]今天已经发送过了");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("帖子回复的评论点赞通知失败，帖子code为【" + threadsCode + "]通知失败");
        }
    }

    @Async
    public void sendCommentsLikeMessage(String code, CommentsInfo commentsInfo, String checkKey) {
        try {
            //评论被赞通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(commentsInfo.getUserCode());
            String toCode = commentsInfo.getToCode();
            if (!redisCache.hasKey(checkKey)) {
                if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(userDB.getOpenId());
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.COMMENTS_LIKE_TEMPLATE_ID);
                    subscribeMessageDto.setPage(String.format(TemplateConstant.COMMENTS_LIKE_TEMPLATE_PAGE, toCode));
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("thing1", commentsInfo.getContent());
                    dataMap.put("number3", commentsInfo.getLikeNum());
                    dataMap.put("thing2", commentsInfo.getFromName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    dataMap.put("time4", sdf.format(new Date()));
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                } else {
                    LOGGER.warn("评论点赞通知失败，评论code为【" + code + "]通知失败,获取不到openId");
                }

                //消息通知
                MessageType messageType = MessageType.COMMENT_LIKE;
                Map<String, Object> params = new HashMap<>();
                params.put("code", toCode);
                this.createMessage(commentsInfo.getUserCode(), messageType.getMessage(), messageType.getCode(),
                                   JSON.toJSONString(params));
                //记录redis值
                redisCache.setCacheObject(checkKey, new Date().getTime(),
                                          com.xiaogua.comments.utils.DateUtil.getLastSeconds(), TimeUnit.SECONDS);
            } else {
                LOGGER.error("评论点赞通知失败，评论code为【" + code + "]今天已经发送过了");
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("评论点赞通知失败，评论code为【" + code + "]通知失败");
        }
    }

    //不加积分
    @Async
    public void sendCommentsReplyLikeMessage(String code, User user, CommentsReply commentsReply) {
        try {
            //评论回复点赞通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(commentsReply.getUserCode());
            CommentsInfo commentsInfo = commentsInfoMapper.selectByCodeWithoutDel(commentsReply.getCommentsCode());
            String toCode = "";
            if (commentsInfo != null && !StringUtils.isEmpty(commentsInfo.getToCode())) {
                toCode = commentsInfo.getToCode();
            }
            String userCode = user.getCode();
            if (!redisCache.hasKey(NotifyKeyConstant.COMMENTS_REPLY_LIKE + code + "_" + userCode)) {
                if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(userDB.getOpenId());
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.COMMENTS_LIKE_TEMPLATE_ID);
                    subscribeMessageDto.setPage(String.format(TemplateConstant.COMMENTS_LIKE_TEMPLATE_PAGE, toCode));
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("thing1", commentsReply.getContent());
                    dataMap.put("number3", commentsReply.getLikeNum());
                    dataMap.put("thing2", commentsReply.getFromName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                    dataMap.put("time4", sdf.format(new Date()));
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                } else {
                    LOGGER.warn("评论回复点赞通知失败，评论code为【" + code + "]通知失败,获取不到openId");
                }

                //消息通知
                MessageType messageType = MessageType.COMMENT_LIKE;
                Map<String, Object> params = new HashMap<>();
                params.put("code", toCode);
                this.createMessage(commentsReply.getUserCode(), messageType.getMessage(), messageType.getCode(),
                                   JSON.toJSONString(params));

                //记录redis值
                redisCache
                    .setCacheObject(NotifyKeyConstant.COMMENTS_REPLY_LIKE + code + "_" + userCode, new Date().getTime(),
                                    com.xiaogua.comments.utils.DateUtil.getLastSeconds(), TimeUnit.SECONDS);
            } else {
                LOGGER.error("评论回复点赞通知失败，评论code为【" + code + "]今天已经发送过了");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("评论回复点赞通知失败，评论code为【" + code + "]通知失败");
        }
    }

    @Async
    public void sendThreadsReplyMessage(ThreadsInfoWithBLOBs threadsInfo, ThreadsReply threadsReply) {
        String threadsCode = threadsReply.getThreadsCode();
        try {
            //帖子回复通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(threadsInfo.getUserCode());
            if (null != userDB && !StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.REPLY_TEMPLATE_ID);
                subscribeMessageDto.setPage(String.format(TemplateConstant.THREADS_REPLY_TEMPLATE_PAGE, threadsCode));
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("thing2", threadsReply.getContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date3", sdf.format(new Date()));
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("帖子回复通知失败，帖子code为【" + threadsCode + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.THREADS_REPLY;
            Map<String, Object> params = new HashMap<>();
            params.put("code", threadsCode);
            this.createMessage(threadsInfo.getUserCode(), messageType.getMessage(), messageType.getCode(),
                               JSON.toJSONString(params));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("帖子回复通知失败，帖子code为【" + threadsCode + "]通知失败");
        }
    }

    @Async
    public void sendThreadsReplyCommentMessage(ThreadsReplyComment threadsReplyComment) {
        String threadsCode = threadsReplyComment.getThreadsCode();
        try {
            //帖子回复通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(threadsReplyComment.getToUsercode());
            if (null != userDB && !StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.REPLY_TEMPLATE_ID);
                subscribeMessageDto.setPage(String.format(TemplateConstant.THREADS_REPLY_TEMPLATE_PAGE, threadsCode));
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("thing2", threadsReplyComment.getContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date3", sdf.format(new Date()));
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("帖子回复评论通知失败，帖子code为【" + threadsCode + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.THREADS_REPLY;
            Map<String, Object> params = new HashMap<>();
            params.put("code", threadsCode);
            this.createMessage(threadsReplyComment.getToUsercode(), messageType.getMessage(), messageType.getCode(),
                               JSON.toJSONString(params));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("帖子回复评论通知失败，帖子code为【" + threadsCode + "]通知失败");
        }
    }

    @Async
    public void sendCommentReplyMessage(CommentsReply commentsReply) {
        String commentsCode = commentsReply.getCommentsCode();
        CommentsInfo commentsInfo = commentsInfoMapper.selectByCodeWithoutDel(commentsReply.getCommentsCode());
        try {
            //帖子回复通知
            //根据用户ID查询openId
            User userDB = userMapper.selectByCode(commentsReply.getToUsercode());
            String toCode = commentsInfo.getToCode();
            if (null != userDB && !StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.REPLY_TEMPLATE_ID);
                subscribeMessageDto.setPage(String.format(TemplateConstant.COMMENTS_REPLY_TEMPLATE_PAGE, toCode));
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("thing2", commentsReply.getContent());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date3", sdf.format(new Date()));
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("评论回复通知失败，帖子code为【" + commentsCode + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.COMMENT_REPLY;
            Map<String, Object> params = new HashMap<>();
            params.put("code", toCode);
            this.createMessage(userDB.getCode(), messageType.getMessage(), messageType.getCode(),
                               JSON.toJSONString(params));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("评论回复评论通知失败，帖子code为【" + commentsCode + "]通知失败");
        }
    }

    @Async
    public void sendKnowledgeSuccessMessage(Knowledge knowledge, Integer score) {
        try {
            //干货审核通过提醒
            //根据用户ID查询openId
            String userCodeApply = knowledge.getUserCode();
            User userDB = userMapper.selectByCode(userCodeApply);
            if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.AUDIT_TEMPLATE_ID);
                subscribeMessageDto.setPage(TemplateConstant.AUDIT_TEMPLATE_PAGE);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("phrase1", "通过");
                dataMap.put("thing3", "干货审核");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date4", sdf.format(new Date()));
                dataMap.put("thing12", "您的干货已审核通过");
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("干货审核通过通知失败，干货信息编号code为【" + knowledge.getCode() + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.KNOWLEDGE_AUDIT_SUCCESS;
            this.createMessage(userCodeApply, String.format(messageType.getMessage(), score), messageType.getCode(),
                               null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("干货审核通过通知失败，干货信息编号code为【" + knowledge.getCode() + "]通知失败");
        }
    }

    @Async
    public void sendKnowledgeFailMessage(Knowledge knowledge) {
        try {
            //干货审核不通过提醒
            //根据用户ID查询openId
            String userCodeApply = knowledge.getUserCode();
            User userDB = userMapper.selectByCode(userCodeApply);
            if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.AUDIT_TEMPLATE_ID);
                subscribeMessageDto.setPage(TemplateConstant.AUDIT_TEMPLATE_PAGE);
                Map<String, Object> dataMap = new HashMap<>();
                dataMap.put("phrase1", "拒绝");
                dataMap.put("thing3", "干货审核");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date4", sdf.format(new Date()));
                dataMap.put("thing12", "您的干货审核不通过");
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("干货审核不通过通知失败，干货信息编号code为【" + knowledge.getCode() + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.KNOWLEDGE_AUDIT_FAIL;
            this.createMessage(userCodeApply, messageType.getMessage(), messageType.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("干货审核不通过通知失败，干货信息编号code为【" + knowledge.getCode() + "]通知失败");
        }
    }

    @Async
    public void sendUserTypeSuccessMessage(UserTypeApplySubmit submit, UserTypeApply apply) {
        try {
            //审核通过提醒
            //根据用户ID查询openId
            String userCodeApply = apply.getUserCode();
            User userDB = userMapper.selectByCode(userCodeApply);
            if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.AUDIT_TEMPLATE_ID);
                subscribeMessageDto.setPage(TemplateConstant.AUDIT_TEMPLATE_PAGE);
                Map<String, Object> dataMap = new HashMap<>();
                String opinions = submit.getOpinions();
                if (StringUtils.isEmpty(opinions)) {
                    opinions = "您的专家身份审核已通过";
                }
                dataMap.put("phrase1", "通过");
                dataMap.put("thing3", "专家身份审核");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date4", sdf.format(new Date()));
                dataMap.put("thing12", opinions);
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("用户审核通过通知失败，申请编号code为【" + submit.getCode() + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.EXPERT_AUDIT_SUCCESS;
            this.createMessage(userCodeApply, messageType.getMessage(), messageType.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户审核通过通知失败，申请编号code为【" + submit.getCode() + "]通知失败");
        }
    }

    @Async
    public void sendUserTypeFileMessage(UserTypeApplySubmit submit, UserTypeApply apply) {
        try {
            //审核不通过提醒
            //根据用户ID查询openId
            String userCodeApply = apply.getUserCode();
            User userDB = userMapper.selectByCode(userCodeApply);
            if (null != userDB && !org.apache.commons.lang3.StringUtils.isEmpty(userDB.getOpenId())) {
                List<String> toUser = new ArrayList<>();
                toUser.add(userDB.getOpenId());
                SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                subscribeMessageDto.setToUser(toUser);
                subscribeMessageDto.setTemplateId(TemplateConstant.AUDIT_TEMPLATE_ID);
                subscribeMessageDto.setPage(TemplateConstant.AUDIT_TEMPLATE_PAGE);
                Map<String, Object> dataMap = new HashMap<>();
                String opinions = submit.getOpinions();
                if (StringUtils.isEmpty(opinions)) {
                    opinions = "您的专家身份审核不通过";
                }
                dataMap.put("phrase1", "拒绝");
                dataMap.put("thing3", "专家身份审核");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
                dataMap.put("date4", sdf.format(new Date()));
                dataMap.put("thing12", opinions);
                subscribeMessageDto.setData(dataMap);
                this.sendMessage(subscribeMessageDto);
            } else {
                LOGGER.warn("用户审核不通过通知失败，申请编号code为【" + submit.getCode() + "]通知失败,获取不到openId");
            }

            //消息通知
            MessageType messageType = MessageType.EXPERT_AUDIT_FAIL;
            this.createMessage(userCodeApply, String.format(messageType.getMessage(), submit.getOpinions()),
                               messageType.getCode(), null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户审核不通过通知失败，申请编号code为【" + submit.getCode() + "]通知失败");
        }
    }

    @Async
    public void changNotifyMessage(CreditEvent creditEvent, int newIntegral, String relUserCode, int numType,
        int caculateNum) {
        try {
            User user = userMapper.selectByCode(relUserCode);
            if (user != null && !StringUtils.isEmpty(user.getOpenId())) {
                String openId = user.getOpenId();
                //加分
                if (numType == 1) {
                    List<String> toUser = new ArrayList<>();
                    toUser.add(openId);
                    SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                    subscribeMessageDto.setToUser(toUser);
                    subscribeMessageDto.setTemplateId(TemplateConstant.INTEGRAL_CHANGE_TEMPLATE_ID);
                    subscribeMessageDto.setPage(TemplateConstant.INTEGRAL_CHANGE_TEMPLATE_PAGE);
                    Map<String, Object> dataMap = new HashMap<>();
                    dataMap.put("number2", newIntegral);
                    dataMap.put("thing3", creditEvent.getEvent());
                    subscribeMessageDto.setData(dataMap);
                    this.sendMessage(subscribeMessageDto);
                }

                //减分
                if (numType == 2) {
                    //减分目前只有下载干货发送通知
                    if (CreditEvent.DOWNLOAD_KNOWLEDGE.getCode() == creditEvent.getCode()) {
                        List<String> toUser = new ArrayList<>();
                        toUser.add(openId);
                        SubscribeMessageDto subscribeMessageDto = new SubscribeMessageDto();
                        subscribeMessageDto.setToUser(toUser);
                        subscribeMessageDto.setTemplateId(TemplateConstant.INTEGRAL_CHANGE_TEMPLATE_ID);
                        subscribeMessageDto.setPage(TemplateConstant.INTEGRAL_CHANGE_TEMPLATE_PAGE);
                        Map<String, Object> dataMap = new HashMap<>();
                        dataMap.put("number2", newIntegral);
                        if (CreditEvent.DOWNLOAD_KNOWLEDGE.getCode() == creditEvent.getCode()) {
                            dataMap.put("thing3", String.format(creditEvent.getEvent(), caculateNum));
                        }
                        subscribeMessageDto.setData(dataMap);
                        this.sendMessage(subscribeMessageDto);
                    }
                }
            } else {
                LOGGER.warn("积分变动通知失败，用户code为【" + relUserCode + "]通知失败,获取不到openId");
            }

            //消息通知
            //加分
            if (numType == 1) {
                MessageType messageType = MessageType.INTEGRAL_CHANGE;
                this.createMessage(relUserCode, String.format(messageType.getMessage(), caculateNum),
                                   messageType.getCode(), null);
            }

            //减分
            if (numType == 2) {
                //减分目前只有下载干货发送通知
                MessageType messageType = MessageType.INTEGRAL_CHANGE;
                if (CreditEvent.DOWNLOAD_KNOWLEDGE.getCode() == creditEvent.getCode()) {
                    this.createMessage(relUserCode, String.format(messageType.getMessage(), caculateNum),
                                       messageType.getCode(), null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("积分变动通知失败，用户code为【" + relUserCode + "]通知失败");
        }
    }

    /**
     * 分页获取消息信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public MessagePage getMessagePage(MessagePageInfoDal pageInfo) {
        Integer isGroupId = pageInfo.getIsGroupId();
        int count = 0;
        if (isGroupId != null && isGroupId == 1) {
            count = messageMapper.groupByCount(pageInfo.getUserCode(), pageInfo.getKeyword(), pageInfo.getStartTime(),
                                               pageInfo.getEndTime(), pageInfo.getIsRead(), pageInfo.getType());
        } else {
            count = messageMapper.count(pageInfo.getUserCode(), pageInfo.getKeyword(), pageInfo.getStartTime(),
                    pageInfo.getEndTime(), pageInfo.getIsRead(), pageInfo.getType());
        }

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<Message> messageList = messageMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getUserCode(), pageInfo.getKeyword(),
                      pageInfo.getStartTime(), pageInfo.getEndTime(), pageInfo.getIsRead(), pageInfo.getType(),
                      pageInfo.getIsGroupId());
        MessagePage messagePage = new MessagePage();

        if (!CollectionUtils.isEmpty(messageList)) {
            messagePage.setMessageList(messageList);
        }
        messagePage.setPagingInfo(pagingInfo);
        return messagePage;
    }

    /**
     * 将消息置为已读
     *
     * @param messageCode
     * @param userCode
     */
    public void updateMessage(String messageCode, String userCode) {
        messageMapper.updateMessage(messageCode, userCode);
    }

    /**
     * 查询消息数量
     *
     * @param userCode
     * @return
     */
    public Map<String, Object> count(String userCode) {
        Map<String, Object> map = new HashMap<>();
        int count = messageMapper.count(userCode, null, null, null, null, null);
        int isReadCount = messageMapper.count(userCode, null, null, null, 1, null);

        map.put("count", count);
        map.put("isReadCount", isReadCount);
        map.put("noReadCount", count - isReadCount);
        return map;
    }

    /**
     * 撤销消息
     *
     * @param groupId
     */
    public void withdrawMessage(String groupId) {
        messageMapper.deleteByPrimaryGroupId(groupId);
    }
}
