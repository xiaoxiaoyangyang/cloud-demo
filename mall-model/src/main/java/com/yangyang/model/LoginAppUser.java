package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author gzy
 * @date 2020/4/25 21:42
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginAppUser extends AppUser implements UserDetails, Serializable {
    private static final long serialVersionUID = 4167960190222228895L;

    private Set<SysRole> sysRoles;

    private Set<String> permissions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new HashSet<>();
        if (CollectionUtils.isNotEmpty(sysRoles)) {
            sysRoles.forEach(role->{
                if (role.getCode().startsWith("ROLE_")) {
                    collection.add(new SimpleGrantedAuthority(role.getCode()));
                } else {
                    collection.add(new SimpleGrantedAuthority("ROLE_"+role.getCode()));
                }
            });
        }

        if (CollectionUtils.isNotEmpty(permissions)) {
            permissions.forEach(permission -> {
                collection.add(new SimpleGrantedAuthority(permission));
            });
        }

        return collection;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
