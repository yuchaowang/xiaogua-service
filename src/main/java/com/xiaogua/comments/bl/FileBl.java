package com.xiaogua.comments.bl;

import com.xiaogua.comments.config.MinioConfig;
import com.xiaogua.comments.dal.entity.FileInfo;
import com.xiaogua.comments.dal.mapper.FileInfoMapper;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 文件 业务领域
 * @author wangyc
 * @date 2018-10-10 19:21
 */
@Service
public class FileBl {

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private FileInfoMapper fileMapper;


    @Autowired
    private MinioConfig minioConfig;

    /**
     * 上传文件(v1.1,与上传图片合并)
     * @param fileInfo fileInfo
     * @return
     */
    public FileInfo upload(FileInfo fileInfo) {
        // 验证参数
        VerifyParamsUtil.hasText(fileInfo.getCode(), "文件编号不可为空");
        VerifyParamsUtil.hasText(fileInfo.getName(), "文件名为不可为空");
        VerifyParamsUtil.notNull(fileInfo.getSize(), "文件大小不可为空");
        VerifyParamsUtil.hasText(fileInfo.getContentType(), "文件格式不可为空");
        VerifyParamsUtil.hasText(fileInfo.getUrl(), "文件路径不可为空");

        FileInfo file = fileMapper.selectByCode(fileInfo.getCode());
        if (file != null) {
            throw new CommentsRuntimeException(-1, "文件编号已存在");
        }

        fileMapper.insertSelective(fileInfo);

        // 将地址替换成绝对地址
        fileInfo.setUrl(minioConfig.getStaticUrl() + fileInfo.getUrl());
        // 根据文件类型保存对应信息

        return fileInfo;
    }

    /**
     * 通过编号获取单个文件信息
     * @param code code
     * @return
     */
    public FileInfo getFileInfo(String code) {
        // 验证参数
        VerifyParamsUtil.notNull(code, "文件编号不可为空");

        // 将地址替换成绝对地址
        FileInfo file = fileMapper.selectByCode(code);
        if (file != null) {
            file.setUrl(minioConfig.getStaticUrl() + file.getUrl());
        }

        return file;
    }
}
