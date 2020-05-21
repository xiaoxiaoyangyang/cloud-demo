package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gzy
 * @date 2020/4/25 21:37
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission implements Serializable {
    private static final long serialVersionUID = -2126831860379451897L;
    private Long roleId;
    private Long permissionId;
}
