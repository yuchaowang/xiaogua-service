package com.xiaogua.comments.domain.pub;

import com.xiaogua.comments.config.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 文件工具类
 *
 * @author wangyc
 * @date 2018-10-19 9:34
 */
@Service
public class MinioDomain {

    @Autowired
    private MinioClient minioClient;

    @Autowired
    private MinioConfig minioConfig;

    /**
     * 文件上传
     *
     * @param stream      文件流
     * @param buketName   存储桶名称
     * @param objectName  对象名称
     * @param size        文件大小
     * @param contentType 文件类型
     * @return
     * @throws Exception Exception
     */
    public String uploadFile(InputStream stream, String buketName, String objectName, int size, String contentType)
        throws Exception {
        minioClient.putObject(
            PutObjectArgs.builder().bucket(buketName).object(objectName).stream(stream, stream.available(), -1)
                         .contentType(contentType).build());
        return String.format("/%s/%s", buketName, objectName);
    }

    /**
     * 文件下载
     *
     * @param buketName  存储桶名称
     * @param objectName 对象名称
     * @return
     * @throws Exception Exception
     */
    public InputStream downFile(String buketName, String objectName) throws Exception {
        return minioClient.getObject(GetObjectArgs.builder().bucket(buketName).object(objectName).build());
    }

    /**
     * 文件下载url
     *
     * @param url url
     * @return
     * @throws Exception Exception
     */
    public InputStream downFile(String url) throws Exception {
        String uri = url.replace(minioConfig.getStaticUrl(), "");
        String buketName = uri.substring(uri.indexOf("/") + 1, uri.indexOf("/", 2));
        String objectName = uri.substring(uri.lastIndexOf("/") + 1);

        return downFile(buketName, objectName);
    }
}
