package com.yangyang.oauth2;

import com.yangyang.annotation.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author gzy
 * @date 2020/4/26 12:31
 */
@EnableFeignClients
@EnableSwagger
@EnableDiscoveryClient
@SpringBootApplication
public class MallOauth2Application {
    public static void main(String[] args) {
        SpringApplication.run(MallOauth2Application.class,args);
    }
}
