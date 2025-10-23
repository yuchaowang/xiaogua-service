package com.xiaogua.comments;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * comments 启动类
 *
 * @author wangyc
 * @date 2020-11-9 02:42:52
 **/
@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.xiaogua"})
@MapperScan(basePackages = "com.xiaogua.comments.dal")
@EnableAsync
public class CommentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsServiceApplication.class, args);
    }

}
