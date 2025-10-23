package com.xiaogua.comments.domain;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bl.KnowledgeConsumeBl;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.Knowledge;
import com.xiaogua.comments.dal.entity.KnowledgeConsume;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 干货消费信息 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class KnowledgeConsumeDomain {

    @Autowired
    KnowledgeConsumeBl knowledgeConsumeBl;

    /**
     * 获取干货消费信息
     * @param knowledgeCode
     * @param userCode
     * @return
     */
    public KnowledgeConsume getKnowledgeConsume(String knowledgeCode, String userCode) {
        KnowledgeConsume knowledgeConsume = knowledgeConsumeBl.getByKnowledgeCodeAndUserCode(knowledgeCode, userCode);
        return knowledgeConsume;
    }

    /**
     * 获取干货消费信息数量
     * @param knowledgeCode 干货编号
     * @return
     */
    public Integer getKnowledgeConsumeCount(String knowledgeCode) {
        VerifyParamsUtil.hasText(knowledgeCode, "干货编号不存在");
        Integer count = knowledgeConsumeBl.countByKnowledgeCode(knowledgeCode);
        return count;
    }

    /**
     * 新增干货消费信息
     * @param knowledgeCode
     * @param userCode
     * @return
     */
    public KnowledgeConsume addConsume(String knowledgeCode, String userCode) {
        VerifyParamsUtil.hasText(knowledgeCode, "干货编号不可为空");
        VerifyParamsUtil.hasText(userCode, "用户编号不可为空");
        KnowledgeConsume knowledgeConsume = knowledgeConsumeBl.getByKnowledgeCodeAndUserCode(knowledgeCode, userCode);
        if (knowledgeConsume != null) {
            throw new CommentsRuntimeException(-1, "已支付过金瓜子");
        }

        knowledgeConsume = knowledgeConsumeBl.addConsume(knowledgeCode, userCode);
        return knowledgeConsume;
    }
}


