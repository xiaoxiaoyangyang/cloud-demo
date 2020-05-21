package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author gzy
 * @date 2020/4/25 20:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 9083156892690705295L;
    private Long permissionId;
    private String permission;
    private String permissionName;
    private Date createTime;
    private Date updateTime;


}
