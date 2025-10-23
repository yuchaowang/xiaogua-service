package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.KnowledgeConsume;
import com.xiaogua.comments.dal.mapper.KnowledgeConsumeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 干货消费信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class KnowledgeConsumeBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeConsumeBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KnowledgeConsumeMapper knowledgeConsumeMapper;

    /**
     * 获取干货消费信息
     * @param knowledgeCode knowledgeCode
     * @param userCode userCode
     * @return
     */
    public KnowledgeConsume getByKnowledgeCodeAndUserCode(String knowledgeCode, String userCode) {
        KnowledgeConsume knowledge = knowledgeConsumeMapper.selectByKnowledgeCodeAndUserCode(knowledgeCode, userCode);

        return knowledge;
    }

    /**
     * 统计干货消费数量
     * @param knowledgeCode
     * @return
     */
    public Integer countByKnowledgeCode(String knowledgeCode) {
        Integer count = knowledgeConsumeMapper.countByKnowledgeCode(knowledgeCode);
        return count;
    }

    /**
     * 获取干货消费信息
     * @param knowledgeCode knowledgeCode
     * @param userCode userCode
     * @return
     */
    public KnowledgeConsume addConsume(String knowledgeCode, String userCode) {
        KnowledgeConsume knowledgeConsume = new KnowledgeConsume();
        knowledgeConsume.setCode(BizCodeUtil.genLongBizCode(TableCode.T_KNOWLEDGE_CONSUME.getCode()));
        knowledgeConsume.setKnowledgeCode(knowledgeCode);
        knowledgeConsume.setUserCode(userCode);
        knowledgeConsume.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

        knowledgeConsumeMapper.insertSelective(knowledgeConsume);
        return knowledgeConsume;
    }

}
