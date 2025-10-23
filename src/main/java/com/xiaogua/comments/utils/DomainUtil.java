package com.xiaogua.comments.utils;

import com.google.common.net.HttpHeaders;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取域名工具
 *
 * @author huyy
 * @date 2018-11-14 15:46
 */
public class DomainUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(DomainUtil.class);

    /**
     * 获取一级域名
     *
     * @param request request
     * @return
     */
    public static String getFirstDomain(HttpServletRequest request) {
        // 获取请求host
        String host = getHost(request);
        // 把host拆分成一级域名
        String domain = getDomain(host);

        return domain;
    }

    /**
     * 获取域名/IP地址和端口号
     *
     * @param request request
     * @return 域名/IP地址:端口号
     */
    private static String getHost(HttpServletRequest request) {
        // 通过nginx反向代理
        String host = request.getHeader(HttpHeaders.X_FORWARDED_HOST);
        if (host == null) {
            // 直接访问
            host = request.getHeader(HttpHeaders.HOST);
            LOGGER.debug("从请求头[Host]获取域名为：{}", host);
        } else {
            LOGGER.debug("从请求头[X-Forwarded-Host]获取域名为：{}", host);
        }
        if (host == null) {
            LOGGER.warn("从请求头[X-Forwarded-Host, Host]获取域名失败");
            throw new CommentsRuntimeException("获取域名失败!");
        }
        return host;
    }

    /**
     * 获取域名
     *
     * @param host 域名/IP地址:端口号
     * @return
     */
    private static String getDomain(String host) {
        String domain = host;
        // 域名等级，1为一级域名
        int level = 1;
        // 去除端口号
        domain = domain.split(":")[0];
        String pattern = "^([a-zA-Z0-9]+([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6}";
        boolean isDomain = Pattern.matches(pattern, domain);
        // 使用域名+端口号形式则解析域名
        if (isDomain) {
            String[] domains = domain.split("\\.");
            int length = domains.length;
            if (length < level + 1) {
                LOGGER.warn("域名({})转换{}级域名出错！", host, level + 1);
                throw new CommentsRuntimeException("域名错误!");
            }
            domain = domains[length - 2] + "." + domains[length - 1];
        }
        return domain;
    }
}
