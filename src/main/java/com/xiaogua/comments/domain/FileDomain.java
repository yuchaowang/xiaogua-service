package com.xiaogua.comments.domain;

import com.xiaogua.bizcode.utils.BizCodeUtil;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.file.FileInput;
import com.xiaogua.comments.bean.file.FileNoUrlRest;
import com.xiaogua.comments.bean.file.FileRest;
import com.xiaogua.comments.bl.CommentsReplyBl;
import com.xiaogua.comments.bl.FileBl;
import com.xiaogua.comments.common.constant.Constant;
import com.xiaogua.comments.common.constant.TableCode;
import com.xiaogua.comments.config.MinioConfig;
import com.xiaogua.comments.dal.entity.FileInfo;
import com.xiaogua.comments.domain.pub.MinioDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import com.xiaogua.comments.utils.VerifyParamsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 文件 domain
 *
 * @author wangyc
 * @date 2020-11-19 17:31:17
 **/

@Service
public class FileDomain {

    @Autowired
    private CommentsReplyBl commentsReplyBl;

    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    private MinioConfig minioConfig;

    @Autowired
    private MinioDomain minioDomain;

    @Autowired
    private FileBl fileBl;

    /**
     * 上传文件
     *
     * @param fileInput 文件信息
     * @return
     */
    public ResponseBuilder upload(FileInput fileInput) {
        // 获取应用信息
        String buketName = minioConfig.getBuket();
        String contentType =
            StringUtils.isEmpty(fileInput.getContentType()) ? Constant.FILE_DEFAULT_MIME : fileInput.getContentType();
        String name = fileInput.getName();

        // 获取文件拓展名
        String extension = name.lastIndexOf(".") == -1 ? "" : name.substring(name.lastIndexOf(".") + 1);
        String code = BizCodeUtil.genLongBizCode(TableCode.T_FILE.getCode());
        // 转换文件流
        InputStream inputStream = new ByteArrayInputStream(fileInput.getStream());

        FileInfo fileInfo = new FileInfo();
        fileInfo.setSize(fileInput.getStream().length);
        // 上传文件

        try {
            fileInfo.setUrl(minioDomain.uploadFile(inputStream, buketName, StringUtils.isEmpty(extension) ? code
                : String.format("%s.%s", code, extension), fileInfo.getSize(), contentType));
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommentsRuntimeException("文件上传失败");
        }
        fileInfo.setCode(code);
        fileInfo.setContentType(contentType);
        fileInfo.setName(name);

        FileInfo info = fileBl.upload(fileInfo);

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        builder.setData(fileInfo);
        return builder;
    }

    /**
     * 下载文件
     *
     * @param code     code
     * @param response response
     * @return
     */
    public ResponseBuilder download(String code, HttpServletResponse response) {
        // 1.验证参数
        VerifyParamsUtil.hasText(code, "文件编号不可为空");

        // 2.获取文件信息
        FileInfo file = getFile(code);
        if (file == null) {
            throw new CommentsRuntimeException(-1, "文件不存在");
        }

        // 3.下载文件
        InputStream in;
        try {
            in = minioDomain.downFile(file.getUrl());
        } catch (Exception e) {
            throw new CommentsRuntimeException(-1, "下载失败");
        }

        // 4.修改文件名,设置文件流
        response.reset();
        response.setContentType(file.getContentType());
        try {
            response.addHeader("Content-Disposition",
                               "attachment; filename*=UTF-8''" + URLEncoder.encode(file.getName(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new CommentsRuntimeException(-1, "文件名错误");
        }
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = in.read(b)) > 0) {
                response.getOutputStream().write(b, 0, len);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseBuilder builder = new ResponseBuilder();
        builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
        return builder;
    }

    /**
     * 获取文件
     *
     * @param code 文件编号
     * @return
     */
    private FileInfo getFile(String code) {
        VerifyParamsUtil.hasText(code, "文件编号不可为空");

        FileInfo fileInfo = fileBl.getFileInfo(code);

        return fileInfo;
    }

    /**
     * 获取文件rest信息
     *
     * @param code 文件编号
     * @return
     */
    public FileRest getFileRest(String code) {
        VerifyParamsUtil.hasText(code, "文件编号不可为空");

        FileInfo fileInfo = fileBl.getFileInfo(code);
        FileRest fileRest = null;
        if (fileInfo != null) {
            fileRest = new FileRest(fileInfo);
        } else {
            fileRest = new FileRest();
        }

        return fileRest;
    }

    /**
     * 获取文件rest信息-无链接
     *
     * @param code 文件编号
     * @return
     */
    public FileNoUrlRest getFileNoUrlRest(String code) {
        VerifyParamsUtil.hasText(code, "文件编号不可为空");

        FileInfo fileInfo = fileBl.getFileInfo(code);
        FileNoUrlRest fileRest = null;
        if (fileInfo != null) {
            fileRest = new FileNoUrlRest(fileInfo);
        } else {
            fileRest = new FileNoUrlRest();
        }

        return fileRest;
    }

    /**
     * 获取文件路径
     *
     * @param code 文件编号
     * @return
     */
    public String getFileUrl(String code) {
        VerifyParamsUtil.hasText(code, "文件编号不可为空");

        FileInfo fileInfo = fileBl.getFileInfo(code);

        return fileInfo == null ? Constant.EMPTY_STR : fileInfo.getUrl();
    }

}


