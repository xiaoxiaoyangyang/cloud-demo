package com.yangyang.mall.file.config;

import com.yangyang.mall.file.utils.ResourceUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author gzy
 * @date 2020/5/5 18:26
 */
@Configuration
public class LocalFilePathConfig {
    @Value("${file.local.path}")
    private String localFilePath;
    @Value("${file.local.prefix}")
    public String localFilePrefix;

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer(){
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler(localFilePrefix+"/**")
                        .addResourceLocations(ResourceUtils.FILE_URL_PREFIX + localFilePath + File.separator);
            }
        };
    }
}
