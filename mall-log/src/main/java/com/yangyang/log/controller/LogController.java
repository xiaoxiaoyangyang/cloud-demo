package com.yangyang.log.controller;

import com.yangyang.log.service.LogService;
import com.yangyang.model.Log;
import com.yangyang.model.Page;
import com.yangyang.pojo.request.LogRequest;
import com.yangyang.pojo.response.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gzy
 * @date 2020/5/3 15:37
 */
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @PostMapping("/logs-anon/internal")
    public void save(@RequestBody Log log){
        logService.save(log);
    }

    @PreAuthorize("hasAuthority('back:log:query')")
    @PostMapping("/logs/query")
    public ResultResponse<Page<Log>> query(@RequestBody LogRequest logRequest){
        Page<Log> query = logService.query(logRequest);
        ResultResponse<Page<Log>> resultResponse = new ResultResponse();
        resultResponse.setSuccess(true);
        resultResponse.setData(query);
        resultResponse.setCode(HttpStatus.OK.value());
        return resultResponse;
    }
}
