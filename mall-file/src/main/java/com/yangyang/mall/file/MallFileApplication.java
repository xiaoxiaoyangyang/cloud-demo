package com.yangyang.mall.file;

import com.yangyang.exception.ExceptionHandlerAdvice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @author gzy
 * @date 2020/5/5 17:57
 */
@EnableDiscoveryClient
@Import(ExceptionHandlerAdvice.class)
@SpringBootApplication
public class MallFileApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallFileApplication.class,args);
    }
}
