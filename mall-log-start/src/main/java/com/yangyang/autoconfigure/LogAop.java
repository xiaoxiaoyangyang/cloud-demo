package com.yangyang.autoconfigure;

import com.alibaba.fastjson.JSON;
import com.yangyang.annotation.LogAnnotation;
import com.yangyang.model.Log;
import com.yangyang.model.LoginAppUser;
import com.yangyang.utils.AppUserUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

/**
 * @author gzy
 * @date 2020/5/5 15:10
 */
@Aspect
public class LogAop {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Around(value = "@annotation(com.yangyang.annotation.LogAnnotation)")
    public Object logSave(ProceedingJoinPoint joinPoint) {
        Log log = new Log();
        log.setCreateTime(new Date());
        LoginAppUser loginAppUser = AppUserUtils.loginAppUser();
        if (loginAppUser != null) {
            log.setUsername(loginAppUser.getUsername());
        }

        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        LogAnnotation declaredAnnotation = methodSignature.getMethod().getDeclaredAnnotation(LogAnnotation.class);
        String module = declaredAnnotation.module();
        log.setModule(module);
        if (declaredAnnotation.recordParam()) {
            String[] parameterNames = methodSignature.getParameterNames();
            if (parameterNames != null && parameterNames.length >0) {
                Object[] args = joinPoint.getArgs();
                HashMap<String, Object> map = new HashMap<>();
                for (int i=0;i<parameterNames.length;i++) {
                    map.put(parameterNames[i],args[i]);
                }
                log.setParams(JSON.toJSONString(map));
            }
        }
        try {
            Object proceed = joinPoint.proceed();
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }finally {
            CompletableFuture.runAsync(() -> {
                stringRedisTemplate.convertAndSend("testLog",JSON.toJSONString(log));
                System.out.println("--------------记录日志的打印" + JSON.toJSONString(log));
            });
        }
    }
}
