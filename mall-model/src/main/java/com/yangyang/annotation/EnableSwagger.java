package com.yangyang.annotation;

import com.yangyang.config.SwaggerAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author gzy
 * @date 2020/4/25 20:45
 */
@Import(SwaggerAutoConfiguration.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableSwagger {
}
