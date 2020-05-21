package com.yangyang.pojo.request;

import lombok.Data;

/**
 * @author gzy
 * @date 2020/4/30 0:50
 */
@Data
public class SysPermissionRequest {
    private Long permissionId;
    private String permissionName;
    private String permission;
    private Integer page = 1;
    private Integer size = 10;
}
