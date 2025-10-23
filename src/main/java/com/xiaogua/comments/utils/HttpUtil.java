package com.xiaogua.comments.utils;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * http请求工具类
 * @author wangyc
 * @date 2021-2-9 23:30:17
 **/
public class HttpUtil {

    public static String doPost(String postUrl, String formData) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(postUrl);
        StringEntity postingString = new StringEntity(formData, "UTF-8");
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        String content = EntityUtils.toString(response.getEntity());
        return content;
    }

    public static String doGet(String getUrl) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(getUrl);
        httpGet.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(httpGet);
        String content = EntityUtils.toString(response.getEntity());
        return content;
    }
}
