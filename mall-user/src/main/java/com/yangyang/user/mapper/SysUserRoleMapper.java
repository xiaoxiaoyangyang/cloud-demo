package com.yangyang.user.mapper;

import com.yangyang.model.SysAppUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

/**
 * @author gzy
 * @date 2020/4/26 22:20
 */
@Mapper
public interface SysUserRoleMapper {
    void deleteUserRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

    void batchInsert(List<SysAppUserRole> sysAppUserRoles);

    List<SysAppUserRole>  findRoleByUserId(@Param("userId") Long userId, @Param("roleId") Long roleId);
}
