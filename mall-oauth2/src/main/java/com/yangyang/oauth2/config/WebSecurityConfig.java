package com.yangyang.oauth2.config;

import com.yangyang.oauth2.service.UserDetailsServiceImpl;
import com.yangyang.utils.PermitAllUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gzy
 * @date 2020/4/26 12:45
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
//                .requestMatcher(new Oauth2RequestMatcher())
                .authorizeRequests()
                .antMatchers(PermitAllUrlUtil.permitAllUrl("/swagger-resources/**","/v2/api-docs")).permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }

//    private static class Oauth2RequestMatcher implements RequestMatcher {
//
//        @Override
//        public boolean matches(HttpServletRequest httpServletRequest) {
//            if (httpServletRequest.getParameter("access_token") != null) {
//                return true;
//            }
//            String authorization = httpServletRequest.getHeader("Authorization");
//            if (authorization != null) {
//                if (authorization.startsWith("Bearer")) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }

}
