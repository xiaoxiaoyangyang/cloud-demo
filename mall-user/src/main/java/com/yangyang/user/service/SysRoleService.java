package com.yangyang.user.service;

import com.yangyang.model.Page;
import com.yangyang.model.SysPermission;
import com.yangyang.model.SysRole;
import com.yangyang.pojo.request.SysRoleRequest;

import java.util.List;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/26 12:22
 */
public interface SysRoleService {
    Set<SysRole> selectByRoleId(Long userId);

    void save(SysRole sysRole);

    void deleteRole(Long roleId);

    void update(SysRole sysRole);

    void permissionToRole(Long roleId, Set<Long> permissionIds);

    List<SysPermission> findPermission(Long roleId);

    SysRole query(Long roleId);

    Page<SysRole> querySysRole(SysRoleRequest sysRoleRequest);
}
