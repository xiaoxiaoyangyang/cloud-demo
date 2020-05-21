package com.yangyang.log.controller;

import com.alibaba.fastjson.JSON;
import com.yangyang.log.service.LogService;
import com.yangyang.model.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author gzy
 * @date 2020/5/5 14:24
 */
@Slf4j
@Component
public class LogConsumer {
    @Autowired
    private LogService logService;

    /**
     * 接收消息的方法
     */
    public void receiveMessage(String message) {
        log.info("---------------------日志信息打印：{}",message);
        Log log = JSON.parseObject(message, Log.class);
        logService.save(log);
    }
}
