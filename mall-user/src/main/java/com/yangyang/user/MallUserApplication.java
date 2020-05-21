package com.yangyang.user;

import com.yangyang.annotation.EnableSwagger;
import com.yangyang.exception.ExceptionHandlerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author gzy
 * @date 2020/4/25 18:12
 */
@EnableSwagger
@Import(ExceptionHandlerAdvice.class)
@EnableDiscoveryClient
@SpringBootApplication
public class MallUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallUserApplication.class,args);
    }
}
