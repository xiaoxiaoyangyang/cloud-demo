package com.yangyang.user.service.impl;

import com.yangyang.exception.ServiceException;
import com.yangyang.model.*;
import com.yangyang.pojo.request.SysRoleRequest;
import com.yangyang.user.mapper.SysPermissionMapper;
import com.yangyang.user.mapper.SysRoleMapper;
import com.yangyang.user.mapper.SysRolePermissionMapper;
import com.yangyang.user.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gzy
 * @date 2020/4/26 12:22
 */
@Slf4j
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public Set<SysRole> selectByRoleId(Long userId) {
        return sysRoleMapper.selectByRoleId(userId);
    }

    @Override
    public void save(SysRole sysRole) {
        SysRole byRoleCode = sysRoleMapper.findByRoleCode(sysRole.getRoleName());
        if (byRoleCode != null) {
            throw new ServiceException("角色名已经存在");
        }
        sysRole.setCreateTime(new Date());
        sysRole.setUpdateTime(sysRole.getCreateTime());
        sysRoleMapper.save(sysRole);
    }

    @Transactional
    @Override
    public void deleteRole(Long roleId) {
        sysRoleMapper.delete(roleId);
        sysRolePermissionMapper.delete(roleId,null);
    }

    @Override
    public void update(SysRole sysRole) {
        sysRole.setUpdateTime(new Date());
        sysRoleMapper.update(sysRole);
    }

    @Transactional
    @Override
    public void permissionToRole(Long roleId,  Set<Long> permissionIds) {
        SysRole byRoleId = sysRoleMapper.findByRoleId(roleId);
        if (byRoleId == null) {
            throw new ServiceException("角色不存在");
        }
        List<SysPermission> sysPermissions = sysPermissionMapper.selectByRoleId(roleId);
        List<Long> oldPermissions = sysPermissions.stream()
                .map(sysPermission -> sysPermission.getPermissionId())
                .collect(Collectors.toList());

        Collection<Long> deletePermission = CollectionUtils.subtract(oldPermissions, permissionIds);
        if (CollectionUtils.isNotEmpty(deletePermission)) {
            sysRolePermissionMapper.deleteBatch(deletePermission);
        }
        Collection<Long> addPermission = CollectionUtils.subtract(permissionIds, oldPermissions);
        List<SysRolePermission> sysRolePermissions = addPermission.stream()
                .map(value -> {
                    SysRolePermission sysRolePermission = new SysRolePermission();
                    sysRolePermission.setPermissionId(value);
                    sysRolePermission.setRoleId(roleId);
                    return sysRolePermission;
                }).collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(sysRolePermissions)) {
            sysRolePermissionMapper.batchSave(sysRolePermissions);
        }
    }

    @Override
    public List<SysPermission> findPermission(Long roleId) {

        List<SysPermission> byRoleIds = sysPermissionMapper.findByRoleId(roleId);
        return byRoleIds;
    }

    @Override
    public SysRole query(Long roleId) {
        SysRole sysRole = sysRoleMapper.queryRole(roleId);
        return sysRole;
    }

    @Override
    public  Page<SysRole> querySysRole(SysRoleRequest sysRoleRequest) {
        int count = sysRoleMapper.count(sysRoleRequest);
        Page<SysRole> sysRolePage = new Page<>();
        if (count > 0) {
            int start = (sysRoleRequest.getPage() -1) * sysRoleRequest.getSize();
            List<SysRole> sysRoles = sysRoleMapper.selectByParams(sysRoleRequest, start, sysRoleRequest.getSize());
            sysRolePage.setData(sysRoles);
        }
        sysRolePage.setTotal(count);
        sysRolePage.setPage(sysRoleRequest.getPage());
        sysRolePage.setSize(sysRoleRequest.getSize());
        return sysRolePage;
    }
}
