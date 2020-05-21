package com.yangyang.user.service;

import com.yangyang.model.Page;
import com.yangyang.model.SysPermission;
import com.yangyang.pojo.request.SysPermissionRequest;

import java.util.List;

/**
 * @author gzy
 * @date 2020/4/26 12:20
 */
public interface SysPremissionService {
    List<SysPermission> selectPermissions(List<Long> roleIds);

    void save(SysPermission sysPermission);

    void update(SysPermission sysPermission);

    void delete(Long permissionId);

    Page<SysPermission> query(SysPermissionRequest sysPermissionRequest);

    SysPermission queryDetails(Long permissionId);
}
