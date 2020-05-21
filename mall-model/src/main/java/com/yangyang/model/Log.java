package com.yangyang.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * @author gzy
 * @date 2020/5/5 13:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private Long logId;
    private String username;
    private String module;
    private String params;
    private String remark;
    private Date createTime;
}
