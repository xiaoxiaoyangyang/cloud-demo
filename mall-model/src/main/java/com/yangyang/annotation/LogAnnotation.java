package com.yangyang.annotation;

import java.lang.annotation.*;

/**
 * @author gzy
 * @date 2020/5/5 14:46
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {
    String module();

    /**
     * 是否需要记录参数
     * @return
     */
    boolean recordParam() default true;
}
