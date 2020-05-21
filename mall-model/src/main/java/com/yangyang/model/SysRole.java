package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author gzy
 * @date 2020/4/25 21:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRole implements Serializable {
    private static final long serialVersionUID = 7931723314965195829L;
    private Long roleId;
    private String roleName;
    private String code;
    private Date createTime;
    private Date updateTime;
}
