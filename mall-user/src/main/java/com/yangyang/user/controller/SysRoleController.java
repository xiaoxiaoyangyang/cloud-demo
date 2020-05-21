package com.yangyang.user.controller;

import com.yangyang.model.Page;
import com.yangyang.model.SysPermission;
import com.yangyang.model.SysRole;
import com.yangyang.pojo.request.SysRoleRequest;
import com.yangyang.pojo.response.ResultResponse;
import com.yangyang.user.service.SysRoleService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/26 12:20
 */
@Api(tags = "角色管理")
@RestController
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService;

    @PreAuthorize("hasAuthority('back:role:save')")
    @PostMapping("/roles/save")
    public ResultResponse saveRole(@RequestBody SysRole sysRole){
        if (StringUtils.isEmpty(sysRole.getCode())) {
            throw  new IllegalArgumentException("角色code不能为空");
        }
        if (StringUtils.isEmpty(sysRole.getRoleName())) {
            throw new IllegalArgumentException("角色的role_name不能为空");
        }
        sysRoleService.save(sysRole);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAuthority('back:role:delete')")
    @DeleteMapping("/roles/{roleId}/delete")
    public ResultResponse deleteRole(@PathVariable Long roleId){
        sysRoleService.deleteRole(roleId);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }

    @PreAuthorize("hasAuthority('back:role:details')")
    @GetMapping("/roles/{roleId}/details")
    public ResultResponse<SysRole> queryRole(@PathVariable Long roleId){
        SysRole query = sysRoleService.query(roleId);
        ResultResponse<SysRole> appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        appUserResultResponse.setData(query);
        return appUserResultResponse;
    }

    @PreAuthorize("hasAuthority('back:role:update')")
    @PostMapping("/roles/update")
    public ResultResponse updateRole(@RequestBody SysRole sysRole){
        if (StringUtils.isEmpty(sysRole.getRoleName())) {
            throw new IllegalArgumentException("角色的role_name不能为空");
        }
        sysRoleService.update(sysRole);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAuthority('back:role:query')")
    @PostMapping("/roles/query")
    public ResultResponse<Page<SysRole>> queryRole(@RequestBody SysRoleRequest sysRoleRequest){
        Page<SysRole> sysRolePage = sysRoleService.querySysRole(sysRoleRequest);
        ResultResponse<Page<SysRole>> appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        appUserResultResponse.setData(sysRolePage);
        return appUserResultResponse;
    }

    @PreAuthorize("hasAuthority('back:role:permission:set')")
    @PostMapping("/roles/{roleId}/permissions")
    public ResultResponse permissionToRole(@PathVariable Long roleId, @RequestBody Set<Long> permissionIds){
        sysRoleService.permissionToRole(roleId,permissionIds);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAnyAuthority('back:role:view:permission')")
    @GetMapping("/roles/{roleId}/viewPermissions")
    public ResultResponse<List<SysPermission>> findPermissionByRoleId(@PathVariable Long roleId){
        List<SysPermission> permissions = sysRoleService.findPermission(roleId);
        ResultResponse<List<SysPermission>> appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        appUserResultResponse.setData(permissions);
        return appUserResultResponse;
    }

}
