package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.api.entity.PagingInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgeAdminPageInfo;
import com.xiaogua.comments.bean.knowledge.KnowledgePage;
import com.xiaogua.comments.bean.knowledge.KnowledgeSubmit;
import com.xiaogua.comments.common.constant.CategoryType;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.Knowledge;
import com.xiaogua.comments.dal.mapper.KnowledgeMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.PagingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 干货信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class KnowledgeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KnowledgeMapper knowledgeMapper;

    @Autowired KnowledgeFileBl knowledgeFileBl;

    /**
     * 保存干货信息
     *
     * @param submit   干货提交信息
     * @param userCode 用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(KnowledgeSubmit submit, String userCode) {
        Knowledge knowledge = new Knowledge();
        knowledge.setCode(BizCodeUtil.genLongBizCode(TableCode.T_KNOWLEDGE.getCode()));
        knowledge.setTitle(submit.getTitle());
        knowledge.setBrief(submit.getBrief());
        knowledge.setUserCode(userCode);
        knowledge.setStatus(StatusCode.USER_TYPE_STATUS_UNADUIT.getCode());
        knowledge.setCategoryType(submit.getCategoryType());
        knowledge.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        knowledge.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));
        knowledge.setDeleted(false);

        knowledgeMapper.insertSelective(knowledge);

        // 保存干货文件信息
        if (CollectionUtils.isEmpty(submit.getFileSubmits())) {
            throw new CommentsRuntimeException(-1, "干货文件不可为空");
        }

        knowledgeFileBl.saveFiles(submit.getFileSubmits(), knowledge.getCode());
    }

    /**
     * 更新干货信息
     *
     * @param knowledge
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateKnowledge(Knowledge knowledge) {
        knowledgeMapper.updateByPrimaryKeySelective(knowledge);
    }

    /**
     * 审核通过干货信息
     *
     * @param code         干货编号
     * @param credit       积分数量
     * @param categoryType 干货分类
     * @param userCode     用户编号
     */
    @Transactional(rollbackFor = Exception.class)
    public Knowledge auditSuccess(String code, Integer credit, Integer categoryType, String userCode) {
        Knowledge knowledge = knowledgeMapper.selectByCode(code);

        if (knowledge == null) {
            throw new CommentsRuntimeException(-1, "干货信息不存在");
        }

        if (knowledge.getStatus().intValue() != StatusCode.USER_TYPE_STATUS_UNADUIT.getCode()) {
            throw new CommentsRuntimeException(-1, "干货信息已审核，不可操作");
        }

        knowledge.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT.getCode());
        knowledge.setCredit(credit);
        knowledge.setAuditUserCode(userCode);
        knowledge.setCategoryType(categoryType);
        knowledge.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        knowledgeMapper.updateByPrimaryKeySelective(knowledge);
        return knowledge;
    }

    /**
     * 审核不通过干货信息
     *
     * @param code     干货编号
     * @param userCode 用户编号
     */
    public Knowledge auditFailed(String code, String userCode) {
        Knowledge knowledge = knowledgeMapper.selectByCode(code);

        if (knowledge == null) {
            throw new CommentsRuntimeException(-1, "干货信息不存在");
        }

        if (knowledge.getStatus().intValue() != StatusCode.USER_TYPE_STATUS_UNADUIT.getCode()) {
            throw new CommentsRuntimeException(-1, "干货信息已审核，不可操作");
        }

        knowledge.setStatus(StatusCode.USER_TYPE_STATUS_ADUIT_FAILED.getCode());
        knowledge.setAuditUserCode(userCode);
        knowledge.setUpdateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        knowledgeMapper.updateByPrimaryKeySelective(knowledge);
        return knowledge;
    }

    /**
     * 分页获取干货信息
     *
     * @param pageInfo 分页信息
     * @return
     */
    public KnowledgePage getPage(KnowledgeAdminPageInfo pageInfo) {
        // 兼容查询所有
        if (pageInfo.getCategoryType() != null && pageInfo.getCategoryType().equals(CategoryType.ALL.getCode())) {
            pageInfo.setCategoryType(null);
        }
        int count = knowledgeMapper.count(pageInfo.getCategoryType(),
                                          pageInfo.getStatus() == null || pageInfo.getStatus() == 0 ? null
                                              : pageInfo.getStatus(),
                                          StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                                          StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode());

        PagingInfo pagingInfo = PagingUtil.getPagingInfo(pageInfo.getPageNumber(), pageInfo.getPageSize(), count);

        List<Knowledge> knowledges = knowledgeMapper
            .findPage(pagingInfo.getIndex(), pagingInfo.getPageSize(), pageInfo.getSort(),
                      pageInfo.getStatus() == null || pageInfo.getStatus() == 0 ? null : pageInfo.getStatus(),
                      StringUtils.isEmpty(pageInfo.getKeyword()) ? null : pageInfo.getKeyword(),
                      StringUtils.isEmpty(pageInfo.getUserCode()) ? null : pageInfo.getUserCode(),
                      pageInfo.getCategoryType(), pageInfo.getPrioritySort());

        KnowledgePage knowledgePage = new KnowledgePage();

        if (!CollectionUtils.isEmpty(knowledges)) {
            knowledgePage.setKnowledges(knowledges);
        }
        knowledgePage.setPagingInfo(pagingInfo);
        return knowledgePage;
    }

    /**
     * 分页获取干货信息
     *
     * @param code code
     * @return
     */
    public Knowledge get(String code) {
        Knowledge knowledge = knowledgeMapper.selectByCode(code);

        return knowledge;
    }
}
