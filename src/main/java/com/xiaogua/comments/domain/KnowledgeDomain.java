package com.xiaogua.comments.domain;

import com.alibaba.fastjson.JSONObject;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.common.ResponsePageBuilder;
import com.xiaogua.comments.bean.knowledge.KnowledgeAdminPageInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgeAdminRest;
import com.xiaogua.comments.bean.knowledge.KnowledgeAudit;
import com.xiaogua.comments.bean.knowledge.KnowledgeFileAdminRest;
import com.xiaogua.comments.bean.knowledge.KnowledgeFileRest;
import com.xiaogua.comments.bean.knowledge.KnowledgePage;
import com.xiaogua.comments.bean.knowledge.KnowledgePageInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgeRest;
import com.xiaogua.comments.bean.knowledge.KnowledgeSubmit;
import com.xiaogua.comments.bean.message.SubscribeMessageDto;
import com.xiaogua.comments.bl.CreditBl;
import com.xiaogua.comments.bl.KnowledgeBl;
import com.xiaogua.comments.bl.MessageBl;
import com.xiaogua.comments.bl.UserBl;
import com.xiaogua.comments.common.constant.MessageType;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TemplateConstant;
import com.xiaogua.comments.dal.entity.Knowledge;
import com.xiaogua.comments.dal.entity.User;
import com.xiaogua.comments.dal.entity.UserLoginState;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import com.xiaogua.comments.utils.WxUtil;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class KnowledgeDomain {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeDomain.class);

    @Autowired
    KnowledgeBl knowledgeBl;

    @Autowired
    KnowledgeFileDomain knowledgeFileDomain;

    @Autowired
    UserDomain userDomain;

    @Autowired
    CreditBl creditBl;

    @Autowired
    WxUtil wxUtil;

    @Autowired
    private MessageBl messageBl;

    /**
     * 保存干货信息
     *
     * @param submit   submit
     * @param userCode userCode
     * @return
     */
    public ResponseBuilder save(KnowledgeSubmit submit, String userCode) {
        VerifyParamsUtil.notNull(submit, "干货信息不可为空");
        VerifyParamsUtil.notNull(submit.getCategoryType(), "干货分类不可为空");
        VerifyParamsUtil.hasText(submit.getTitle(), "干货标题不可为空");
        VerifyParamsUtil.isTrue(submit.getTitle().length() <= 50, "干货标题不可超过50个字");
        VerifyParamsUtil.hasText(submit.getBrief(), "干货简介不可为空");
        VerifyParamsUtil.isTrue(submit.getBrief().length() <= 500, "干货简介不可超过500个字");
        VerifyParamsUtil.isTrue(!CollectionUtils.isEmpty(submit.getFileSubmits()), "干货文件不可为空");

        // 微信验证干货标题、简介内容合法性
        wxUtil.validateContent(submit.getBrief());
        wxUtil.validateContent(submit.getTitle());

        knowledgeBl.save(submit, userCode);
        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 审核通过干货信息
     *
     * @param audit    audit
     * @param userCode userCode
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder auditSuccess(KnowledgeAudit audit, String userCode) {
        VerifyParamsUtil.notNull(audit, "干货信息不可为空");
        VerifyParamsUtil.hasText(audit.getCode(), "干货编号不可为空");

        Knowledge knowledge = knowledgeBl.auditSuccess(audit.getCode(), audit.getCredit(), audit.getCategoryType(), userCode);
        // 增加积分
        JSONObject jsonObject = creditBl.auditSuccessKnowledge(userCode, knowledge.getUserCode(), knowledge.getCode(), audit.getCredit());
        Integer score = jsonObject.getInteger("score");
        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        //干货审核通过提醒
        messageBl.sendKnowledgeSuccessMessage(knowledge, score);
        return builder;
    }

    /**
     * 更新干货积分
     *
     * @param code     干货编号
     * @param credit   更新积分
     * @param userCode 操作人用户编号
     * @param priority 排序权重
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder updateCredit(String code, Integer credit, Integer categoryType, Integer priority, String userCode) {
        VerifyParamsUtil.hasText(code, "干货编号不可为空");
        VerifyParamsUtil.notNull(credit, "更新金瓜子不可为空");
        VerifyParamsUtil.notNull(categoryType, "干货分类不能为空");
        Knowledge knowledge = knowledgeBl.get(code);
        if (knowledge == null || knowledge.getDeleted()) {
            throw new CommentsRuntimeException(-1, "干货不存在");
        }

        if (StatusCode.USER_TYPE_STATUS_ADUIT.getCode() != knowledge.getStatus().intValue()) {
            throw new CommentsRuntimeException(-1, "干货未审核通过，请先审核通过再更新金瓜子");
        }

        if (credit.intValue() != knowledge.getCredit().intValue()) {
            knowledge.setCredit(credit);
            knowledge.setAuditUserCode(userCode);
            knowledge.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        }

        knowledge.setCategoryType(categoryType);
        knowledge.setPriority(priority);
        knowledgeBl.updateKnowledge(knowledge);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 审核不通过干货信息
     *
     * @param audit    audit
     * @param userCode userCode
     * @return
     */
    public ResponseBuilder auditFailed(KnowledgeAudit audit, String userCode) {
        VerifyParamsUtil.notNull(audit, "干货信息不可为空");
        VerifyParamsUtil.hasText(audit.getCode(), "干货编号不可为空");

        Knowledge knowledge = knowledgeBl.auditFailed(audit.getCode(), userCode);
        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        //干货审核不通过提醒
        messageBl.sendKnowledgeFailMessage(knowledge);
        return builder;
    }

    /**
     * admin-分页获取干货信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getAdminPage(KnowledgeAdminPageInfo pageInfo) {
        KnowledgePage knowledgePage = knowledgeBl.getPage(pageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        if (!CollectionUtils.isEmpty(knowledgePage.getKnowledges())) {
            builder.setData(convertAdminKnowledgeRests(knowledgePage.getKnowledges()));
        }

        builder.setPageInfo(knowledgePage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        return builder;
    }

    /**
     * admin-获取干货信息详情
     *
     * @param code code
     * @return
     */
    public ResponseBuilder getAdmin(String code) {
        Knowledge knowledge = knowledgeBl.get(code);

        if (knowledge == null) {
            throw new CommentsRuntimeException(-1, "干货信息不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(convertAdminKnowledgeRest(knowledge));
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 分页获取干货信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public ResponsePageBuilder getPage(KnowledgePageInfo pageInfo) {
        KnowledgeAdminPageInfo knowledgeAdminPageInfo = new KnowledgeAdminPageInfo();
        knowledgeAdminPageInfo.setPageNumber(pageInfo.getPageNumber());
        knowledgeAdminPageInfo.setPageSize(pageInfo.getPageSize());
        knowledgeAdminPageInfo.setSort(pageInfo.getSort());
        knowledgeAdminPageInfo.setKeyword(pageInfo.getKeyword());
        knowledgeAdminPageInfo.setCategoryType(pageInfo.getCategoryType());
        // 用户只接受审核通过干货信息
        knowledgeAdminPageInfo.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
        KnowledgePage knowledgePage = knowledgeBl.getPage(knowledgeAdminPageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        if (!CollectionUtils.isEmpty(knowledgePage.getKnowledges())) {
            builder.setData(convertKnowledgeRests(knowledgePage.getKnowledges()));
        }

        builder.setPageInfo(knowledgePage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        return builder;
    }

    /**
     * 分页获取我的干货信息
     *
     * @param pageInfo 分页信息
     * @param userLoginState 用户信息
     * @return
     */
    public ResponsePageBuilder getMyPage(KnowledgePageInfo pageInfo, UserLoginState userLoginState) {
        KnowledgeAdminPageInfo knowledgeAdminPageInfo = new KnowledgeAdminPageInfo();
        knowledgeAdminPageInfo.setPageNumber(pageInfo.getPageNumber());
        knowledgeAdminPageInfo.setPageSize(pageInfo.getPageSize());
        knowledgeAdminPageInfo.setSort(pageInfo.getSort());
        knowledgeAdminPageInfo.setKeyword(pageInfo.getKeyword());
        knowledgeAdminPageInfo.setStatus(pageInfo.getStatus());
        knowledgeAdminPageInfo.setUserCode(userLoginState.getCode());
        knowledgeAdminPageInfo.setCategoryType(pageInfo.getCategoryType());
        KnowledgePage knowledgePage = knowledgeBl.getPage(knowledgeAdminPageInfo);

        ResponsePageBuilder builder = new ResponsePageBuilder();

        if (!CollectionUtils.isEmpty(knowledgePage.getKnowledges())) {
            builder.setData(convertKnowledgeRests(knowledgePage.getKnowledges()));
        }

        builder.setPageInfo(knowledgePage.getPagingInfo());
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());

        return builder;
    }

    /**
     * 获取干货信息详情
     *
     * @param code code
     * @return
     */
    public ResponseBuilder get(String code) {
        Knowledge knowledge = knowledgeBl.get(code);

        if (knowledge == null) {
            throw new CommentsRuntimeException(-1, "干货信息不存在");
        }

        // 用户只接受审核通过干货信息
        if (knowledge.getStatus().intValue() != StatusCode.USER_TYPE_STATUS_ADUIT.getCode()) {
            throw new CommentsRuntimeException(-1, "干货信息不存在");
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setData(convertKnowledgeRest(knowledge));
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取干货信息详情
     *
     * @param code code
     * @return
     */
    public Knowledge getKnowledge(String code) {
        Knowledge knowledge = knowledgeBl.get(code);

        return knowledge;
    }

    /**
     * 转换干货信息admin-Rest
     *
     * @param knowledges knowledges
     * @return
     */
    private List<KnowledgeAdminRest> convertAdminKnowledgeRests(List<Knowledge> knowledges) {
        List<KnowledgeAdminRest> rests = new ArrayList<>();

        for (Knowledge knowledge : knowledges) {
            rests.add(convertAdminKnowledgeRest(knowledge));
        }
        return rests;
    }

    /**
     * 转换干货信息admin-Rest
     *
     * @param knowledge knowledge
     * @return
     */
    private KnowledgeAdminRest convertAdminKnowledgeRest(Knowledge knowledge) {
        KnowledgeAdminRest rest = new KnowledgeAdminRest(knowledge);

        List<KnowledgeFileAdminRest> fileAdminRests =
            knowledgeFileDomain.getKnowledgeFileAdminRest(knowledge.getCode());
        rest.setFiles(fileAdminRests);

        rest.setUserRest(userDomain.getUserRest(knowledge.getUserCode()));
        return rest;
    }

    /**
     * 转换干货信息Rest
     *
     * @param knowledges knowledges
     * @return
     */
    private List<KnowledgeRest> convertKnowledgeRests(List<Knowledge> knowledges) {
        List<KnowledgeRest> rests = new ArrayList<>();

        for (Knowledge knowledge : knowledges) {
            rests.add(convertKnowledgeRest(knowledge));
        }
        return rests;
    }

    /**
     * 转换干货信息Rest
     *
     * @param knowledge knowledge
     * @return
     */
    private KnowledgeRest convertKnowledgeRest(Knowledge knowledge) {
        KnowledgeRest rest = new KnowledgeRest(knowledge);

        List<KnowledgeFileRest> fileRests = knowledgeFileDomain.getKnowledgeFileRest(knowledge.getCode());
        rest.setFiles(fileRests);

        rest.setUserRest(userDomain.getUserRest(knowledge.getUserCode()));
        return rest;
    }
}


