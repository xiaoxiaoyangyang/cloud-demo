package com.yangyang.user.service;

import com.yangyang.model.*;
import com.yangyang.pojo.request.UserRequest;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/25 19:15
 */
public interface AppUserService {
    AppUserDTO selectByUsername(String username);

    void saveUser(AppUser appUser);

    Page<AppUser> selectByParams(UserRequest userRequest);

    AppUser selectOne(Long userId);

    void update(AppUser appUser);

    void updatePassword(Long userId, String oldPassword, String newPassword);

    void roleToUser(Long userId, Set<Long> roleIds);

    List<SysAppUserRole> findRolesByUserId(Long userId);
}
