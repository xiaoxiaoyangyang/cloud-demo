package com.yangyang.oauth2.fegin;

import com.yangyang.model.AppUserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gzy
 * @date 2020/4/26 16:07
 */
@FeignClient(value = "mall-user")
public interface UserClient {
    @GetMapping(value = "/users-anon/internal",params = "username")
    AppUserDTO findByUsername(@RequestParam(value = "username") String username);
}
