package com.yangyang.log.service.impl;

import com.yangyang.log.mapper.LogMapper;
import com.yangyang.log.service.LogService;
import com.yangyang.model.Log;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.LogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.print.attribute.IntegerSyntax;
import java.util.Date;
import java.util.List;

/**
 * @author gzy
 * @date 2020/5/3 15:36
 */
@Primary
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;

    @Async
    @Override
    public void save(Log log) {
        if (log.getCreateTime() == null) {
            log.setCreateTime(new Date());
        }
        logMapper.save(log);
    }

    @Override
    public Page<Log> query(LogRequest logRequest) {
        Integer count = logMapper.count(logRequest);
        Page<Log> listPage = new Page<>();
        if (count > 0) {
            int start = (logRequest.getPage()-1)*logRequest.getSize();
            List<Log> logs = logMapper.queryList(logRequest, start, logRequest.getSize());
            listPage.setData(logs);
        }
        listPage.setTotal(count);
        listPage.setPage(logRequest.getPage());
        listPage.setSize(logRequest.getSize());
        return listPage;
    }
}
