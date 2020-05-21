package com.yangyang.user.mapper;

import com.yangyang.model.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * @author gzy
 * @date 2020/4/26 22:20
 */
@Mapper
public interface SysRolePermissionMapper {
    void delete(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    void deleteBatch(Collection<Long> deletePermission);

    void batchSave(List<SysRolePermission> sysRolePermissionStream);

}
