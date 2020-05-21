package com.yangyang.user.controller;

import com.yangyang.annotation.LogAnnotation;
import com.yangyang.constant.LogModule;
import com.yangyang.model.*;
import com.yangyang.pojo.request.UserRequest;
import com.yangyang.pojo.response.ResultResponse;
import com.yangyang.user.service.AppUserService;
import com.yangyang.utils.AppUserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/25 19:14
 */
@Api(tags = "用户管理")
@RestController
public class AppUserController {
    @Autowired
    private AppUserService appUserService;


    @GetMapping(value = "/current/user")
    public LoginAppUser currentLoginUser(){
        return AppUserUtils.loginAppUser();
    }


    @GetMapping(value = "/users-anon/internal",params = "username")
    public AppUserDTO findByUsername(@RequestParam String username) {
        AppUserDTO appUserDTO = appUserService.selectByUsername(username);
        return appUserDTO;
    }


    @PostMapping(value = "/users-anon/register")
    @ApiOperation(value = "用户注册",notes = "用户注册")
    public AppUser register(@RequestBody AppUser appUser) {
        if (StringUtils.isEmpty(appUser.getUsername())) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        if (StringUtils.isEmpty(appUser.getPassword())) {
            throw new IllegalArgumentException("密码不能为空");
        }
        appUserService.saveUser(appUser);
        return appUser;
    }


    @PreAuthorize("hasAuthority('back:user:query')")
    @PostMapping("/users/query")
    public ResultResponse findUsers(@RequestBody UserRequest userRequest){
        Page<AppUser> appUserPage = appUserService.selectByParams(userRequest);
        ResultResponse<Page> resultResponse = new ResultResponse();
        resultResponse.setSuccess(true);
        resultResponse.setData(appUserPage);
        resultResponse.setCode(HttpStatus.OK.value());
        return resultResponse;
    }


    @LogAnnotation(module = LogModule.USER_DETAILS)
    @PreAuthorize("hasAuthority('back:user:details')")
    @GetMapping("/users/{userId}/details")
    public ResultResponse<AppUser> findByUserId(@PathVariable Long userId) {
        AppUser user = appUserService.selectOne(userId);
        ResultResponse<AppUser> appUserResultResponse = new ResultResponse<>();
        appUserResultResponse.setData(user);
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @LogAnnotation(module = LogModule.LOGOUT)
    @PostMapping("/users/me/update")
    public ResultResponse<AppUser> updateMe(@RequestBody AppUser appUser) {
        LoginAppUser loginAppUser = AppUserUtils.loginAppUser();
        appUser.setUserId(loginAppUser.getUserId());
        appUser.setEnable(loginAppUser.getEnable());
        appUserService.update(appUser);
        ResultResponse<AppUser> appUserResultResponse = new ResultResponse<>();
        appUserResultResponse.setData(appUser);
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }

    /**
     * 管理员更新用户信息
     * @param appUser
     * @return
     */
    @PreAuthorize("hasAuthority('back:admin:update')")
    @PostMapping("/users/admin/update")
    public ResultResponse<AppUser> adminUpdateUser(@RequestBody AppUser appUser) {
        appUserService.update(appUser);
        ResultResponse<AppUser> appUserResultResponse = new ResultResponse<>();
        appUserResultResponse.setData(appUser);
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PostMapping(value = "/users/me/password",params = {"oldPassword","newPassword"})
    public ResultResponse updatePasswordMe(@RequestParam String oldPassword,@RequestParam String newPassword) {
        if (StringUtils.isEmpty(oldPassword)) {
            throw new IllegalArgumentException("旧密码不能为空");
        }
        if (StringUtils.isEmpty(newPassword)) {
            throw new IllegalArgumentException("新密码不能为空");
        }
        LoginAppUser loginAppUser = AppUserUtils.loginAppUser();
        appUserService.updatePassword(loginAppUser.getUserId(),oldPassword,newPassword);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAuthority('back:admin:password')")
    @PostMapping(value = "/users/{userId}/password",params = {"newPassword"})
    public ResultResponse resetPassword(@PathVariable Long userId,@RequestParam String newPassword) {
        appUserService.updatePassword(userId,null,newPassword);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAuthority('back:user:role:set')")
    @PostMapping(value = "/users/{userId}/roles")
    public ResultResponse roleToUser(@PathVariable Long userId, @RequestBody Set<Long> roleIds){
        appUserService.roleToUser(userId,roleIds);
        ResultResponse appUserResultResponse = new ResultResponse();
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }


    @PreAuthorize("hasAnyAuthority('back:user:roles')")
    @GetMapping(value = "/users/{userId}/viewRoles")
    public ResultResponse<List<SysAppUserRole>> findRolesByUserId(@PathVariable Long userId) {
        List<SysAppUserRole> rolesByUserId = appUserService.findRolesByUserId(userId);
        ResultResponse<List<SysAppUserRole>> appUserResultResponse = new ResultResponse<>();
        appUserResultResponse.setData(rolesByUserId);
        appUserResultResponse.setCode(HttpStatus.OK.value());
        appUserResultResponse.setSuccess(true);
        return appUserResultResponse;
    }

}
