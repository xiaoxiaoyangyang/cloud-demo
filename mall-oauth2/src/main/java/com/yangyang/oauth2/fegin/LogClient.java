package com.yangyang.oauth2.fegin;

import com.yangyang.model.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author gzy
 * @date 2020/5/5 14:31
 */
@FeignClient(value = "mall-log")
public interface LogClient {
    @PostMapping("/logs-anon/internal")
    public void save(@RequestBody Log log);
}
