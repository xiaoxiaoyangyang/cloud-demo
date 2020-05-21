package com.yangyang.pojo.request;

import lombok.Data;

/**
 * @author gzy
 * @date 2020/4/30 0:11
 */
@Data
public class SysRoleRequest {
    private String roleName;
    private String code;
    private Integer size = 10;
    private Integer page = 1;
}
