package com.yangyang.user.service.impl;

import com.yangyang.exception.ServiceException;
import com.yangyang.model.Page;
import com.yangyang.model.SysPermission;
import com.yangyang.pojo.request.SysPermissionRequest;
import com.yangyang.user.mapper.SysPermissionMapper;
import com.yangyang.user.mapper.SysRolePermissionMapper;
import com.yangyang.user.service.SysPremissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gzy
 * @date 2020/4/26 12:21
 */
@Slf4j
@Service
public class SysPremissionServiceImpl implements SysPremissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysPermission> selectPermissions(List<Long> roleIds) {
        return sysPermissionMapper.selectPermissions(roleIds);
    }

    @Override
    public void save(SysPermission sysPermission) {
        SysPermission byPermission = sysPermissionMapper.findByPermission(sysPermission.getPermission());
        if (byPermission != null) {
            throw new ServiceException("权限标识已经存在");
        }
        sysPermission.setCreateTime(new Date());
        sysPermission.setUpdateTime(sysPermission.getCreateTime());
        sysPermissionMapper.save(sysPermission);
    }

    @Override
    public void update(SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        sysPermissionMapper.update(sysPermission);
    }

    @Transactional
    @Override
    public void delete(Long permissionId) {
        SysPermission byPermissionId = sysPermissionMapper.findByPermissionId(permissionId);
        if (byPermissionId == null) {
            throw new ServiceException("权限标识不存在");
        }
        List<Long> collect = Stream.of(permissionId).collect(Collectors.toList());
        sysPermissionMapper.deleteBatch(collect);
        sysRolePermissionMapper.delete(null,permissionId);
    }

    @Override
    public Page<SysPermission> query(SysPermissionRequest sysPermissionRequest) {
        int count = sysPermissionMapper.count(sysPermissionRequest);
        Page<SysPermission> sysPermissionPage = new Page<>();
        if (count >0) {
            int start = (sysPermissionRequest.getPage() -1)*sysPermissionRequest.getSize();
            List<SysPermission> query = sysPermissionMapper.query(sysPermissionRequest, start, sysPermissionRequest.getSize());
            sysPermissionPage.setData(query);
        }
        sysPermissionPage.setTotal(count);
        sysPermissionPage.setPage(sysPermissionRequest.getPage());
        sysPermissionPage.setSize(sysPermissionRequest.getSize());
        return sysPermissionPage;
    }

    @Override
    public SysPermission queryDetails(Long permissionId) {
        SysPermission sysPermission = sysPermissionMapper.selectDetails(permissionId);
        return sysPermission;
    }
}
