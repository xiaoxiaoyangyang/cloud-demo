package com.yangyang.log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author gzy
 * @date 2020/5/3 15:31
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MallLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MallLogApplication.class,args);
    }
}
