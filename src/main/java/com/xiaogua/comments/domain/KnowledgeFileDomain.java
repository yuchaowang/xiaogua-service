package com.xiaogua.comments.domain;

import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.knowledge.KnowledgeFileAdminRest;
import com.xiaogua.comments.bean.knowledge.KnowledgeFileRest;
import com.xiaogua.comments.bl.CreditBl;
import com.xiaogua.comments.bl.KnowledgeBl;
import com.xiaogua.comments.bl.KnowledgeFileBl;
import com.xiaogua.comments.common.constant.StatusCode;
import com.xiaogua.comments.dal.entity.Knowledge;
import com.xiaogua.comments.dal.entity.KnowledgeConsume;
import com.xiaogua.comments.dal.entity.KnowledgeFile;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 评论 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class KnowledgeFileDomain {

    @Autowired
    KnowledgeFileBl knowledgeFileBl;

    @Autowired
    FileDomain fileDomain;

    @Autowired
    KnowledgeConsumeDomain knowledgeConsumeDomain;

    @Autowired
    KnowledgeDomain knowledgeDomain;

    @Autowired
    KnowledgeBl knowledgeBl;

    @Autowired
    CreditBl creditBl;

    /**
     * 转换干货文件信息AdminRest
     *
     * @param knowledgeCode 干货编号
     * @return
     */
    public List<KnowledgeFileAdminRest> getKnowledgeFileAdminRest(String knowledgeCode) {
        VerifyParamsUtil.hasText(knowledgeCode, "干货编号不可为空");

        List<KnowledgeFile> knowledgeFiles = knowledgeFileBl.getFiles(knowledgeCode);
        List<KnowledgeFileAdminRest> fileAdminRests = new ArrayList<>();

        if (!CollectionUtils.isEmpty(knowledgeFiles)) {
            fileAdminRests = convertKnowledgeFileAdminRests(knowledgeFiles);
        }
        return fileAdminRests;
    }

    /**
     * 转换干货文件信息AdminRest
     *
     * @param knowledgeCode 干货编号
     * @return
     */
    public List<KnowledgeFileRest> getKnowledgeFileRest(String knowledgeCode) {
        VerifyParamsUtil.hasText(knowledgeCode, "干货编号不可为空");

        List<KnowledgeFile> knowledgeFiles = knowledgeFileBl.getFiles(knowledgeCode);
        List<KnowledgeFileRest> fileRests = new ArrayList<>();

        if (!CollectionUtils.isEmpty(knowledgeFiles)) {
            fileRests = convertKnowledgeFileRests(knowledgeFiles);
        }
        return fileRests;
    }

    /**
     * 获取干货文件下载地址
     *
     * @param code      干货文件编号
     * @param useCredit 是否花费积分 Y
     * @param userCode  用户编号
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseBuilder downKnowledgeFile(String code, String useCredit, String userCode) {
        KnowledgeFile knowledgeFile = knowledgeFileBl.getFile(code);
        VerifyParamsUtil.notNull(knowledgeFile, "干货文件不存在");

        Knowledge knowledge = knowledgeDomain.getKnowledge(knowledgeFile.getKnowledgeCode());
        VerifyParamsUtil.notNull(knowledge, "干货信息不存在");

        String url = "";

        // 干货属于本人，无需验证
        if (!knowledge.getUserCode().equals(userCode)) {
            // 查询积分消费情况
            KnowledgeConsume consume = knowledgeConsumeDomain.getKnowledgeConsume(knowledge.getCode(), userCode);
            if (consume == null) {
                if (StringUtils.isEmpty(useCredit) || !"Y".equals(useCredit)) {
                    throw new CommentsRuntimeException(StatusCode.KNOWLEDGE_NOT_CONSUMED.getCode(), "未支付积分");
                } else if ("Y".equals(useCredit)) {
                    KnowledgeConsume knowledgeConsume = knowledgeConsumeDomain.addConsume(knowledge.getCode(), userCode);

                    // 扣分
                    creditBl.downKnowledgeFile(userCode, knowledgeConsume.getCode(), knowledge.getCredit());

                    // 更新干货下载数
                    Integer downloadNum = knowledgeConsumeDomain.getKnowledgeConsumeCount(knowledge.getCode());
                    knowledge.setDownloadNum(downloadNum);

                    knowledgeBl.updateKnowledge(knowledge);

                    // 上传者增加积分
                    creditBl.downKnowledgeFileToUploader(userCode, knowledge.getUserCode(), knowledgeConsume.getCode());
                }
            }
        }

        url = fileDomain.getFileUrl(knowledgeFile.getFileCode());

        ResponseBuilder builder = new ResponseBuilder();

        builder.setData(url);
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 转换干货文件信息AdminRest
     *
     * @param knowledgeFiles 干货文件信息
     * @return
     */
    private List<KnowledgeFileAdminRest> convertKnowledgeFileAdminRests(List<KnowledgeFile> knowledgeFiles) {
        List<KnowledgeFileAdminRest> rests = new ArrayList<>();
        for (KnowledgeFile knowledgeFile : knowledgeFiles) {
            rests.add(convertKnowledgeFileAdminRest(knowledgeFile));
        }
        return rests;
    }

    /**
     * 转换干货文件信息AdminRest
     *
     * @param knowledgeFile 干货文件信息
     * @return
     */
    private KnowledgeFileAdminRest convertKnowledgeFileAdminRest(KnowledgeFile knowledgeFile) {
        KnowledgeFileAdminRest rest = new KnowledgeFileAdminRest(knowledgeFile);

        if (!StringUtils.isEmpty(knowledgeFile.getFileCode())) {
            rest.setFile(fileDomain.getFileRest(knowledgeFile.getFileCode()));
        }
        return rest;
    }

    /**
     * 转换干货文件信息Rest
     *
     * @param knowledgeFiles 干货文件信息
     * @return
     */
    private List<KnowledgeFileRest> convertKnowledgeFileRests(List<KnowledgeFile> knowledgeFiles) {
        List<KnowledgeFileRest> rests = new ArrayList<>();
        for (KnowledgeFile knowledgeFile : knowledgeFiles) {
            rests.add(convertKnowledgeFileRest(knowledgeFile));
        }
        return rests;
    }

    /**
     * 转换干货文件信息Rest
     *
     * @param knowledgeFile 干货文件信息
     * @return
     */
    private KnowledgeFileRest convertKnowledgeFileRest(KnowledgeFile knowledgeFile) {
        KnowledgeFileRest rest = new KnowledgeFileRest(knowledgeFile);

        if (!StringUtils.isEmpty(knowledgeFile.getFileCode())) {
            rest.setFile(fileDomain.getFileNoUrlRest(knowledgeFile.getFileCode()));
        }
        return rest;
    }
}


