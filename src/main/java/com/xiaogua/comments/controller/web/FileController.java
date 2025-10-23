package com.xiaogua.comments.controller.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaogua.comments.bean.common.RespHeader;
import com.xiaogua.comments.bean.common.ResponseBuilder;
import com.xiaogua.comments.bean.file.FileInput;
import com.xiaogua.comments.domain.FileDomain;
import com.xiaogua.comments.utils.CommentsRuntimeException;
import com.xiaogua.comments.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 文件controller
 * @author wangyc
 * @date 2020-11-24 03:06:46
 */
@RestController
public class FileController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileDomain fileDomain;

    /**
     * 上传文件
     * @param request request
     * @param stream 文件流
     * @return
     */
    @PostMapping(value = "/api/file/upload", produces = "application/json")
    public String upload(
        HttpServletRequest request, @RequestParam(name = "stream", required = true) MultipartFile stream) {
        ResponseBuilder builder = new ResponseBuilder();

        FileInput fileInput = new FileInput();
        fileInput.setName(stream.getOriginalFilename());
        fileInput.setContentType(stream.getContentType());

        try {
            byte[] bytes = stream.getBytes();
            // 为了异常时输出可读性高的json串
            String json = JSONObject.toJSONString(fileInput);

            try {
                fileInput.setStream(bytes);
                builder = fileDomain.upload(fileInput);
                builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
                LOGGER.info("上传文件[FileController.upload]正常，参数：{}", json);
            } catch (CommentsRuntimeException e) {
                LOGGER.warn("上传文件[FileController.upload]告警，参数：{}", json, e);
                RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
                builder.setRespHeader(respHeader);
            } catch (Exception e) {
                LOGGER.error("上传文件[FileController.upload]异常，参数：{}", json, e);
                RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
                builder.setRespHeader(respHeader);
            }
        } catch (IOException e) {
            LOGGER.error("上传文件[FileController.upload]异常，文件转换错误");
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.IO_ERROR_CODE, CommentsRuntimeException.IO_ERROR_STRING);
            builder.setRespHeader(respHeader);
        }

        return JSON.toJSONString(builder);
    }

    /**
     * 下载文件
     * @param request request
     * @param response response
     * @param code code
     * @return
     */
    @GetMapping(value = "/api/file/download")
    public String download(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code) {
        ResponseBuilder builder = new ResponseBuilder();
        try {
            builder = fileDomain.download(code, response);
            builder.setRespHeader(ResponseUtil.getSuccessRespHeader());
            LOGGER.info("下载文件[FileController.download]正常，参数：{}", code);
        } catch (CommentsRuntimeException e) {
            LOGGER.warn("下载文件[FileController.download]告警，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(e.getErrorCode(), e.getErrorMessage());
            builder.setRespHeader(respHeader);
        } catch (Exception e) {
            LOGGER.error("下载文件[FileController.download]异常，参数：{}", code, e);
            RespHeader respHeader = new RespHeader(CommentsRuntimeException.ERROR_CODE_100, CommentsRuntimeException.ERROR_CODE_100_MSG);
            builder.setRespHeader(respHeader);
        }
        return JSON.toJSONString(builder);
    }
}
