package com.xiaogua.comments;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 跨域请求处理
 *
 * @author wangyc
 * @date 2020-11-10 14:34:48
 */
@Configuration
public class GlobalCorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            //重写父类提供的跨域请求处理的接口
            public void addCorsMappings(CorsRegistry registry) {
                //添加映射路径
                registry.addMapping("/**")
                    //放行哪些原始域
                    .allowedOrigins("*")
                    //放行哪些原始域(请求方式)
                    .allowedMethods("*")
                    //放行哪些原始域(头部信息)
                    .allowedHeaders("*")
                    //暴露哪些头部信息（因为跨域访问默认不能获取全部头部信息）
                    .exposedHeaders("access-control-allow-headers", "access-control-allow-methods",
                        "access-control-allow-origin", "access-control-max-age", "X-Frame-Options")
                    //是否发送Cookie信息
                    .allowCredentials(true);
            }
        };
    }
}
