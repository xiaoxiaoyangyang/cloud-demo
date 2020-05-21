package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author gzy
 * @date 2020/4/25 21:57
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysAppUserRole implements Serializable {
    private static final long serialVersionUID = -4201863528343391388L;
    private Long roleId;
    private Long userId;
}
