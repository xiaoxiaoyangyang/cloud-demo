package com.yangyang.utils;

import com.alibaba.fastjson.JSONObject;
import com.yangyang.model.LoginAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.util.Map;

/**
 * @author gzy
 * @date 2020/4/25 21:49
 */
public class AppUserUtils {
    public static LoginAppUser loginAppUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
            authentication = oAuth2Authentication.getUserAuthentication();

            if (authentication instanceof UsernamePasswordAuthenticationToken) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = (UsernamePasswordAuthenticationToken) authentication;
                Map details = (Map) usernamePasswordAuthenticationToken.getDetails();
                details = (Map)details.get("principal");

                return JSONObject.parseObject(JSONObject.toJSONString(details),LoginAppUser.class);
            }
        }
        return null;
    }
}
