package com.yangyang.oauth2.service;

import com.yangyang.exception.ServiceException;
import com.yangyang.model.AppUserDTO;
import com.yangyang.model.LoginAppUser;
import com.yangyang.oauth2.fegin.UserClient;
import com.yangyang.oauth2.utils.BeanDeepCopyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author gzy
 * @date 2020/4/26 16:13
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw  new ServiceException("username is blank");
        }

        AppUserDTO byUsername = userClient.findByUsername(username);
        if (byUsername == null) {
            throw new ServiceException("user " + username + " is not exist");
        } else if (byUsername.getEnable() != 1) {
            throw new ServiceException("user "+username+" is invalid");
        }
        LoginAppUser loginAppUser = BeanDeepCopyUtils.copyProperties(byUsername, LoginAppUser.class);
        return loginAppUser;
    }
}
