package com.yangyang.mall.file.config;

import com.yangyang.utils.PermitAllUrlUtil;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * @author gzy
 * @date 2020/4/25 21:06
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Value("${file.local.prefix}")
    private String localFilePrefix;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private String[] permissionUrl = { "/users/query","/users/admin/update","/users/{userId}/details","/users/me/update","/users/me/password","/users/{userId}/password","/users/{userId}/roles","/users/{userId}/viewRoles",
            "/permission/save","/permission/update", "/permission/{permissionId}/delete", "/permission/query", "/permission/{permissionId}/details",
            "/roles/save","/roles/{roleId}/delete","/roles/{roleId}/details","/roles/update","/roles/query","/roles/{roleId}/permissions","/roles/{roleId}/viewPermissions"};

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(PermitAllUrlUtil.permitAllUrl("/files-anon/**",localFilePrefix+"/**")).permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(new RedisTokenStore(redisConnectionFactory));
    }
}
