package com.yangyang.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author gzy
 * @date 2020/5/3 4:06
 */
@Data

@NoArgsConstructor
public class MySimpleGrantedAuthority implements GrantedAuthority,Serializable {
    private static final long serialVersionUID = -418517343581613641L;

    private String role;

    public MySimpleGrantedAuthority(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }
    @Override
    public String getAuthority() {
        return null;
    }

}
