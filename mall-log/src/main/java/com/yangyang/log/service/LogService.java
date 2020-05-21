package com.yangyang.log.service;

import com.yangyang.model.Log;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.LogRequest;

import java.util.List;

/**
 * @author gzy
 * @date 2020/5/3 15:36
 */
public interface LogService {
    void save(Log log);

    Page<Log> query(LogRequest logRequest);
}
