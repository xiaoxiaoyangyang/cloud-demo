package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/5/3 12:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUserDTO extends AppUser implements Serializable {
    private Set<SysRole> sysRoles;

    private Set<String> permissions;
}
