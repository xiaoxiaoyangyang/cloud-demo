package com.yangyang.user.mapper;

import com.yangyang.model.SysPermission;
import com.yangyang.model.SysRole;
import com.yangyang.pojo.request.SysPermissionRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author gzy
 * @date 2020/4/26 12:22
 */
@Mapper
public interface SysPermissionMapper {
    List<SysPermission> selectPermissions(List<Long> roleIds);
    List<SysPermission> selectByRoleId(Long roleId);

    void deleteBatch(Collection<Long> deletePermission);

    List<SysPermission> findByRoleId(Long roleId);

    SysPermission findByPermission(String permission);

    void save(SysPermission sysPermission);

    void update(SysPermission sysPermission);

    SysPermission findByPermissionId(Long permissionId);

    int count(@Param("sysPermissionRequest") SysPermissionRequest sysPermissionRequest);

    List<SysPermission> query(SysPermissionRequest sysPermissionRequest, int start, Integer size);

    SysPermission selectDetails(Long permissionId);
}
