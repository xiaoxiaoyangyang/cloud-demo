package com.yangyang.pojo.request;

import lombok.Data;

import java.util.Date;

/**
 * @author gzy
 * @date 2020/5/5 13:18
 */
@Data
public class LogRequest {
    private String username;
    private String module;
    private Date createTime;
    private Integer page;
    private Integer size;
}
