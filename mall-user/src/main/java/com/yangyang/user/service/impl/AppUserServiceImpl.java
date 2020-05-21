package com.yangyang.user.service.impl;

import com.yangyang.enums.UserEnableType;
import com.yangyang.enums.UserType;
import com.yangyang.exception.ServiceException;
import com.yangyang.model.*;
import com.yangyang.pojo.request.UserRequest;
import com.yangyang.user.mapper.AppUserMapper;
import com.yangyang.user.mapper.SysPermissionMapper;
import com.yangyang.user.mapper.SysRoleMapper;
import com.yangyang.user.mapper.SysUserRoleMapper;
import com.yangyang.user.service.AppUserService;
import com.yangyang.user.service.SysPremissionService;
import com.yangyang.user.service.SysRoleService;
import com.yangyang.user.utils.BeanDeepCopyUtils;
import com.yangyang.user.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.print.attribute.IntegerSyntax;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author gzy
 * @date 2020/4/25 19:15
 */
@Slf4j
@Service
public class AppUserServiceImpl implements AppUserService {
    @Autowired
    private AppUserMapper appUserMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysPremissionService sysPremissionService;

    @Override
    public AppUserDTO selectByUsername(String username) {
        AppUser appUser = appUserMapper.selectByUsername(username);
        if (appUser != null) {
            AppUserDTO appUserDTO = BeanDeepCopyUtils.copyProperties(appUser, AppUserDTO.class);

            Set<SysRole> sysRoles = sysRoleService.selectByRoleId(appUserDTO.getUserId());
            appUserDTO.setSysRoles(sysRoles);

            if (CollectionUtils.isNotEmpty(sysRoles)) {
                List<Long> roleIds = sysRoles.stream().map(SysRole::getRoleId)
                        .collect(Collectors.toList());

                List<SysPermission> sysPermissions = sysPremissionService.selectPermissions(roleIds);

                if (CollectionUtils.isNotEmpty(sysPermissions)) {
                    Set<String> permissions = sysPermissions.stream()
                            .map(p -> p.getPermission())
                            .collect(Collectors.toSet());
                    appUserDTO.setPermissions(permissions);
                }
            }
            return appUserDTO;
        }
        return null;
    }

    @Override
    public void saveUser(AppUser appUser) {
        AppUser user = appUserMapper.selectByUsername(appUser.getUsername());
        if (user != null) {
            throw new IllegalArgumentException("用户名已存在");
        }
        if (appUser.getType() == null) {
            appUser.setType(UserType.BACKEND_USER.type);
        }
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setEnable(UserEnableType.ENABLE.type);
        appUser.setType(UserType.BACKEND_USER.type);
        appUser.setCreateTime(new Date());
        appUser.setUpdateTime(appUser.getCreateTime());
        appUserMapper.save(appUser);
        log.info("---------------->添加用户：{}",appUser);
    }

    @Override
    public Page<AppUser> selectByParams(UserRequest userRequest) {
        int count = appUserMapper.count(userRequest);
        Page<AppUser> appUserPage = new Page<>();
        if (count > 0) {
            int start = (userRequest.getPage() -1) * userRequest.getSize();
            List<AppUser> appUsers = appUserMapper.selectByParams(userRequest,start,userRequest.getSize());
            appUserPage.setData(appUsers);
        }
        appUserPage.setPage(userRequest.getPage());
        appUserPage.setSize(userRequest.getSize());
        appUserPage.setTotal(count);
        return appUserPage;
    }

    @Override
    public AppUser selectOne(Long userId) {
        AppUser user = appUserMapper.selectOneByUserId(userId);
        return user;
    }

    @Override
    public void update(AppUser appUser) {
        appUser.setUpdateTime(new Date());
        appUserMapper.updateAppUser(appUser);
    }

    @Override
    public void updatePassword(Long userId, String oldPassword, String newPassword) {
        AppUser user = appUserMapper.selectOneByUserId(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }
        if (StringUtils.isNotBlank(oldPassword)) {
            if ( bCryptPasswordEncoder.matches(oldPassword,user.getPassword())) {
                throw new ServiceException("老密码输入错误");
            }
        }
        AppUser updateUser = new AppUser();
        updateUser.setUserId(userId);
        updateUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        updateUser.setUpdateTime(new Date());
        appUserMapper.updateAppUser(updateUser);
    }

    @Transactional
    @Override
    public void roleToUser(Long userId, Set<Long> roleIds) {
        AppUser user = appUserMapper.selectOneByUserId(userId);
        if (user == null) {
            throw new ServiceException("用户不存在");
        }

        sysUserRoleMapper.deleteUserRole(userId,null);
        if (CollectionUtils.isNotEmpty(roleIds)) {
            List<SysAppUserRole> sysAppUserRoles = roleIds.stream().map(roleId -> {
                SysAppUserRole sysAppUserRole = new SysAppUserRole();
                sysAppUserRole.setRoleId(roleId);
                sysAppUserRole.setUserId(userId);
                return sysAppUserRole;
            }).collect(Collectors.toList());
            sysUserRoleMapper.batchInsert(sysAppUserRoles);
        }
    }

    @Override
    public List<SysAppUserRole> findRolesByUserId(Long userId) {
        List<SysAppUserRole> roleByUserId = sysUserRoleMapper.findRoleByUserId(userId, null);
        return roleByUserId;
    }
}
