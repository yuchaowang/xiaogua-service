package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.comments.CommentsInfoSubmit;
import com.xiaogua.comments.bean.comments.CommentsPage;
import com.xiaogua.comments.bean.comments.CommentsPageInfoDal;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.common.constant.NotifyKeyConstant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.*;
import com.xiaogua.comments.dal.mapper.CommentsInfoMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import com.xiaogua.comments.utils.RedisCache;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 评论信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class CommentsBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentsBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private CommentsInfoMapper commentsInfoMapper;

    @Autowired
    private BrandBl brandBl;

    @Autowired
    private CommentsInfoLikeBl commentsInfoLikeBl;

    @Autowired
    private UserTypeBl userTypeBl;

    @Autowired
    private FileBl fileBl;

    @Autowired
    private CreditBl creditBl;

    @Autowired
    private MessageBl messageBl;

    /**
     * 分页获取评论信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public CommentsPage getCommentsPage(CommentsPageInfoDal pageInfo) {
        int count = commentsInfoMapper.count(StringUtils.isEmpty(pageInfo.getToCode()) ? null : pageInfo.getToCode(),
                                             StringUtils.isEmpty(pageInfo.getUserCode()) ? null
                                                 : pageInfo.getUserCode(), pageInfo.isShowDeleted(),
                                             pageInfo.getUserType() == null || pageInfo.getUserType() == 0 ? null
                                                 : pageInfo.getUserType());

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<CommentsInfo> commentsInfos = commentsInfoMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getSort(),
                      StringUtils.isEmpty(pageInfo.getToCode()) ? null : pageInfo.getToCode(),
                      StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode(),
                      pageInfo.isShowDeleted(),
                      pageInfo.getUserType() == null || pageInfo.getUserType() == 0 ? null : pageInfo.getUserType());

        CommentsPage commentsPage = new CommentsPage();

        if (!CollectionUtils.isEmpty(commentsInfos)) {
            commentsPage.setCommentsInfos(commentsInfos);
        }
        commentsPage.setPagingInfo(pagingInfo);
        return commentsPage;
    }

    /**
     * 获取单个评论详情（不含删除）
     *
     * @param code 评论编号
     * @return
     */
    public CommentsInfo getCommentsInfo(String code) {
        VerifyParamsUtil.hasText(code, "评论编号不可为空");

        CommentsInfo commentsInfo = commentsInfoMapper.selectByCodeWithoutDel(code);

        if (commentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        return commentsInfo;
    }

    /**
     * 获取单个评论详情（含删除）
     *
     * @param code 评论编号
     * @return
     */
    public CommentsInfo getCommentsInfoWithDel(String code) {
        VerifyParamsUtil.hasText(code, "评论编号不可为空");

        CommentsInfo commentsInfo = commentsInfoMapper.selectByCode(code);

        if (commentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        return commentsInfo;
    }

    /**
     * 保存评论
     *
     * @param submit 评论信息
     */
    @Transactional(rollbackFor = Exception.class)
    public CommentsInfo save(CommentsInfoSubmit submit, User user) {
        CommentsInfo commentsInfo = null;

        UserType userType = userTypeBl.getCurrentUserType(user.getCode());

        // 新增
        if (StringUtils.isEmpty(submit.getCode())) {
            commentsInfo = mappingCommentsInfo(submit, user, userType);
            commentsInfoMapper.insertSelective(commentsInfo);
        } else {
            // 更新
            throw new CommentsRuntimeException("评论不可更新");
            //            commentsInfo = updateCommentsInfo(submit, user, userType);
        }
        return commentsInfo;
    }

    /**
     * 通过被评论编号获取评论数量
     *
     * @param toCode
     * @return
     */
    public Integer getCountByToCode(String toCode) {
        Integer count = commentsInfoMapper.count(toCode, null, false, null);
        return count;
    }

    /**
     * 更新评论信息
     *
     * @param submit 评论信息
     */
    private CommentsInfo updateCommentsInfo(CommentsInfoSubmit submit, User user, UserType userType) {
        CommentsInfo existCommentsInfo = commentsInfoMapper.selectByCodeWithoutDel(submit.getCode());

        if (existCommentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在,请确认评论编号");
        }

        if (!existCommentsInfo.getUserCode().equals(user.getCode())) {
            throw new CommentsRuntimeException(-1, "评论不属于你，不可更新");
        }

        if (existCommentsInfo.getUserType().intValue() != userType.getType().intValue()) {
            throw new CommentsRuntimeException(-1, "用户类型已变更，不可更新");
        }

        if (!existCommentsInfo.getToCode().equals(submit.getToCode())) {
            throw new CommentsRuntimeException(-1, "被评论者不同，更新失败");
        }

        if (!existCommentsInfo.getContent().equals(submit.getContent())) {
            existCommentsInfo.setContent(submit.getContent());
        }

        commentsInfoMapper.updateByPrimaryKeySelective(existCommentsInfo);
        return existCommentsInfo;
    }

    /**
     * 验证评论信息
     *
     * @param submit 评论信息
     */
    public void validate(CommentsInfoSubmit submit) {
        VerifyParamsUtil.isTrue(submit != null, "评论不可为空");
        VerifyParamsUtil.hasText(submit.getContent(), "评论内容不可为空");
        VerifyParamsUtil.isTrue(submit.getContent().trim().length() <= 1000, "评论长度不可超过1000");
        VerifyParamsUtil.hasText(submit.getToCode(), "被评论者编号不可为空");

        Brand brand = brandBl.getBrandWithDel(submit.getToCode());
        VerifyParamsUtil.isTrue(brand != null, "品牌不存在");
    }

    /**
     * 转换dal.CommentsInfo
     *
     * @param submit 评论信息
     * @return
     */
    private CommentsInfo mappingCommentsInfo(CommentsInfoSubmit submit, User user, UserType userType) {
        CommentsInfo commentsInfo = new CommentsInfo();
        commentsInfo.setToCode(submit.getToCode());
        commentsInfo.setContent(submit.getContent());

        commentsInfo.setCode(BizCodeUtil.genLongBizCode(TableCode.T_COMMENTS_INFO.getCode()));
        commentsInfo.setLikeNum(0);
        commentsInfo.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        commentsInfo.setDeleted(false);
        commentsInfo.setUserCode(user.getCode());
        commentsInfo.setFromName(user.getName());

        FileInfo fileInfo = fileBl.getFileInfo(user.getAvatar());
        if (fileInfo != null) {
            commentsInfo.setFromAvatar(fileInfo.getUrl());
        }
        commentsInfo.setUserType(userType.getType());

        return commentsInfo;
    }

    /**
     * 点赞
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int like(String code, User user) {
        CommentsInfo commentsInfo = commentsInfoMapper.selectByCodeWithoutDel(code);
        if (commentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        int likeNum = commentsInfo.getLikeNum();
        likeNum++;
        commentsInfo.setLikeNum(likeNum);

        // 增加评论点赞信息
        CommentsInfoLike like = commentsInfoLikeBl.save(code, user);

        commentsInfoMapper.updateByPrimaryKeySelective(commentsInfo);

        // 点赞后加分
        if (!commentsInfo.getUserCode().equals(user.getCode())) {
            String userCode = user.getCode();
            String checkKey = NotifyKeyConstant.COMMENTS_LIKE + code + "_" + userCode;
            creditBl.commentsLike(user.getCode(), commentsInfo.getUserCode(), code, checkKey);
            //评论被赞通知
            messageBl.sendCommentsLikeMessage(code, commentsInfo, checkKey);
        }

        return likeNum;
    }

    /**
     * 取消点赞
     *
     * @param code
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int unlike(String code, User user) {
        CommentsInfo commentsInfo = commentsInfoMapper.selectByCodeWithoutDel(code);
        if (commentsInfo == null) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        int likeNum = commentsInfo.getLikeNum();
        likeNum--;
        commentsInfo.setLikeNum(likeNum);

        // 增加评论点赞信息
        commentsInfoLikeBl.delete(code, user.getCode());

        commentsInfoMapper.updateByPrimaryKeySelective(commentsInfo);

        // 取消点赞后扣分
        if (!commentsInfo.getUserCode().equals(user.getCode())) {
            creditBl.commentsLikeCancel(user.getCode(), commentsInfo.getUserCode(), code);
        }

        return likeNum;
    }

    /**
     * 删除评论
     *
     * @param code
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) {
        CommentsInfo commentsInfo = commentsInfoMapper.selectByCode(code);
        if (commentsInfo.getDeleted()) {
            throw new CommentsRuntimeException(-1, "评论不存在");
        }

        commentsInfo.setDeleted(true);

        // TODO 处理回复 、点赞？
        commentsInfoMapper.updateByPrimaryKeySelective(commentsInfo);
    }

    /**
     * 按评论发布时间排序获取去重品牌编号
     *
     * @param limit 数量
     * @return
     */
    public List<String> getDistinctBrandCodeOrderByCommentCreateDate(Integer limit) {
        List<String> brandCodes = commentsInfoMapper.selectDistinctBrandCodeOrderByCommentCreateDate(limit);

        return brandCodes;
    }

    /**
     * 通过品牌编号获取评论点赞数
     *
     * @param brandCode
     * @return
     */
    public Integer sumLikeByBrandCode(String brandCode) {
        Integer sum = commentsInfoMapper.sumLikeByBrand(brandCode);

        return sum == null ? 0 : sum;
    }

    /**
     * 通过品牌编号获取最大点赞数评论
     *
     * @param brandCode
     * @return
     */
    public CommentsInfo getMaxLikeNumCommentsInfo(String brandCode) {
        CommentsInfo commentsInfo = commentsInfoMapper.selectMaxLikeNumCommentsInfoByBrand(brandCode);
        return commentsInfo;
    }
}
