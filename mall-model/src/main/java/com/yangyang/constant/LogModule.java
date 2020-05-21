package com.yangyang.constant;


import java.util.HashMap;
import java.util.Map;

/**
 * @author gzy
 * @date 2020/5/5 14:15
 */
public abstract class LogModule {
    public static final Map<String,String> MODULES = new HashMap<>();

    public static final String LOGIN = "LOGIN";
    public static final String LOGOUT = "LOGOUT";

    public static final String PERMISSION_SAVE = "permission_save";
    public static final String PERMISSION_DETAILS = "permission_details";
    public static final String PERMISSION_UPDATE = "permission_update";
    public static final String PERMISSION_DELETE = "permission_delete";
    public static final String PERMISSION_QUERY = "permission_query";

    public static final String ROLE_SAVE = "role_save";
    public static final String ROLE_DETAILS = "role_details";
    public static final String ROLE_UPDATE = "role_update";
    public static final String ROLE_DELETE = "role_delete";
    public static final String ROLE_QUERY = "role_query";
    public static final String ROLE_ONE_PERMISSION = "role_permission_details";
    public static final String ROLE_SET_PERMISSION = "role_set_permission";

    public static final String USER_REGISTER = "user_register";
    public static final String USER_DETAILS = "user_details";
    public static final String ME_USER_UPDATE = "me_user_update";
    public static final String ADMIN_USER_UPDATE = "admin_user_update";
    public static final String USER_DELETE = "user_delete";
    public static final String USER_QUERY = "user_query";
    public static final String ME_UPDATE_PASSWORD = "me_update_password";
    public static final String ADMIN_UPDATE_PASSWORD = "admin_update_password";
    public static final String USER_TO_ROLE = "user_to_role";
    public static final String USER_ROLE_DETAILS = "user_role_details";
    static {
        MODULES.put(LOGIN,"登录");
        MODULES.put(LOGOUT,"登出");
        MODULES.put(PERMISSION_SAVE,"保存权限");
        MODULES.put(PERMISSION_DETAILS,"权限详情");
        MODULES.put(PERMISSION_UPDATE,"修改权限详情");
        MODULES.put(PERMISSION_DELETE,"删除权限");
        MODULES.put(PERMISSION_QUERY,"查询权限列表");
        MODULES.put(ROLE_SAVE,"保存角色");
        MODULES.put(ROLE_DETAILS,"角色详情");
        MODULES.put(ROLE_UPDATE,"修改角色");
        MODULES.put(ROLE_DELETE,"删除角色");
        MODULES.put(ROLE_QUERY,"查询角色列表");
        MODULES.put(ROLE_ONE_PERMISSION,"角色分配的权限详情");
        MODULES.put(ROLE_SET_PERMISSION,"角色分配详情");
        MODULES.put(USER_REGISTER,"注册用户");
        MODULES.put(USER_DETAILS,"用户详情");
        MODULES.put(ME_USER_UPDATE,"自己更新自己的用户信息");
        MODULES.put(ADMIN_USER_UPDATE,"后台管理员修改用户信息");
        MODULES.put(USER_DELETE,"删除用户");
        MODULES.put(USER_QUERY,"查询用户列表");
        MODULES.put(ME_UPDATE_PASSWORD,"自己修改密码");
        MODULES.put(ADMIN_UPDATE_PASSWORD,"后台管理员修改密码");
        MODULES.put(USER_TO_ROLE,"用户分配角色");
        MODULES.put(USER_ROLE_DETAILS,"用户对应角色对应的详情");
    }
}
