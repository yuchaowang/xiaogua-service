package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bean.threads.ThreadsPage;
import com.xiaogua.comments.bean.threads.ThreadsPageInfoDal;
import com.xiaogua.comments.bean.threads.ThreadsSubmit;
import com.xiaogua.comments.common.constant.*;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.CreditRuleMapper;
import com.xiaogua.comments.dal.mapper.ThreadsInfoMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
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
import java.util.concurrent.TimeUnit;

/**
 * 评论信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class ThreadsInfoBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadsInfoBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private ThreadsInfoMapper threadsInfoMapper;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CreditRuleMapper creditRuleMapper;

    @Autowired
    private UserBl userBl;

    @Autowired
    private UserTypeBl userTypeBl;

    @Autowired
    private ThreadsInfoLikeBl threadsInfoLikeBl;

    @Autowired
    private CreditBl creditBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 分页获取帖子
     *
     * @param pageInfo 分页信息
     * @param deleted  是否查询已删除帖子
     * @return
     */
    public ThreadsPage  getPage(ThreadsPageInfoDal pageInfo, boolean deleted) {
        int count = threadsInfoMapper.count(pageInfo.getIsEssence() == 1,
                                            (pageInfo.getType() == null || pageInfo.getType() == 0) ? null
                                                : pageInfo.getType(),
                                            StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                                            StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode(),
                                            deleted);

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<ThreadsInfoWithBLOBs> threadsInfos = threadsInfoMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getIsEssence() == 1,
                      (pageInfo.getType() == null || pageInfo.getType() == 0) ? null : pageInfo.getType(),
                      StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode(),
                deleted);

        ThreadsPage threadsPage = new ThreadsPage();

        if (!CollectionUtils.isEmpty(threadsInfos)) {
            threadsPage.setThreadsInfos(threadsInfos);
        }
        threadsPage.setPagingInfo(pagingInfo);
        return threadsPage;
    }

    /**
     * 保存帖子
     *
     * @param submit   帖子提交信息
     * @param userCode 用户信息
     * @return
     */
    public String save(ThreadsSubmit submit, String userCode) {
        User user = userBl.getUserByCode(userCode);
        VerifyParamsUtil.notNull(user, "用户不存在");
        UserType userType = userTypeBl.getCurrentUserType(userCode);
        VerifyParamsUtil.notNull(userType, "用户类型不存在");

        ThreadsInfoWithBLOBs threadsInfo = new ThreadsInfoWithBLOBs();
        threadsInfo.setCode(BizCodeUtil.genLongBizCode(TableCode.T_THREADS_INFO.getCode()));
        threadsInfo.setCover(StringUtils.isEmpty(submit.getCover()) ? Constant.EMPTY_STR : submit.getCover());
        threadsInfo.setTitle(submit.getTitle());
        threadsInfo.setType((submit.getType() != null && submit.getType().intValue() != 0) ? submit.getType()
                                : StatusCode.THREADS_TYPE_TRADE.getCode());
        threadsInfo.setContent(submit.getContent());
        threadsInfo.setContentHtml(submit.getContentHtml());
        threadsInfo.setUserAvatar(StringUtils.isEmpty(user.getAvatar()) ? Constant.EMPTY_STR : user.getAvatar());
        threadsInfo.setLikeNum(0);
        threadsInfo.setIsEssence(false);
        threadsInfo.setDeleted(false);
        threadsInfo.setUserCode(userCode);
        threadsInfo.setUserName(user.getName());
        threadsInfo.setUsertType(userType.getType());
        threadsInfo.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        threadsInfoMapper.insert(threadsInfo);

        return threadsInfo.getCode();
    }

    /**
     * 获取帖子详情
     *
     * @param code 帖子编号
     * @return
     */
    public ThreadsInfoWithBLOBs getThreadsInfo(String code) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        return threadsInfo;
    }

    /**
     * 点赞
     *
     * @param code 帖子编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int like(String code, User user) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        int likeNum = threadsInfo.getLikeNum();
        likeNum++;
        threadsInfo.setLikeNum(likeNum);

        // 增加评论点赞信息
        ThreadsLike like = threadsInfoLikeBl.save(code, user);

        threadsInfoMapper.updateByPrimaryKeySelective(threadsInfo);

        // 非本人点赞后加分
        if (!threadsInfo.getUserCode().equals(user.getCode())) {
            String userCode = user.getCode();
            String checkKey = NotifyKeyConstant.THREADS_LIKE + code + "_" + userCode;
            creditBl.threadsLike(user.getCode(), threadsInfo.getUserCode(), threadsInfo.getCode(), checkKey);
            //帖子被赞通知
            messageBl.sendThreadsLikeMessage(code, user, threadsInfo, checkKey);
        }

        return likeNum;
    }

    /**
     * 取消点赞
     *
     * @param code 帖子编号
     * @param user 用户信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int unlike(String code, User user) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        int likeNum = threadsInfo.getLikeNum();
        likeNum--;
        threadsInfo.setLikeNum(likeNum);

        // 增加评论点赞信息
        threadsInfoLikeBl.delete(code, user.getCode());

        threadsInfoMapper.updateByPrimaryKeySelective(threadsInfo);

        // 取消点赞后扣分
        if (!threadsInfo.getUserCode().equals(user.getCode())) {
            creditBl.threadsUnLike(user.getCode(), threadsInfo.getUserCode(), threadsInfo.getCode());
        }

        return likeNum;
    }

    /**
     * 帖子加精
     *
     * @param code 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void essence(String code) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        threadsInfo.setIsEssence(true);

        threadsInfoMapper.updateByPrimaryKeySelective(threadsInfo);
    }

    /**
     * 取消帖子加精
     *
     * @param code 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void unessence(String code) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        threadsInfo.setIsEssence(false);

        threadsInfoMapper.updateByPrimaryKeySelective(threadsInfo);
    }

    /**
     * 删除帖子
     *
     * @param code 帖子编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) {
        ThreadsInfoWithBLOBs threadsInfo = threadsInfoMapper.selectByCode(code);
        if (threadsInfo == null || threadsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "帖子不存在");
        }

        threadsInfo.setDeleted(true);

        threadsInfoMapper.updateByPrimaryKeySelective(threadsInfo);
    }
}
