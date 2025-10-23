package com.xiaogua.comments.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by wangyc on 2018/10/10.
 *
 * @version 2018/10/10 14:16 wangyc
 */
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    private String url;

    private String accessKey;

    private String secretKey;

    private String staticUrl;

    private String buket;

    private String programState;

    /**
     * 生成MinioClient
     *
     * @return
     */
    @Bean
    @Primary
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder().endpoint(url).credentials(accessKey, secretKey).build();
        return minioClient;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUrl() {
        return url;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public String getBuket() {
        return buket;
    }

    public void setBuket(String buket) {
        this.buket = buket;
    }

    public String getProgramState() {
        return programState;
    }

    public void setProgramState(String programState) {
        this.programState = programState;
    }
}
