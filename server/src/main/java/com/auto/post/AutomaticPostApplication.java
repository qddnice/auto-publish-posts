package com.auto.post;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * auto-publish-posts 后端启动入口。
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.auto.post.**.mapper")
public class AutomaticPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomaticPostApplication.class, args);
    }
}
