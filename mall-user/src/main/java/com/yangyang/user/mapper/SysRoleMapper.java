package com.yangyang.user.mapper;

import com.yangyang.model.SysRole;
import com.yangyang.pojo.request.SysRoleRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/26 12:22
 */
@Mapper
public interface SysRoleMapper {
    Set<SysRole> selectByRoleId(Long userId);

    SysRole findByRoleCode(String roleName);

    void save(SysRole sysRole);

    SysRole findByRoleId(Long roleId);

    void delete(Long roleId);

    void update(SysRole sysRole);

    SysRole queryRole(Long roleId);

    int count(@Param("sysRoleRequest") SysRoleRequest sysRoleRequest);

    List<SysRole> selectByParams(@Param("sysRoleRequest")SysRoleRequest sysRoleRequest, @Param("start") int start,@Param("size") Integer size);
}
