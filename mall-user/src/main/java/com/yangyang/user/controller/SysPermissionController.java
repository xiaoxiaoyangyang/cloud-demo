package com.yangyang.user.controller;

import com.yangyang.model.Page;
import com.yangyang.model.SysPermission;
import com.yangyang.pojo.request.SysPermissionRequest;
import com.yangyang.pojo.response.ResultResponse;
import com.yangyang.user.service.SysPremissionService;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author gzy
 * @date 2020/4/26 12:20
 */

@Api(tags = "权限管理")
@RestController
public class SysPermissionController {
    @Autowired
    private SysPremissionService sysPremissionService;

    @PreAuthorize("hasAuthority('back:permission:save')")
    @PostMapping("/permission/save")
    public ResultResponse<SysPermission> save(@RequestBody SysPermission sysPermission){
        if (StringUtils.isEmpty(sysPermission.getPermission())) {
            throw new IllegalArgumentException("权限标识不能为空");
        }
        if (StringUtils.isEmpty(sysPermission.getPermissionName())) {
            throw new IllegalArgumentException("权限名不能为空");
        }
        sysPremissionService.save (sysPermission);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        resultResponse.setData(sysPermission);
        return resultResponse;
    }

    @PreAuthorize("hasAuthority('back:permission:details')")
    @GetMapping("/permission/{permissionId}/details")
    public ResultResponse<SysPermission> details(@PathVariable("permissionId")Long permissionId){
        SysPermission sysPermission = sysPremissionService.queryDetails(permissionId);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        resultResponse.setData(sysPermission);
        return resultResponse;
    }

    @PreAuthorize("hasAuthority('back:permission:update')")
    @PostMapping("/permission/update")
    public ResultResponse update(@RequestBody SysPermission sysPermission) {
        sysPremissionService.update(sysPermission);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        return resultResponse;
    }

    @PreAuthorize("hasAuthority('back:permission:delete')")
    @DeleteMapping("/permission/{permissionId}/delete")
    public ResultResponse delete(@PathVariable Long permissionId) {
        sysPremissionService.delete(permissionId);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        return resultResponse;
    }

    @PreAuthorize("hasAuthority('back:permission:query')")
    @PostMapping("/permission/query")
    public ResultResponse<Page<SysPermission>> query(@RequestBody SysPermissionRequest sysPermissionRequest) {
        Page<SysPermission> query = sysPremissionService.query(sysPermissionRequest);
        ResultResponse<Page<SysPermission>> resultResponse = new ResultResponse();
        resultResponse.setCode(HttpStatus.OK.value());
        resultResponse.setSuccess(true);
        resultResponse.setData(query);
        return resultResponse;
    }
}
