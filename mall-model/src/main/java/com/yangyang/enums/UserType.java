package com.yangyang.enums;

/**
 * @author gzy
 * @date 2020/4/25 21:33
 */
public enum UserType {
    /**
     * APP 用户
     */
    APP_USER(1,"app前端用户"),
    /**
     * 后端用户
     */
    BACKEND_USER(2,"后端用户")
    ;

    public Integer type;
    public String description;

    UserType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
