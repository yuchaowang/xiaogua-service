package com.xiaogua.comments.bl;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.bizcode.utils.DateUtil;
import com.xiaogua.comments.bean.knowledge.KnowledgeFileSubmit;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.dal.entity.KnowledgeFile;
import com.xiaogua.comments.dal.mapper.KnowledgeFileMapper;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 干货文件信息 bl
 *
 * @author wangyc
 * @date 2020-11-20 14:37:42
 */
@Service
public class KnowledgeFileBl {

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeFileBl.class);

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private KnowledgeFileMapper knowledgeFileMapper;

    /**
     * 保存干货文件信息信息
     * @param submits 干货文件提交信息
     * @param knowledgeCode 干货编号
     */
    @Transactional(rollbackFor = Exception.class)
   public void saveFiles(List<KnowledgeFileSubmit> submits, String knowledgeCode) {
        for (KnowledgeFileSubmit submit : submits) {
            VerifyParamsUtil.hasText(submit.getFileCode(), "文件信息不存在");
            VerifyParamsUtil.isTrue(submit.getDetno() != null && submit.getDetno() != 0, "文件序号不存在");

            KnowledgeFile file = new KnowledgeFile();
            file.setCode(BizCodeUtil.genLongBizCode(TableCode.T_KNOWLEDGE_FILE.getCode()));
            file.setKnowledgeCode(knowledgeCode);
            file.setFileCode(submit.getFileCode());
            file.setDetno(submit.getDetno());
            file.setCreateDate(DateUtil.getCurrentDate(DateUtil.PATTERN_DATE_TIME));

            knowledgeFileMapper.insertSelective(file);
        }
   }

    /**
     * 通过干货编号获取干货文件信息
     * @param knowledgeCode knowledgeCode
     */
   public List<KnowledgeFile> getFiles(String knowledgeCode) {
        List<KnowledgeFile> knowledgeFiles = knowledgeFileMapper.selectByKnowledgeCode(knowledgeCode);

        return knowledgeFiles;
   }

    /**
     * 通过编号获取干货文件信息
     * @param code code
     */
    public KnowledgeFile getFile(String code) {
        KnowledgeFile knowledgeFile = knowledgeFileMapper.selectByCode(code);

        return knowledgeFile;
    }
}
