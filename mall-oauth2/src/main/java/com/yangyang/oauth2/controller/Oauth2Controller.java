package com.yangyang.oauth2.controller;

import com.yangyang.model.Log;
import com.yangyang.oauth2.fegin.LogClient;
import com.yangyang.pojo.response.ResultResponse;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @author gzy
 * @date 2020/4/26 17:33
 */
@Slf4j
@Api(tags = "oauth2管理模块")
@RestController
public class Oauth2Controller {
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private LogClient logClient;



    @DeleteMapping(value = "/remove_token",params = "access_token")
    public void removeToken(@RequestParam String access_token) {
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        if (oAuth2AccessToken == null) {
            tokenStore.removeAccessToken(oAuth2AccessToken);
            tokenStore.removeRefreshToken(oAuth2AccessToken.getRefreshToken());
        }
    }

    public void saveLogoutLog(String username) {
        log.info("-------:{}推出登录",username);
        CompletableFuture.runAsync(()->{
            try {
                Log log = new Log();
                log.setModule("LOGOUT");
                log.setUsername(username);
                log.setCreateTime(new Date());
                logClient.save(log);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
