package com.yangyang.enums;

/**
 * @author gzy
 * @date 2020/4/26 22:39
 */
public enum UserEnableType {
    ENABLE(1,"启用"),
    DISABLE(2,"禁用"),
    ;
    public Integer type;
    public String description;

    UserEnableType(Integer type, String description) {
        this.type = type;
        this.description = description;
    }
}
